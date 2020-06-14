<template>

    <div style="display: inline-block">
    <todo-list :todos="todos">
        <template slot-scope="slotProps">
            <el-tag type="danger" effect="light"
                    style="font-size: 15px;height: 40px;padding-top: 4px;margin-left: 10px"
                    @click="seeDetails(slotProps.todo)">
                <el-avatar :size="30" :src="slotProps.todo.thematic.thm_icon" shape="square" style="float: left;"></el-avatar>
                <span style="margin-left: 4px;color: #696969">{{slotProps.todo.thematic.thm_name | ellipsis}}</span>
            </el-tag>
        </template>
    </todo-list>
    </div>
</template>

<script>
    import todoList from './ArticleDetailsThematicSlot'

    export default {
        name: "ArticleDetailsThematic",
        data() {
            return {
                todos: [
                    {
                        thematicId: '',
                        articleId: '',
                        article: [],
                        thematic: []
                    },

                ]
            }
        },
        components: {
            todoList
        },
        //限制文章显示内容的多少
        filters: {
            ellipsis(value) {
                if (!value) return ''
                if (value.length > 5) {
                    return value.slice(0, 5) + '...'
                }
                return value
            }
        },
        methods: {
            // 获取所有数据
            findAllThematicInArticle() {
                const _this = this
                axios.get('http://localhost:8181/thematic/findAllThematicInArticle?articleId=' + this.$route.query.articleId).then(function (resp) {
                    _this.todos = resp.data
                    console.log("gridData:::::" + _this.todos)
                    // console.log("gridDatathis.uid::::::"+this,uid)
                })
            },
            seeDetails(todo) {
                this.$router.push({
                    path: '/thematic',
                    query: {
                        articleId: todo.articleId,
                        thematicId: todo.thematicId
                    }
                })
            }
        },
        created() {
            this.findAllThematicInArticle();

        }
    }
</script>

<style scoped>

</style>