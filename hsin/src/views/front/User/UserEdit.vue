<template>
    <div>
        <div style="width: 600px;margin-left: 450px;">
        <el-form ref="userModifyForm" :model="userModifyForm" label-width="80px">
            <el-form-item style="margin-top: 20px;">
                <div>
                    <!--<el-form-item prop="usericon">-->

                    <el-avatar :src="userData.usericon" v-model="userModifyForm.usericon" :size="120"
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
                        <img width="100%" :src="userModifyForm.usericon" alt="">
                    </el-dialog>

                </div>

            </el-form-item>


            <el-form-item label="昵称" style="margin-top: 50px;">
                <el-row hidden="true" v-model="userModifyForm.uid=this.userData.uid"></el-row>
                <el-input v-model="userModifyForm.username"></el-input>
            </el-form-item>

            <el-form-item label="所在地" style="margin-top: 50px;">
                <el-input v-model="userModifyForm.useraddress">{{userData.useraddress}}</el-input>
            </el-form-item>

            <el-form-item label="邮箱" style="margin-top: 50px;">
                <el-input v-model="userModifyForm.useremail">{{userData.useremail}}</el-input>
            </el-form-item>

            <el-form-item label="个人介绍" style="margin-top: 50px;">
                <el-input type="textarea" v-model="userModifyForm.userdescribe">{{userData.userdescribe}}</el-input>
            </el-form-item>
            <el-form-item style="margin-top: 50px;">
                <el-button type="primary" @click="submitUserDes('userModifyForm')">保存</el-button>
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
                userData: {
                    uid: '',
                    usericon: '',
                    username: '',
                    useraddress: '',
                    useremail: '',
                    userdescribe: '',
                },
                userModifyForm: {
                    username: '',
                    usericon: '',
                    useraddress: '',
                    useremail: '',
                    userdescribe: ''
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
                            that.userModifyForm.usericon = result.url
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
                this.userModifyForm.usericon = file.url
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
                    this.ImageShow = this.userData.usericon
                } else if (this.flag === true) {
                    this.ImageShow = this.userModifyForm.usericon
                }
            },
            changeVisibleToIcon() {
                const _this = this
                _this.VisibleToIcon = false;
                _this.VisibleToUpload = true;
                _this.ButtonFlagToChangeIcon = false;
            },

            dialogClose(userModifyForm) {
                const _this = this
                this.$refs.userModifyForm.resetFields(); //表单重置
            },
            submitUserDes(formname) {
                const _this = this
                this.$refs[formname].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/user/updateUserModify', this.userModifyForm).then(function (resp) {
                            console.log(resp)
                            window.location.reload()
                            _this.userModifyForm.show = false;
                        })
                    } else {
                        return false;
                    }
                });
            },
            findUser() {
                const _this = this
                axios.get('http://localhost:8181/user/findUserById?uid=' + this.$route.query.uid).then(function (resp) {
                    _this.userData = resp.data
                    _this.userModifyForm = resp.data
                    _this.userModifyForm.show = false;
                })
            }
        },
        created() {
            this.findUser();
            this.showOfImage();
        }
    }
</script>

<style scoped>

</style>