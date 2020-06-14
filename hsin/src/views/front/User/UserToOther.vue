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
    import todoList from '../Thematic/ThematicUserSlot'
    export default {
        name: "UserToOther",
        data() {
            return {
                todos: [
                    {
                        uid: '',
                        otherUserId: '',
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
            findFollowUserOfOther() {
                const _this = this
                axios.get('http://localhost:8181/follow/findFollowUserOfOther?otherUserId=' + this.$route.query.uid).then(function (resp) {
                    _this.todos = resp.data
                    console.log("gridData:::::" + _this.todos)
                    // console.log("gridDatathis.uid::::::"+this,uid)
                })
            },

        },
        created() {
            this.findFollowUserOfOther();

        },
        watch: {
            '$route' (to, from) {
                this.$router.go(0);
            }
        },
    }
</script>

<style scoped>

</style>