<template>
    <div style="display: inline-block">
        <todo-list :todos="todos">
            <template slot-scope="slotProps">
                <router-link :to="{path:'/user',query: {uid:slotProps.todo.uid}}">
                    <el-avatar
                            :size="40"
                            :src="slotProps.todo.user.usericon"
                            style="float: left;"
                            ></el-avatar>
                </router-link>
            </template>
        </todo-list>
    </div>
</template>

<script>
    import todoList from './ThematicUserSlot'

    export default {
        data() {
            return {
                todos: [
                    {
                        uid: '',
                        thematicId: '',
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
            findFollowThematicOfUser() {
                const _this = this
                axios.get('http://localhost:8181/follow/findFollowThematicOfUser?thematicId=' + this.$route.query.thematicId).then(function (resp) {
                    _this.todos = resp.data
                })
            },
        },
        created() {
            this.findFollowThematicOfUser();

        }
    }
</script>

<style scoped>
    /*hr {*/
        /*background-color: #E8E8E8;*/
        /*border: none;*/
        /*height: 1px*/
    /*}*/
</style>