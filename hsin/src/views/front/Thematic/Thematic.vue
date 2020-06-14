<template>
    <div style="width: 1000px;" id="thematic">
        <!--<h2>这是专题页面</h2>-->
        <!--专题抬头-->
        <div style="width: 700px;margin-left: 210px">
            <!--专题图片-->
            <div class="demo-image" style="padding-top: 20px;padding-left: 20px">
                <div class="block" style="float: left;">
                    <el-image class="img"
                              :src="thematicData.thm_icon"
                              :fit="fit"></el-image>
                </div>
                <div style="position:absolute;top: 100px;left: 380px">
                    <p><span style="font-size: 20px;font-weight: bolder" v-model="thematicData.thm_name">{{thematicData.thm_name}}</span><br/><br/>
                        <span style="font-size: 15px;color: #696969">收录了7篇文章 · 0人关注</span>
                    </p>
                </div>
            </div>
            <div style="position: absolute;top: 150px;left: 700px">

                <div>
                    <el-button round
                               type="warning"
                               @click="submit()" style="width:100px">收录</el-button>
                    <!--点击收录，出现用户的文章列表-->
                    <el-dialog title="收录文章到该专题" :visible.sync="dialogTableVisible" @close="reload()">
                        <el-table :data="gridData">
                            <el-table-column property="head" width="150"></el-table-column>
                            <el-table-column property="articleId" width="150" v-if="show"></el-table-column>
                            <el-table-column align="right">
                                <template slot-scope="scope">
                                    <el-button
                                            :type="articleToThm.status==='收录'?'warning':'success'"
                                            @click="submitArticle(scope.row)" >收录</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-dialog>
                    <el-button round
                               :type="followThematicForm.thm_status==='关注'?'warning':'info'"
                               @click="followThematicStatus('followThematicForm')" style="width:100px;margin-left: 30px">
                        {{followThematicForm.thm_status}}
                    </el-button>
                    <el-form :model="followThematicForm" ref="followThematicForm">
                        <el-form-item>
                            <el-row hidden="true" v-model="followThematicForm.uid=this.uid"><span
                                    style="margin-left: 5px;font-size: 14px"></span></el-row>
                            <el-row hidden="true"
                                    v-model="followThematicForm.thematicId=this.thematicData.thematicId"><span
                                    style="margin-left: 5px;font-size: 14px"></span></el-row>
                        </el-form-item>
                    </el-form>
                </div>
            </div>

            <!--显示专题列表-->

            <div style="position: absolute;top: 210px;width: 680px;padding-left: 20px">
                <el-tabs v-model="activeName" @tab-click="handleClick" style="width: 660px">
                    <el-tab-pane label="最新收录" name="second" @>
                        <!--收录的文章-->
                        <thematicList/>
                    </el-tab-pane>
                </el-tabs>
            </div>
        </div>
        <!--专题右侧固定栏-->
        <div style="width: 300px;position: absolute;top:100px;left: 1000px">
            <!--公告-->
            <div style="margin-bottom: 30px">
                <p style="font-size: 15px;color: #696969">专题公告</p>
                <p style="font-size: 13px;color: black" v-model="thematicData.thm_describe">
                    {{thematicData.thm_describe}}</p>
            </div>
            <hr>
            <!--管理员-->
            <div>
                <p style="font-size: 15px;color: #696969">管理员</p>

                <div style="width: 300px;height: 50px;padding-top: 10px;">
                    <router-link :to="{path:'/user',query: {uid:thematicData.user.uid}}">
                    <el-avatar :size="35" :src="thematicData.user.usericon" style="float: left"></el-avatar>
                    <span style="margin-left:10px;font-size: 13px" v-model="thematicData.user.username">{{thematicData.user.username}}</span>
                    </router-link>
                </div>

            </div>
            <hr>
            <!--如果是普通用户，显示关注的人，如果是管理员显示编辑专题和删除专题按钮-->
            <div v-if="this.flag == true">
                <div v-has="adminid">
                    <p style="font-size: 15px;color: #696969">
                        <router-link :to="{path:'/thematicEdit',query:{thematicId:this.thematicData.thematicId}}"><span style="font-size: 15px;color: #696969">编辑专题</span></router-link>
                        <span style="margin-left: 10px" @click="deletethematic()">删除专题</span></p>
                </div>
                <div v-has="uid">
                    <p style="font-size: 15px;color: #696969">关注的人</p>
                    <!--<p></p>-->
                    <ThematicUser/>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import thematicList from './ThematicArticle'
    import ThematicUser from './ThematicUser'

    export default {
        name: "Thematic",
        data() {
            return {
                flag: false,
                fit: 'cover',
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                activeName: 'second',
                thematicData: {
                    thematicId: '',
                    uid: '',
                    thm_icon: '',
                    thm_name: '',
                    thm_day: '',
                    thm_time: '',
                    thm_check: '',
                    thm_submit: '',
                    thm_describe: '',
                    user: [],
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
                dialogTableVisible: false,
                submitSelect: false,
                followThematicForm: {
                    uid: '',
                    thematicId: '',
                    thm_status: '关注',
                },
                ArticleToThematicForm:{
                    articleId:'',
                    thematicId:'',
                    thm_art_status:'收录'
                },
                articleToThm:{
                    status:''
                }
            }
        },
        components: {
            thematicList,
            ThematicUser
        },
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
                let articleId = row.articleId;
                let thematicId = _this.thematicData.thematicId;
                axios.post('http://localhost:8181/thematic/addArticleToThematic', {
                    articleId: row.articleId,
                    thematicId: _this.thematicData.thematicId,
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
            followThematicStatus(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (_this.followThematicForm.thm_status === '已关注') {
                            let uid = this.uid;
                            let thematicId = this.thematicData.thematicId;
                            axios.put('http://localhost:8181/follow/updateFollow?uid=' + uid + '&thematicId=' + thematicId).then(function (resp) {
                                _this.reload()
                                _this.$notify({
                                    title: '取消成功！',
                                    message: '您已取消关注' + _this.thematicData.thm_name,
                                    type: 'success'
                                });

                            })
                        } else {
                            axios.put('http://localhost:8181/follow/addFollowThematic', this.followThematicForm).then(function (resp) {
                                _this.reload()
                                _this.$notify({
                                    title: '关注成功~~',
                                    message: '您已关注' + _this.thematicData.thm_name,
                                    type: 'success'
                                });
                            })
                        }

                    } else {
                        return false;
                    }
                });
            },
            findFollowThematicStatus(uid, thematicId) {
                const _this = this
                axios.get('http://localhost:8181/follow/findFollowThematicStatus?uid=' + uid + '&thematicId=' + thematicId).then(function (resp) {
                    if (resp.data.thm_status === undefined) {
                        console.log("status!!!!!:::::" + resp.data.thm_status);
                    } else {
                        _this.followThematicForm.thm_status = resp.data.thm_status;
                        console.log("otherUserId:::::" + _this.followThematicForm.thm_status)
                    }

                })
            },
            deletethematic(){
                const _this = this
                _this.$alert('确定删除' + _this.thematicData.thm_name + '吗？', '删除后无法恢复哦~', {
                    confirmButtonText: '确定',
                    callback: action => {
                        let thematicId = _this.thematicData.thematicId;
                        axios.put('http://localhost:8181/thematic/deleteThematic?thematicId=' + thematicId).then(function (resp) {
                            _this.$notify({
                                title: '删除成功！',
                                message: '您已删除' + _this.thematicData.thm_name,
                                type: 'success'
                            });
                            _this.$router.push('/thematicFindAllList')
                        })
                    }
                })

            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/thematic/findThematicById?thematicId=' + this.$route.query.thematicId).then(function (resp) {
                _this.thematicData = resp.data
                //v-if 判断数据是否加载再渲染页面
                _this.flag = true
                _this.adminid = _this.thematicData.uid
            })

            let uid = _this.$local.get('uid')
            if (uid) {
                _this.uid = uid;
            }
            this.findFollowThematicStatus(this.uid, this.$route.query.thematicId);
        }


    }
</script>

<style scoped>
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