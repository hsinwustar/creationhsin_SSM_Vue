<template>
    <div class="home">
        <div style="margin-left: 300px;" >
            <todo-list :todos="todos">
                <template slot-scope="slotProps">
                    <div style="width: 700px;display: inline;padding-left: 30px;" @click="see(slotProps.todo)">
                        <div style="display: inline-block;margin-top: 10px;padding-top: 10px; ">
                                <span style="font-size: 20px;font-weight: bolder;padding-left: 13px">{{slotProps.todo.head}}</span><br>
                            <!--内容-->
                            <div style="font-size: 15px;color: #9C9C9C;width: 650px;padding-bottom: 20px;">
                                <div class=" ql-snow">
                                    <div class="ql-editor" v-html="$options.filters.ellipsis(slotProps.todo.content)">
                                    </div>
                                </div>
                                <div style="font-size: 13px;color: #9C9C9C;padding-left: 13px">
                                    <span><i class="el-icon-s-custom" style="color: #E6A23C"></i>&nbsp;{{slotProps.todo.author}}</span>
                                    <span style="margin-left: 30px"><i class="el-icon-date" style="color: #E6A23C"></i>&nbsp;{{slotProps.todo.time}}</span>
                                    <span style="float: right"><i class="el-icon-caret-top" style="color: #E6A23C"></i>&nbsp;{{slotProps.todo.like}}</span></div>
                            </div>
                        </div>
                    </div>
                </template>
            </todo-list>
        </div>
        <div style="width: 300px;position: absolute;top:100px;left: 1050px">
            <div style="width: 250px;height:50px;background-color: #E6A23C;border-radius: 0.5em;text-align:center;line-height: 50px;margin:0 auto;">
                <el-link href="/thematicFindAllList" style="text-decoration: none;color: white;font-family: 'Microsoft YaHei';">发现专题</el-link>
            </div>

        </div>
    </div>
</template>

<script>
    import todoList from './HomeArticleSlot'

    export default {
        data() {
            return {
                todos: [
                    {
                        articleId: '',
                        head: '',
                        author: '',
                        content: '',
                        like:'',
                        time:''
                    },
                ],
                busy: false,
                itemsPerLoad: 5,
                pageIndex: 1,
                flag:false

            }
        },

        components: {
            todoList
        },

        //限制文章显示内容的多少
        filters: {
            ellipsis(value) {
                if (!value) return ''
                if (value.length > 150) {
                    return value.slice(0, 150) + '...'
                }
                return value
            }
        },

        methods: {
            // 获取所有数据
            findAll2() {
                axios.post('http://localhost:8181/article/findAll2').then(result => {
                    this.todos = result.data;
                });
            },
            see(todo) {
                this.$router.push({
                    path: '/articleDetails',
                    query: {
                        articleId: todo.articleId
                    }
                })
            }
        },
        created() {
            this.findAll2();
         }
    }
</script>
<style>
    .el-link{
        text-decoration:none
    }
</style>
