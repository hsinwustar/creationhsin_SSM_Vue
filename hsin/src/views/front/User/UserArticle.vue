<template>
    <div>
        <todo-list :todos="todos">
            <template slot-scope="slotProps">
                <div style="width: 700px" @click="seeDetails(slotProps.todo)">
                    <!--标题-->
                    <p>
                        <span style="font-size: 18px;font-weight: bolder">{{slotProps.todo.head}}</span><br>
                    </p>
                    <!--内容-->
                    <div style="font-size: 15px;color: #9C9C9C;width: 700px">
                        <div class=" ql-snow">
                            <div class="ql-editor" v-html="$options.filters.ellipsis(slotProps.todo.content)" >
                            </div>
                        </div>
                    </div>
                    <!--用户名，评论数，点赞数-->
                    <p style="font-size: 13px;color: #9C9C9C">
                        <span>{{slotProps.todo.author}}</span><span style="margin-left: 30px"><i
                            class="el-icon-s-comment"></i>{{slotProps.todo.time}}</span><span style="margin-left: 5px"><i
                            class="el-icon-caret-top"></i>{{slotProps.todo.like}}</span></p>
                </div>
                <hr>
            </template>
        </todo-list>
    </div>
</template>

<script>
    import todoList from './UserArticleSlot'
    export default {
        data() {
            return {
                todos: [
                    {
                        uid: '',
                        articleId: '',
                        head: '',
                        content:'',
                        author:'',
                        articlestate:'',
                        like:'',
                        time:'',
                        user: []
                    },
                ]
            }
        },
        components: {
            todoList
        },
        //限制文章显示内容的多少
        filters: {
            ellipsis (value) {
                if (!value) return ''
                if (value.length > 200) {
                    return value.slice(0,200) + '...'
                }
                return value
            }
        },
        methods: {
            // 获取所有数据
            findAllArticleInUser() {
                const _this = this
                axios.get('http://localhost:8181/article/findArticleByUIdAll?uid=' + this.$route.query.uid).then(function (resp) {
                    _this.todos = resp.data
                })
            },
            seeDetails(todo){
                this.$router.push({
                    path:'/articleDetails',
                    query: {
                        articleId:todo.articleId,
                        thematicId:todo.thematicId
                    }
                })
            }
        },
        created() {
            this.findAllArticleInUser();
            let userinfo = this.$local.get('user')
            if (userinfo) {
                this.usericon = userinfo.usericon;
                this.username = userinfo.username;
                this.uid = userinfo.uid;

            }

        }
    }
</script>

<style scoped>

</style>