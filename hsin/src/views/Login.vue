<template >
<div class="home">
    <div style="
    padding-top: 100px;
    width: 500px;
    height: 200px;
    display: flex;
    justify-content: left;align-items: center;
    -webkit-box-shadow: #666 0px 0px 50px;
                -moz-box-shadow: #666 0px 0px 50px;
                box-shadow: darkgrey 0px 0px 15px;
">

        <div>
    <el-form style="width: 380px;height:180px;" :model="loginForm" :rules="rules" ref="loginForm" label-width="100px"
             class="demo-ruleForm">

        <el-form-item label="用户名" prop="useremail">
            <el-input ref="username" v-model="loginForm.useremail"></el-input>
        </el-form-item>

        <el-form-item label="密码"  prop="userpwd">
            <el-input type="password" v-model="loginForm.userpwd"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="login()">登录</el-button>
            <el-button @click="resetForm('loginForm')">重置</el-button>
        </el-form-item>

    </el-form></div>
    </div>
    <div style="width:500px;height: 80px;background-color: olive;position: absolute;top:158px;left:518px;box-shadow: darkgrey 0px 0px 15px;"></div>
    </div>
</template>

<script>
    export default {
        name: 'login',
        data() {
            return {
                loginForm: {
                    useremail: '',
                    userpwd: '',
                    info: '',
                    usertype: '',
                    userstatus: '',
                    uid: '',
                    username: ''
                },
                rules: {
                    useremail: [
                        {required: true, message: '请输入邮箱账号', trigger: 'blur'}
                    ],
                    userpwd: [
                        {required: true, message: '请输入密码', trigger: 'blur'}
                    ]
                },
            };
        },
        methods: {
            login() {
                var b = true;
                const _this = this;
                console.log(b);
                if (b) {
                    this.$axios.post('http://localhost:8181/user/login', this.loginForm).then(function (resp) {
                        console.log(resp);
                        _this.info = resp.data.user;
                        _this.usertype = resp.data.user.usertype;
                        _this.uid = resp.data.user.uid;
                        console.log(_this.info);
                        console.log(resp.data.user.uid);
                        if (typeof(_this.info) != "undefined") {
                            //获取ref存储入localstorage
                            _this.$local.set('user', _this.info);
                            _this.$local.set('userPermission',_this.usertype);
                            _this.$local.set('uid',_this.uid);
                            //用户登录
                            if (_this.info.usertype == "普通用户") {
                                _this.$alert('《' + _this.info.username + '》登录成功', 'this is ok', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/home')
                                    },
                                    query: {
                                        userInfo: _this.info,
                                    }
                                })
                            } else if (_this.info.usertype == "超级管理员") {//管理员登录
                                _this.$alert('《' + _this.info.username + '》登录成功', 'this is ok', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/marticle')
                                    },
                                    query: {
                                        userInfo: _this.info

                                    }
                                })

                            }
                        }
                        else if (resp.data.msg1 == "该用户正在封禁中，请等待解封！") {
                            _this.info = resp.data.msg1;
                            console.log("账户冻结::::::" + _this.info)
                            _this.$alert(_this.info, 'this is error', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    window.location.reload()
                                },
                                query: {
                                    userInfo: _this.info
                                }
                            })
                        } else {
                            _this.info = resp.data.msg2;
                            console.log("密码错误::::::" + _this.info)
                            _this.$alert(_this.info, 'this is error', {
                                confirmButtonText: '确定',
                                callback: action => {
                                    window.location.reload()
                                },
                                query: {
                                    userInfo: _this.info
                                }
                            })
                        }
                    })
                }
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
    .home{;
        height: 600px;
       display: flex;
        justify-content: center;
        align-items: center;
        overflow: auto;
        /*background-color: olive;*/
    }

    .home::-webkit-scrollbar{
           display: none;
       }

</style>