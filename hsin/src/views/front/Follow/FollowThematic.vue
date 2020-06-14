<template>
    <div style="margin-left: 300px">
        <todo-list :todos="todos">
            <template slot-scope="slotProps">
                <div style="width: 700px;display: inline;padding-left: 50px;" @click="see(slotProps.todo)">
                    <div style="display: inline-block;margin-top: 10px">
                        <p>
                            <span style="font-size: 18px;font-weight: bolder">{{slotProps.todo.thematic.thm_name}}</span><br>
                        </p>
                        <!--内容-->
                        <div style="font-size: 15px;color: #9C9C9C;width: 500px;padding-bottom: 20px;">
                            {{slotProps.todo.thematic.thm_describe | ellipsis}}
                        </div>
                    </div>
                    <div style="display: inline-block;margin-top: 20px">
                        <el-avatar :size="80" :src="slotProps.todo.thematic.thm_icon" shape="square"
                                   style="float: left;"></el-avatar>
                    </div>
                </div>
            </template>
        </todo-list>
    </div>
</template>

<script>
    import todoList from './FollowSlot'

    export default {
        data() {
            return {
                todos: [
                    {
                        thematicId: '',
                        uid: '',
                        thematic:[]

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
            findAllFollowThematic() {
                axios.post('http://localhost:8181/follow/findFollowThematicListOfPersonalFollow?uid=' + this.$route.query.uid).then(result => {
                    this.todos = result.data;
                });
            },
            see(todo) {
                this.$router.push({
                    path: '/thematic',
                    query: {
                        thematicId: todo.thematicId
                    }
                })
            }
        },
        created() {
            this.findAllFollowThematic();
            const _this = this
            let uid = _this.$local.get('uid')
            if (uid) {
                _this.uid = uid;
            }
        },
    }
</script>

<style scoped>

</style>