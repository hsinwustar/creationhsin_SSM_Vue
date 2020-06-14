<template>
    <div>
        <div v-for="(todo,index) in reverseTodos" :key="index"
             style="
             background-color: rgb(252,252,252);
             margin-bottom: 20px;
             width:700px;
             -webkit-box-shadow: #666 0px 0px 50px;
             -moz-box-shadow: #666 0px 0px 50px;
             box-shadow: darkgrey 0px 0px 15px;">
            <slot :todo="todo">
            </slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "FollowSlot",
        props: {
            todos: {
                type: Array
            }
        },
        computed: {
            reverseTodos() {
                return this.todos.reverse();
            }
        },
    }
</script>

<style scoped>

</style>