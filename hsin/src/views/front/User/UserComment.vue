<template>
    <div >
        <todo-list :todos="todos">
            <template slot-scope="slotProps">
                <div style="margin-top: 30px;margin-left: 20px" @click="seeDetails(slotProps.todo)">
                    <div>
                        <el-avatar :size="40" :src="slotProps.todo.user.usericon" style="float: left;"></el-avatar>
                        <div>
                            <span style="margin-left: 5px;font-size: 14px">{{slotProps.todo.user.username}}</span><br>
                            <span style="margin-left: 5px;font-size: 12px">{{slotProps.todo.time}}</span>
                            <span style="margin-left: 15px;font-size: 12px;">来自</span><span style="margin-left: 5px;font-size: 12px;color: #E6A23C">{{slotProps.todo.article.head}}</span>
                        </div>
                    </div>
                    <div style="margin-left: 40px">
                        <p>{{slotProps.todo.content}}</p>
                    </div>
                </div>
            </template>
        </todo-list>
    </div>
</template>

<script>
    import todoList from '../Comment/CommtentSlot'
    export default {
        data(){
            return{
                uid:'',
                usericon:'',
                username:'',
                todos: [
                    {
                        commentId: '',
                        articleId: '',
                        uid:'',
                        article: [],
                        user: [],
                        content:'',
                        time:'',
                        useericon:'',
                        username:'',
                        head:''
                    },

                ]
            }
        },
        components: {
            todoList
        },

        methods:{

            // 根据文章id获取评论
            findCommentByUId() {
                const _this = this
                axios.get('http://localhost:8181/comment/findCommentByUId?uid=' + this.$route.query.uid).then(function (resp) {
                    _this.todos = resp.data
                    console.log("gridData:::::" + _this.todos)
                    // console.log("gridDatathis.uid::::::"+this,uid)
                })
            },
            seeDetails(todo) {
                this.$router.push({
                    // path: '/articleDetails',
                    path: '/articleDetails',
                    query: {
                        articleId: todo.articleId
                    }
                })
            }

        },
        created(){
            this.findCommentByUId();

            let recomment = this.$local.get('recomment')
            let userinfo = this.$local.get('user')

            let articleData = this.$local.get('articleData')
            if (userinfo) {
                this.usericon = userinfo.usericon;
                this.username = userinfo.username;
                this.uid = userinfo.uid;
                console.log("this.uid:::::::" + this.uid)

            }
            if(recomment){
                this.commentId = recomment.commentId;
                console.log("this,commentId:::::::" + this.commentId)
            }
            if(articleData){
                this.articleId = articleData.articleId;
                console.log("articleId20200402::::"+this.articleId)
            }

        }
    }
</script>

<style scoped>

</style>