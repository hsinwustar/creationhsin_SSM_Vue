<template>
    <div>
    <div style="margin-left:300px;">
        <todo-list :todos="todos">
            <template slot-scope="slotProps">
                <div style="width: 400px;padding-left: 50px;" @click="see(slotProps.todo)">
                    <div style="float: left;padding-top: 10px">
                        <div style="margin-top: 10px;">
                            <span style="font-size: 18px;font-weight: bolder">{{slotProps.todo.thm_name}}</span><br>
                        </div>
                        <!--内容-->
                        <div style="font-size: 15px;color: #9C9C9C;width: 250px;margin-top:17px;padding-bottom: 20px;">

                            {{slotProps.todo.thm_describe | ellipsis}}
                        </div>
                    </div>
                    <div style="padding-top: 10px;padding-bottom: 10px">
                        <el-avatar :size="80" :src="slotProps.todo.thm_icon" shape="square"
                                   style=""></el-avatar>
                    </div>
                </div>
            </template>
        </todo-list>
    </div>

        <div style="width: 300px;position: absolute;top:100px;left: 1050px">
            <router-link to="/thematicCreate" style="text-decoration: none;color: white;font-family: 'Microsoft YaHei';">
                <div style="width: 250px;height:50px;background-color: #E6A23C;border-radius: 0.5em;text-align:center;line-height: 50px;margin:0 auto;">
                创建专题
            </div>
            </router-link>
        </div>

    </div>
</template>

<script>
    import todoList from './ThematicTransitSlot'

    export default {
        data() {
            return {
                todos: [
                    {
                        thematicId: '',
                        uid: '',
                        thm_icon: '',
                        thm_name: '',
                        thm_day: '',
                        thm_time: '',
                        thm_check: '',
                        thm_submit: '',
                        thm_describe: '',
                        username: '',
                        usericon: ''
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
                if (value.length > 200) {
                    return value.slice(0, 200) + '...'
                }
                return value
            }
        },
        methods: {
            // 获取所有数据
            findAll() {
                axios.post('http://localhost:8181/thematic/findAll').then(result => {
                    this.todos = result.data;
                });
            },
            see(todo) {
                // console.log("this.thematicData::::")
                console.log("this.thematicData.id::::" + todo.thematicId)
                this.$router.push({
                    path: '/thematic',
                    query: {
                        thematicId: todo.thematicId
                    }
                })
            }
        },
        created() {
            this.findAll();
        },

    }
</script>

<style scoped>

</style>