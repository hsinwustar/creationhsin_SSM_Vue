<template>
    <!--循环显示评论列表，也可能是一个父子组件-->
    <div>
        <todo-list :todos="todos">
            <template slot-scope="slotProps">
                <div style="margin-top: 30px;margin-left: 20px">
                    <div>
                        <el-avatar :size="40" :src="slotProps.todo.user.usericon" style="float: left;"></el-avatar>
                        <div>
                            <span style="margin-left: 5px;font-size: 14px">{{slotProps.todo.user.username}}</span><br>
                            <span style="margin-left: 5px;font-size: 12px">{{slotProps.todo.time}}</span>
                        </div>
                    </div>
                    <div style="margin-left: 40px">
                        <p>{{slotProps.todo.content}}</p>
                        <i class="el-icon-caret-top"
                               style="font-size: 20px;color: #828282;border: 1px rgb(220,223,230) solid;border-radius: 1em"></i><span
                                style="font-size: 13px;color: #828282;margin-left: 4px">赞</span>
                        <i class="el-icon-chat-dot-square"
                               style="font-size: 20px;color: #828282;border: 1px rgb(220,223,230) solid;border-radius: 1em;margin-left: 10px"></i><span
                                style="font-size: 13px;color: #828282;margin-left: 4px" @click="openRemark(scope.row)">回复</span>

                    </div>
                </div>
            </template>
        </todo-list>
    </div>
</template>

<script>
    import todoList from './CommtentSlot'

    export default {
        name: "Comment",
        data(){
            return{
                todos: [
                    {
                        commentId: '',
                        articleId: '',
                        uid:'',
                        article: [],
                        user: [],
                        content:'',
                        time:''
                    },

                ],
            }
        },
        components: {
            todoList
        },

        methods:{
            // 根据文章id获取评论
            findCommentByarticleId() {
                const _this = this
                axios.get('http://localhost:8181/comment/findCommentByarticleId?articleId=' + this.$route.query.articleId).then(function (resp) {
                    _this.todos = resp.data
                    console.log("gridData:::::" + _this.todos)
                })
            },
        },
        created(){
            this.findCommentByarticleId();

            let recomment = this.$local.get('recomment')
            let userinfo = this.$local.get('user')

            let articleData = this.$local.get('articleData')
            if (userinfo) {
                this.usericon = userinfo.usericon;
                this.username = userinfo.username;
                this.uid = userinfo.uid;
                console.log("this,uid:::::::" + this.uid)

            }
            if(recomment){
                this.commentId = recomment.commentId;
                console.log("this,commentId:::::::" + this.commentId)
            }
            if(articleData){
                this.articleId = articleData.articleId;
                console.log("articleId20200402::::"+this.articleId)
            }

        },
    }
</script>

<style scoped>

</style>