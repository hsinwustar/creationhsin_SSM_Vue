<template>
    <div style="margin-left: 300px">
        <todo-list :todos="todos">
            <template slot-scope="slotProps">
                <router-link :to="{path:'/user',query: {uid:slotProps.todo.otherUserId}}">
                    <div style="width: 700px;padding-left: 50px;color: #696969">
                        <div style="float: left;padding-top: 10px">
                            <div>
                            <span style="font-size: 18px;font-weight: bolder">{{slotProps.todo.user.username}}</span><br></div>
                            <!--内容-->
                            <div style="font-size: 15px;color: #9C9C9C;width: 500px;padding-bottom: 15px;">
                                {{slotProps.todo.user.userdescribe | ellipsis}}
                            </div>
                        </div>
                        <div style="margin-top: 20px;padding-top: 20px">
                            <el-avatar :size="100" :src="slotProps.todo.user.usericon" shape="square"
                                       style=""></el-avatar>
                        </div>
                    </div>
                </router-link>
            </template>
        </todo-list>
    </div>
</template>

<script>
    import todoList from './FollowSlot'

    export default {
        name: "FollowUser",
        data() {
            return {
                todos: [
                    {
                        uid: '',
                        otherUserId: '',
                        user: []
                    }
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
                if (value.length > 30) {
                    return value.slice(0, 30) + '...'
                }
                return value
            }
        },
        methods: {
            // 获取所有数据
            findFollowUserOfOther() {
                const _this = this
                axios.get('http://localhost:8181/follow/findFollowUserListOfPersonalFollow?uid=' + this.$route.query.uid).then(function (resp) {
                    _this.todos = resp.data

                })
            },

        },
        created() {
            this.findFollowUserOfOther();
        },
        watch: {
            '$route'(to, from) {
                this.$router.go(0);
            }
        }
    }
</script>

<style scoped>

</style>