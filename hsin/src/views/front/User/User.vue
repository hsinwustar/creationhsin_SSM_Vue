<template>
    <div style="width: 1000px;" id="user">
        <!--<h2>这是专题页面</h2>-->
        <!--专题抬头-->
        <div style="width: 700px;margin-left: 210px;">
            <!--专题图片-->
            <div class="demo-image" style="padding-top: 20px;padding-left: 20px">
                <el-avatar
                        :size="100"
                        :src="userData.usericon"
                ></el-avatar>

                <div style="position:absolute;top: 100px;left: 380px">
                    <p><span style="font-size: 20px;font-weight: bolder" v-model="userData.username">{{userData.username}}</span><br/><br/>
                        <span style="font-size: 15px;color: #696969">收录了7篇文章 · 0人关注</span>
                    </p>
                </div>
            </div>
            <div style="position: absolute;top: 150px;left: 780px">
                <div v-if="this.flag == true">
                    <el-form :model="followUserForm" ref="followUserForm">
                        <el-form-item>
                            <el-row hidden="true" v-model="followUserForm.uid=this.uid"><span
                                    style="margin-left: 5px;font-size: 14px"></span></el-row>
                            <el-row hidden="true"
                                    v-model="followUserForm.otherUserId=this.userData.uid"><span
                                    style="margin-left: 5px;font-size: 14px"></span></el-row>
                        </el-form-item>
                    </el-form>
                    <el-button round
                               :type="followUserForm.status==='关注'?'warning':'info'"
                               @click="followUser('followUserForm')" style="width:100px">{{followUserForm.status}}
                    </el-button>
                </div>
            </div>

            <!--显示专题列表-->

            <div style="position: absolute;top: 210px;width: 680px;padding-left: 20px">
                <!--<hr style="background-color: black;height: 2px">-->
                <el-tabs v-model="activeName" @tab-click="handleClick" style="width: 660px">
                    <el-tab-pane label="最新评论" name="first">
                        <UserComment/>
                    </el-tab-pane>
                    <el-tab-pane label="文章" name="second" v-if="this.flag == true">
                        <!--收录的文章-->
                        <div v-has="adminid">
                        <UserList/>
                        </div>
                        <div v-has="uid">
                         <UserListToOther/>
                        </div>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </div>
        <!--专题右侧固定栏-->
        <div style="width: 300px;position: absolute;top:100px;left: 1000px">
            <!--公告-->
            <div style="margin-bottom: 30px">
                <div style="display: inline" v-if="flag==true">
                    <p style="font-size: 15px;color: #696969;display: inline-block">个人介绍</p>
                    <el-button v-has="adminid" style="margin-left: 175px;display: inline-block" size="mini"
                               @click="edit()">编辑
                    </el-button>
                    <el-row style="font-size: 13px;color: black;" v-model="userData.userdescribe"
                            v-show="describeVisible">
                        {{userData.userdescribe}}
                    </el-row>
                    <div v-show="editdescribeVisible">
                        <el-form :model="userDesForm" ref="userDesForm">
                            <el-form-item>
                                <el-row hidden="true" v-model="userData.uid"></el-row>
                            </el-form-item>
                            <el-form-item>
                                <el-input type="textarea" v-model="userDesForm.userdescribe"
                                          style="font-size: 13px;color: black">
                                </el-input>
                            </el-form-item>
                        </el-form>
                        <el-button @click="dialogClose('userDesForm')">取 消</el-button>
                        <el-button type="primary" @click="submitUserDes('userDesForm')">确 定</el-button>
                    </div>
                </div>
            </div>
            <hr>
            <!--管理员-->
            <div>
                <p style="font-size: 15px;color: #696969">创建的专题</p>
                <UserThematic/>
            </div>
            <hr>
            <!--如果是普通用户，显示关注的人，如果是管理员显示编辑专题和删除专题按钮-->
            <div v-if="this.flag == true">
                <div v-has="adminid">
                    <router-link :to="{path:'/userEdit',query:{uid:this.uid}}"><p style="font-size: 15px;color: #696969"><span>编辑资料</span></p></router-link>
                </div>
                <div v-has="uid">
                    <p style="font-size: 15px;color: #696969">关注的人</p>
                    <UserToOther/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import UserThematic from './UserThematic'
    import UserList from './UserArticle'
    import UserListToOther from './UserArticleListToOther'
    import UserComment from "./UserComment";
    import UserToOther from './UserToOther'

    export default {

        data() {
            return {
                describeVisible: true,
                editdescribeVisible: false,
                flag: false,
                follow: false,
                fit: 'cover',
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                activeName: 'second',
                userData: {
                    uid: '',
                    usericon: '',
                    username: '',
                    usertype: '',
                    useraddress: '',
                    userstatus: '',
                    userdescribe: '',
                },
                uid: '',
                adminid: '',

                gridData: [{
                    head: 'www',
                    articleId: '',
                }, {
                    head: '2222',
                    articleId: '',
                },
                ],
                userDesForm: {
                    uid: '',
                    userdescribe: '',
                },
                followUserForm: {
                    uid: '',
                    otherUserId: '',
                    status: '关注'
                },
            }
        },
        components: {
            UserList,
            UserThematic,
            UserComment,
            UserListToOther,
            UserToOther
        },
        inject: ['reload'],
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            },
            submit() {
                this.dialogTableVisible = true
                const _this = this
                axios.get('http://localhost:8181/article/findArticleByUId?uid=' + this.uid).then(function (resp) {
                    _this.gridData = resp.data

                })
            },
            submitArticle(row) {
                const _this = this
                console.log("row.articleId::::::" + row.articleId)
                console.log("" + _this.thematicData.thematicId)
                axios.post('http://localhost:8181/thematic/addArticleToThematic', {
                    articleId: row.articleId, thematicId: _this.thematicData.thematicId
                }).then(function (resp) {
                    console.log(resp)
                    _this.$message({
                        message: '文章已投递！',
                        type: 'success'
                    });
                })
            },
            reload() {
                window.location.reload()
            },
            mounted() {
                document.querySelector('body').setAttribute('style', 'background-color:#white')
            },
            beforeDestroy() {
                document.querySelector('body').removeAttribute('style')
            },
            edit() {

                const _this = this
                _this.describeVisible = false;
                _this.editdescribeVisible = true;
                axios.get('http://localhost:8181/user/findUserById?uid=' + _this.userData.uid).then(function (resp) {
                    _this.userDesForm = resp.data
                })
            },
            dialogClose(userDesForm) {
                const _this = this
                _this.editdescribeVisible = false; //dialog关闭
                this.$refs.userDesForm.resetFields(); //表单重置
            },
            submitUserDes(formname) {
                const _this = this
                this.$refs[formname].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/user/updateUserDescribe', this.userDesForm).then(function (resp) {
                            console.log(resp)
                            _this.reload()
                            _this.userDesForm.show = false;
                        })
                    } else {
                        return false;
                    }
                });
            },
            /*关注作者*/
            followUser(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (_this.followUserForm.status === '已关注') {
                            let uid = this.uid;
                            let otherUserId = this.userData.uid;
                            axios.put('http://localhost:8181/follow/updateFollowStatus?uid=' + uid + '&otherUserId=' + otherUserId).then(function (resp) {
                                 _this.reload()
                                _this.$notify({
                                    title: '取消成功！',
                                    message: '您已取消关注' + _this.userData.username,
                                    type: 'success'
                                });

                            })
                        } else {
                            axios.put('http://localhost:8181/follow/addFollowUser', this.followUserForm).then(function (resp) {
                                 _this.reload()
                                _this.$notify({
                                    title: '关注成功~~',
                                    message: '您已关注' + _this.userData.username,
                                    type: 'success'
                                });

                            })
                        }

                    } else {
                        return false;
                    }
                });

            },

            findFollowUserStatus(uid, otherUserId) {
                const _this = this
                axios.get('http://localhost:8181/follow/findFollowUserStatus?uid=' + uid + '&otherUserId=' + otherUserId).then(function (resp) {
                    console.log("status!!!!!111:::::" + resp.data.status);
                    if (resp.data.status === undefined) {
                        console.log("status!!!!!:::::" + resp.data.status);
                    } else {
                        _this.followUserForm.status = resp.data.status;
                        console.log("otherUserId:::::" + _this.followUserForm.status)
                    }

                })
            },
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/user/findUserById?uid=' + this.$route.query.uid).then(function (resp) {

                _this.userData = resp.data
                // console.log("dataShuju::::" + _this.thematicData.uid)
                //v-if 判断数据是否加载再渲染页面
                _this.flag = true

                _this.adminid = _this.userData.uid
                console.log("adminid::::" + _this.adminid)
            })

            let uid = _this.$local.get('uid')
            console.log("userper::::::" + uid)
            if (uid) {
                _this.uid = uid;
                console.log("this,uid:::::::" + _this.uid)

            }

            /*判断登录进来的id与当前页面id是否一致，不一致就显示关注按钮，一致就不显示*/
            if (_this.uid !== _this.userData.uid) {
                _this.follow = true;
            } else if (_this.uid === _this.userData.uid) {
                _this.follow = false;
            }


            this.findFollowUserStatus(this.uid, this.$route.query.uid);


        }

    }
</script>


<style>
    .block {
        box-shadow: 0 0 30px #DCDCDC inset;
        -webkit-box-shadow: 0 0 30px #DCDCDC inset;
        width: 102px;
        height: 102px;
        border-radius: 0.6em
    }

    .img {
        margin-left: 2px;
        margin-top: 2px;
        width: 100px;
        height: 100px;
        border-radius: 0.6em;
        border: rgba(248, 248, 55, 0.5)
    }

    hr {
        background-color: #E8E8E8;
        border: none;
        height: 1px
    }

</style>
