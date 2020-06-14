<template>
    <div>
        <div style="display: flex;justify-content: center;align-items: center;">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" >
            <el-form-item prop="thm_icon">
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
                >
                    <i class="el-icon-plus" />
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="ruleForm.thm_icon" alt="">
                </el-dialog>
            </el-form-item>
            <!--其他-->
            <el-form-item label="名称" prop="thm_name">
                <el-input placeholder="请输入专题名称" v-model="ruleForm.thm_name"></el-input>
            </el-form-item>
            <el-form-item label="用户" prop="uid" hidden="true">
                <el-input v-model="ruleForm.uid=this.uid"></el-input>
            </el-form-item>
            <el-form-item label="创建时间" required>
                <el-col :span="11">
                    <el-form-item prop="thm_day">
                        <el-date-picker type="date" placeholder="选择日期" v-model="ruleForm.thm_day"
                                        style="width: 100%;"></el-date-picker>
                    </el-form-item>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                    <el-form-item prop="thm_time">
                        <el-time-picker placeholder="选择时间" v-model="ruleForm.thm_time"
                                        style="width: 100%;"></el-time-picker>
                    </el-form-item>
                </el-col>
            </el-form-item>
            <el-form-item label="审核设置" prop="thm_check">
                <el-switch v-model="ruleForm.thm_check"  active-value="审核" inactive-value="不审核"></el-switch>
            </el-form-item>
            <el-form-item label="投稿设置" prop="thm_submit">
                <el-radio-group v-model="ruleForm.thm_submit">
                    <el-radio label="允许"></el-radio>
                    <el-radio label="不允许"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="描述" prop="thm_describe">
                <el-input type="textarea" placeholder="专题描述请勿超过500字" v-model="ruleForm.thm_describe"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
        </div>
    </div>
</template>

<script>
    import { client } from '@/utils/oss'
    export default {
        data() {
            return {
                dialogVisible:false,
                dialogImageUrl:'',
                Aliyun: {}, // 存签名信息
                progress: 0, // 进度条
                imgUrl: '',
                ruleForm: {
                    thm_icon: '',
                    thm_name: '',
                    uid: '',
                    thm_day: '',
                    thm_time: '',
                    thm_check: false,
                    thm_submit: '',
                    thm_describe: '',
                    thematicID:''

                },
                rules: {
                    thm_name: [
                        {required: true, message: '请输入专题名称', trigger: 'blur'},
                        {min: 1, max: 30, message: '长度在 1-30 个字符以内', trigger: 'blur'}
                    ],
                    thm_day: [
                        {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
                    ],
                    thm_time: [
                        {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
                    ],
                    thm_submit: [
                        {required: true, message: '请选择是否允许投稿', trigger: 'change'}
                    ],
                    thm_describe: [
                        {required: true, message: '请简要描述专题', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            // 获取阿里云数据
            async getAliyun() {
                const { data } = await getAliyun()
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
                            progress: function(p) { // 获取进度条的值
                                console.log(p)
                                that.progress = p * 100
                            }
                        }).then(
                        result => {
                            // 对返回结果再进行处理
                            // console.log(result)
                            // that.imgUrl = 'http://' + result.bucket + '.' + that.Aliyun.endpoint + '/' + result.name
                            that.ruleForm.thm_icon = result.url
                            that.imgUrl.push({
                                name: file.file.name,
                                url: result.url
                            })
                            console.log(that.imgUrl)
                        }).catch(err => {
                        console.log('err:', err)
                    })
                }
                multipartUpload()
            },
            // 图片预览
            handlePictureCardPreview(file) {
                this.ruleForm.thm_icon = file.url
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

            handleAvatarSuccess(res, file) {
                console.log(this.ruleForm.thm_icon)
                this.ruleForm.thm_icon = URL.createObjectURL(file.raw);
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    console.log(formName)
                    if (valid) {
                        axios.post('http://localhost:8181/thematic/addThematic', this.ruleForm).then(function (resp) {
                            console.log(resp)
                            if (resp) {
                                _this.$alert('《' + _this.ruleForm.thm_name + '》创建成功！', 'okkkk', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push({path:'/thematicFindAllList'})
                                    }
                                })
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            let userinfo = this.$local.get('user')
            if (userinfo) {
                this.uid = userinfo.uid;
            }
        }
    }
</script>

<style scoped>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        margin: 0 auto;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 18px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>