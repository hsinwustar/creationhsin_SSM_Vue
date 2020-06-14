<template>
    <div>
        <div style="width: 600px;margin-left: 450px;">
        <el-form ref="thematicModifyForm" :model="thematicModifyForm" label-width="80px">
            <el-form-item prop="thm_icon" style="margin-top: 30px;">
                <div>
                    <!--<el-form-item prop="usericon">-->

                    <el-avatar :src="thematicData.thm_icon" v-model="thematicModifyForm.thm_icon" :size="120"
                               style="float: left;margin-right: 50px" v-show="VisibleToIcon"></el-avatar>
                    <el-button @click="changeVisibleToIcon()" style="position: relative;left: 50px;top:50px" v-show="ButtonFlagToChangeIcon">更改头像</el-button>
                    <el-upload
                            action=""
                            :http-request="Upload"
                            :data="Aliyun"
                            :multiple="false"
                            :show-file-list="true"
                            list-type="picture-card"
                            :on-preview="handlePictureCardPreview"
                            :on-remove="handleRemove"
                            :limit="5"
                            style="clear: none"
                            v-show="VisibleToUpload"

                    >
                        <i class="el-icon-plus"/>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="thematicModifyForm.thm_icon" alt="">
                    </el-dialog>
                    <!--<p style="color: #999;">图片上传限制： 1.最多5张； 2.最大1M</p>-->

                </div>

            </el-form-item>

            <el-form-item label="名称" prop="thm_name" style="margin-top: 50px;">
                <el-row hidden="true" v-model="thematicModifyForm.thematicId=this.thematicData.thematicId"></el-row>
                <el-input v-model="thematicModifyForm.thm_name"></el-input>
            </el-form-item>
            <el-form-item label="审核设置" prop="thm_check" style="margin-top: 50px;">
                <el-switch v-model="thematicModifyForm.thm_check"  active-value="审核" inactive-value="不审核"></el-switch>
            </el-form-item>
            <el-form-item label="投稿设置" prop="thm_submit" style="margin-top: 50px;">
                <el-radio-group v-model="thematicModifyForm.thm_submit">
                    <el-radio label="允许"></el-radio>
                    <el-radio label="不允许"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="描述" prop="thm_describe" style="margin-top: 50px;">
                <el-input type="textarea" v-model="thematicModifyForm.thm_describe"></el-input>
            </el-form-item>
            <el-form-item style="margin-top: 50px;">
                <el-button type="primary" @click="submitThematicDes('thematicModifyForm')">保存</el-button>
                <el-button @click="dialogClose">取消</el-button>
            </el-form-item>
        </el-form>
        </div>
    </div>
</template>

<script>
    import {client} from '@/utils/oss'

    export default {
        name: "UserEdit",
        data() {
            return {
                // ImageShow:showOfImage,
                VisibleToIcon: true,
                VisibleToUpload: false,
                ButtonFlagToChangeIcon:true,
                flag: false,
                dialogVisible: false,
                dialogImageUrl: '',
                Aliyun: {}, // 存签名信息
                progress: 0, // 进度条
                imgUrl: '',
                thematicData: {
                    thematicId: '',
                    thm_icon: '',
                    thm_name: '',
                    thm_check: '',
                    thm_status: '',
                    thm_describe: '',
                },
                thematicModifyForm: {
                    thm_name: '',
                    thm_icon: '',
                    thm_check: '',
                    thm_status: '',
                    thm_describe: ''
                }
            }
        },
        methods: {

            // 获取阿里云数据
            async getAliyun() {
                const {data} = await getAliyun()
                this.Aliyun = data
            },
            // 上传图片
            Upload(file) {
                const that = this
                // 判断扩展名
                const tmpcnt = file.file.name.lastIndexOf('.')
                const exname = file.file.name.substring(tmpcnt + 1)
                const names = ['jpg', 'jpeg', 'png']
                if (names.indexOf(exname) < 0) {
                    this.$message.error('不支持的格式!')
                    return
                }
                if (file.size > 1024 * 1024) {
                    this.$message.error('图片大小最大1M')
                    return
                }

                async function multipartUpload() {
                    // const fileName = that.name + file.file.uid
                    const fileName = that.Aliyun.objectName + +'/' + Date.now() + '-' + file.file.name
                    // fileName = aliyunConfig.objectName+'/'+Date.now()+'-'+file.name //所要上传的文件名拼接 (test/)
                    // 定义唯一的文件名，打印出来的uid其实就是时间戳
                    // client 是第一步中的 client
                    client(that.Aliyun).put(fileName, file.file,
                        {
                            progress: function (p) { // 获取进度条的值
                                console.log(p)
                                that.progress = p * 100
                            }
                        }).then(
                        result => {
                            // 下面是如果对返回结果再进行处理，根据项目需要
                            // console.log(result)
                            // that.imgUrl = 'http://' + result.bucket + '.' + that.Aliyun.endpoint + '/' + result.name
                            that.thematicModifyForm.thm_icon = result.url
                            that.imgUrl.push({
                                name: file.file.name,
                                url: result.url
                            })
                            that.flag = true;
                            console.log(that.imgUrl)
                        }).catch(err => {
                        console.log('err:', err)
                    })
                }

                multipartUpload()
            },
            // 图片预览
            handlePictureCardPreview(file) {
                this.thematicModifyForm.thm_icon = file.url
                this.dialogVisible = true
            },
            // 删除图片
            handleRemove(file, fileList) {
                // console.log(file)
                for (var i in this.imgUrl) {
                    if (this.imgUrl[i].name === file.name) {
                        this.imgUrl.splice(i, 1)
                    }
                }
            },
            showOfImage() {
                if (this.flag === false) {
                    this.ImageShow = this.thematicData.thm_icon
                } else if (this.flag === true) {
                    this.ImageShow = this.thematicModifyForm.thm_icon
                }
            },
            changeVisibleToIcon() {
                const _this = this
                _this.VisibleToIcon = false;
                _this.VisibleToUpload = true;
                _this.ButtonFlagToChangeIcon = false;
            },

            dialogClose(thematicModifyForm) {
                const _this = this
                this.$refs.thematicModifyForm.resetFields(); //表单重置
            },
            submitThematicDes(formname) {
                const _this = this
                this.$refs[formname].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/thematic/updateThematicModify', this.thematicModifyForm).then(function (resp) {
                            console.log(resp)
                            window.location.reload()
                            _this.thematicModifyForm.show = false;
                        })
                    } else {
                        return false;
                    }
                });
            },
            findThematic() {
                const _this = this
                axios.get('http://localhost:8181/thematic/findThematicById?thematicId=' + this.$route.query.thematicId).then(function (resp) {
                    _this.thematicData = resp.data
                    _this.thematicModifyForm = resp.data
                    _this.thematicModifyForm.show = false;
                })
            }
        },
        created() {
            this.findThematic();
            this.showOfImage();
        }
    }
</script>

<style scoped>

</style>