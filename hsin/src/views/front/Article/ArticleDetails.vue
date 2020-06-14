<template>
    <div>
        <!--权限判定是否显示“编辑文章”这个链接，只有作者本人可以看到-->
        <!--<router-link to="/articleEdiot">编辑文章</router-link>-->
        <!--左侧容器-->
        <div style="width: 850px;margin-left: 130px; ">
            <div style="-webkit-box-shadow: rgba(10,16,34,.2) 0px 0px 50px;-moz-box-shadow: rgba(10,16,34,.2) 0px 0px 50px;box-shadow: rgba(10,16,34,.2) 0px 0px 20px;">
                <!--标题加作者信息-->
                <div style="background-color: rgb(252,252,252);height: 150px;padding-top: 15px;padding-left: 30px;">
                    <div style="margin-left: 20px;">
                        <h1>{{articleData.head}}</h1>
                        <div style="height: 30px;margin-top: 30px">
                            <router-link :to="{path:'/user',query:{uid:articleData.uid}}">
                                <el-avatar :size="50" :src="articleData.user.usericon" style="float: left;"></el-avatar>
                            </router-link>
                            <div style="margin-left:10px;font-size: 16px">
                                <span style="margin-left:10px;font-family: 'Microsoft YaHei';font-size: 18px">{{articleData.author}}</span>
                                <el-form :model="followUserForm" ref="followUserForm">
                                    <el-form-item>
                                        <el-row hidden="true" v-model="followUserForm.uid=this.uid"><span
                                                style="margin-left: 5px;font-size: 14px"></span></el-row>
                                        <el-row hidden="true"
                                                v-model="followUserForm.otherUserId=this.articleData.user.uid"><span
                                                style="margin-left: 5px;font-size: 14px"></span></el-row>
                                    </el-form-item>
                                </el-form>
                                <el-button size="mini" style="position: absolute;left: 270px;top:223px"
                                           :type="followUserForm.status==='关注'?'warning':'info'"
                                           @click="followUser('followUserForm')">{{followUserForm.status}}
                                </el-button>
                            </div>
                            <div style="font-size: 14px;color: #696969;position: absolute;left: 330px;top:232px">
                                <span style="margin-left:10px;">{{articleData.time}}</span><span
                                    style="margin-left: 10px;">字数 481</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div style="background-color: rgb(252,252,252);padding-top: 20px;">
                    <!--展示编辑器内容-->
                    <div class=" ql-snow" style="margin-left: 50px;margin-right: 50px;">
                        <div class="ql-editor" v-html="articleData.content" style="font-family: 'Microsoft YaHei';font-size: 18px;color: rgb(89,91,89);">
                        </div>
                    </div>

                </div>
                <!--点赞+收藏-->
                <div style="background-color: rgb(252,252,252);height: 80px;padding-top: 40px;padding-bottom: 40px;">
                    <div style="margin-left: 20px">

                        <div style="float: left">
                            <el-form :model="likeForm" ref="likeForm">
                                <el-form-item>
                                    <el-row hidden="true" v-model="likeForm.uid=this.uid"><span
                                            style="margin-left: 5px;font-size: 14px"></span></el-row>
                                    <el-row hidden="true" v-model="likeForm.articleId=this.articleData.articleId"><span
                                            style="margin-left: 5px;font-size: 14px"></span></el-row>

                                </el-form-item>
                            </el-form>

                            <el-button :type="likeForm.likebyuser==='0'?'primary':'info'" icon="el-icon-caret-top"
                                       circle
                                       style="margin-left: 50px;margin-bottom: 50px"
                                       @click="like('likeForm')"></el-button>
                            <span style="padding-left: 5px;font-family: '宋体';font-size: 12px">共有{{articleData.like}}人赞同了这篇文章</span>
                        </div>
                        <div style="float: left">
                            <el-form :model="starForm" ref="starForm">
                                <el-form-item>
                                    <el-row hidden="true" v-model="starForm.uid=this.uid"><span
                                            style="margin-left: 5px;font-size: 14px"></span></el-row>
                                    <el-row hidden="true" v-model="starForm.articleId=this.articleData.articleId"><span
                                            style="margin-left: 5px;font-size: 14px"></span></el-row>
                                </el-form-item>
                            </el-form>
                            <el-button :type="starForm.starstatus==='0'?'warning':'info'" icon="el-icon-star-off" circle
                                       style="margin-left: 460px;margin-bottom: 50px"
                                       @click="star('starForm')"></el-button>
                        </div>

                    </div>
                </div>
            </div>

            <div style="-webkit-box-shadow: rgba(10,16,34,.2) 0px 0px 50px;
                -moz-box-shadow: rgba(10,16,34,.2) 0px 0px 50px;
                box-shadow: rgba(10,16,34,.2) 0px 0px 20px;">
            <!--评论区-->
            <div style="clear:both;">
                <!--写下评论-->
                <div style="background-color: rgb(250,250,250);margin-top: 30px;padding-top: 30px;padding-left: 20px;padding-right: 20px">
                    <el-form :model="commentForm" ref="commentForm" size="small">
                        <el-form-item>
                            <el-avatar :size="30" :src="usericon" style="float: left;"
                                       v-model="commentForm.usericon=this.usericon"></el-avatar>
                            <el-row v-model="commentForm.username=this.username"><span
                                    style="margin-left: 5px;font-size: 14px">{{username}}</span></el-row>
                            <el-row hidden="true" v-model="commentForm.uid=this.uid"><span
                                    style="margin-left: 5px;font-size: 14px">{{uid}}</span></el-row>
                            <el-row hidden="true" v-model="commentForm.articleId=this.$route.query.articleId"><span
                                    style="margin-left: 5px;font-size: 14px">{{articleId}}</span></el-row>
                        </el-form-item>
                        <el-form-item>
                            <el-input
                                    type="textarea"
                                    :rows="2"
                                    placeholder="写下你的评论..."
                                    v-model="commentForm.content">
                            </el-input>
                        </el-form-item>
                    </el-form>
                    <el-button @click="postComments('commentForm')">发布</el-button>
                    <el-button>取消</el-button>

                </div>
            </div>


            <!--展示评论-->

            <div style="background-color: rgb(250,250,250);padding-left: 20px;padding-top: 60px;padding-bottom: 30px">
                <p style="border-left: 5px #E6A23C solid;font-size: 18px"><span style="margin-left: 10px">全部评论</span>
                </p>

                <comment/>
            </div>
            </div>
            <!--收入的专题-->
            <div style="background-color: rgb(250,250,250);padding-left: 20px;margin-top: 30px;padding-top: 20px;padding-bottom: 30px;-webkit-box-shadow: rgba(10,16,34,.2) 0px 0px 50px;
                -moz-box-shadow: rgba(10,16,34,.2) 0px 0px 50px;
                box-shadow: rgba(10,16,34,.2) 0px 0px 20px;">
                <p style="border-left: 5px #E6A23C solid;font-size: 18px"><span
                        style="margin-left: 10px">被以下专题收入，发现更多精彩内容</span>
                </p>

                <articleDetailsThematic/>
            </div>
        </div>
        <!--右侧容器-->
        <div style="width: 280px;position: absolute;top:90px;left: 1020px">
            <!--作者信息展示-->
            <div style="background-color: rgb(250,250,250);padding-left: 20px;padding-top: 30px;padding-bottom: 20px">
                <!--作者信息-->
                <div>
                    <el-avatar :size="50" :src="articleData.user.usericon" style="float: left;"></el-avatar>
                    <div style="margin-left:10px;font-size: 16px">
                        <span style="margin-left:10px;">{{articleData.author}}</span>
                    </div>
                    <div style="font-size: 14px;color: #696969">
                        <span style="margin-left:10px;">共发表7篇文章</span>
                    </div>
                </div>
                <hr style="margin-top: 20px;width: 240px;margin-left: 0px">
                <!--作者文章展示前三-->
                <p style="border-left: 5px #E6A23C solid;font-size: 18px;margin-bottom: 30px"><span
                        style="margin-left: 10px">文章创作展示</span>
                <div>
                    <div style="margin-top: 10px;">
                        <el-link type="info" style="color: #696969;font-size: 17px">一个人的成长，必有原因</el-link>
                        <br>
                        <span style="color: #828282;font-size: 13px">55人点赞</span>
                    </div>
                    <div style="margin-top: 10px;">
                        <el-link type="info" style="color: #696969;font-size: 17px">书读多了，也有颗敬畏之心</el-link>
                        <br>
                        <span style="color: #828282;font-size: 13px">55人点赞</span>
                    </div>
                    <div style="margin-top: 10px;">
                        <el-link type="info" style="color: #696969;font-size: 17px">来自谷歌的创业故事《谷歌方法》</el-link>
                        <br>
                        <span style="color: #828282;font-size: 13px">55人点赞</span>
                    </div>
                    <div style="margin-top: 10px;">
                        <el-link type="info" style="color: #696969;font-size: 17px">来自谷歌的创业故事《谷歌方法》</el-link>
                        <br>
                        <span style="color: #828282;font-size: 13px">55人点赞</span>
                    </div>
                    <div style="margin-top: 10px;">
                        <el-link type="info" style="color: #696969;font-size: 17px">来自谷歌的创业故事《谷歌方法》</el-link>
                        <br>
                        <span style="color: #828282;font-size: 13px">55人点赞</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import articleDetailsThematic from './ArticleDetailsThematic'
    import comment from '../Comment/Comment'

    export default {
        name: "ArticleDetails",

        data() {
            return {
                fit: 'cover',
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                articleData: {
                    articleId: '',
                    uid: '',
                    head: '',
                    author: '',
                    like: '',
                    content: '',
                    articlestate: '',
                    time: '',
                    user: []
                },
                textarea: '',
                commentForm: {
                    username: '',
                    uid: '',
                    content: '',
                    articleId: '',
                    usericon: ''
                },
                username: '',
                uid: '',
                usericon: '',
                followUserForm: {
                    uid: '',
                    otherUserId: '',
                    status: '关注'
                },
                starForm: {
                    uid: '',
                    articleId: '',
                    starstatus: '0'
                },
                likeForm: {
                    uid: '',
                    articleId: '',
                    likebyuser: '0'
                },
                articleForm: {
                    articleId: '',
                    likeNum: 0
                }
            }
        },
        components: {
            articleDetailsThematic,
            comment
        },
        inject: ['reload'],
        methods: {
            findArticleById() {
                const _this = this
                axios.get('http://localhost:8181/article/findArticleById?articleId=' + this.$route.query.articleId).then(function (resp) {
                    _this.articleData = resp.data
                    // _this.articleData.like = resp.data.like
                    _this.$local.set('articleData', _this.articleData);
                    console.log("gridData:::::" + _this.articleData)
                })
            },
            postComments(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/comment/addComment', this.commentForm).then(function (resp) {
                            if (resp) {
                                _this.$message({
                                    message: _this.username + '评论成功',
                                    type: 'success'
                                });
                                _this.reload()
                            }
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
                            let otherUserId = this.articleData.uid;
                            axios.put('http://localhost:8181/follow/updateFollowStatus?uid=' + uid + '&otherUserId=' + otherUserId).then(function (resp) {
                                _this.reload()
                                _this.$notify({
                                    title: '取消成功！',
                                    message: '您已取消关注' + _this.articleData.user.username,
                                    type: 'success'
                                });
                            })
                        } else {
                            axios.put('http://localhost:8181/follow/addFollowUser', this.followUserForm).then(function (resp) {
                                _this.reload()
                                _this.$notify({
                                    title: '关注成功~~',
                                    message: '您已关注' + _this.articleData.user.username,
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

            /*收藏文章*/
            star(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (_this.starForm.starstatus === '1') {
                            let uid = this.uid;
                            let articleId = this.articleData.articleId;
                            axios.put('http://localhost:8181/follow/deleteFollowArticleStatus?uid=' + uid + '&articleId=' + articleId).then(function (resp) {
                                _this.reload()
                                _this.$notify({
                                    title: '取消成功！',
                                    message: '您已取消收藏' + _this.articleData.head,
                                    type: 'success'
                                });
                            })
                        } else {
                            axios.put('http://localhost:8181/follow/addFollowArticle', this.starForm).then(function (resp) {
                                _this.reload()
                                _this.$notify({
                                    title: '收藏成功~~',
                                    message: '您已收藏' + _this.articleData.head,
                                    type: 'success'
                                });
                            })
                        }

                    } else {
                        return false;
                    }
                });
            },
            findFollowArticle(uid, articleId) {
                const _this = this
                axios.get('http://localhost:8181/follow/findFollowArticleById?uid=' + uid + '&articleId=' + articleId).then(function (resp) {
                    console.log("starstatus!!!!!111:::::" + resp.data.starstatus);
                    if (resp.data.starstatus === undefined) {
                        console.log("starstatus!!!!!:::::" + resp.data.starstatus);
                    } else {
                        _this.starForm.starstatus = resp.data.starstatus;
                        console.log(" _this.starForm.starstatus:::::" + _this.starForm.starstatus)
                    }

                })
            },
            /*读取点赞数*/
            findLikeNum() {
                const _this = this

                axios.put('http://localhost:8181/article/updateArticleLike', this.likeForm).then(function (resp) {
                    _this.articleData.like = resp.data.like;
                })

            },
            subLikeNum() {
                const _this = this

                axios.put('http://localhost:8181/article/updateArticleLikeSub', this.likeForm).then(function (resp) {
                    _this.articleData.like = resp.data.like;
                })
            },
            /*点赞文章*/
            like(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (_this.likeForm.likebyuser === '1') {
                            let uid = this.uid;
                            let articleId = this.articleData.articleId;
                            axios.put('http://localhost:8181/article/deleteLikeArticleStatus?uid=' + uid + '&articleId=' + articleId).then(function (resp) {
                                _this.reload()
                            })
                        } else if (_this.likeForm.likebyuser === undefined || _this.likeForm.likebyuser === '0') {
                            axios.put('http://localhost:8181/article/addArticleLike', this.likeForm).then(function (resp) {
                                _this.reload()
                            })
                        }

                    } else {
                        return false;
                    }
                });
            },
            findArticleLikeById(uid, articleId) {
                const _this = this
                axios.get('http://localhost:8181/article/findArticleLikeById?uid=' + uid + '&articleId=' + articleId).then(function (resp) {
                    console.log("starstatus!!!!!111:::::" + resp.data.likebyuser);
                    if (resp.data.likebyuser === undefined) {
                        console.log("starstatus!!!!!:::::" + resp.data.likebyuser);
                    } else {
                        _this.likeForm.likebyuser = resp.data.likebyuser;
                        console.log(" _this.starForm.starstatus:::::" + _this.likeForm.likebyuser)
                    }

                })
            }

        },
        created() {
            this.findArticleById();

            let userinfo = this.$local.get('user')
            let articleData = this.$local.get('articleData')
            console.log("userper::::::" + userinfo)
            if (userinfo) {
                this.usericon = userinfo.usericon;
                this.username = userinfo.username;
                this.uid = userinfo.uid;
                console.log("this,uid:::::::" + this.usericon)

            }
            if (articleData) {
                this.articleuid = articleData.uid;
                this.articleId = articleData.articleId;
                console.log("articleId20200402::::" + this.articleId)
                console.log("articleuid::::" + this.articleuid)
            }

            this.findFollowUserStatus(this.uid, this.articleuid);
            this.findFollowArticle(this.uid, this.articleId);
            this.findArticleLikeById(this.uid, this.articleId);
        }
    }

</script>

<style scoped>
    hr {
        background-color: #E8E8E8;
        border: none;
        height: 1px
    }
</style>