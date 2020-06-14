<template>
    <div>
        <div style="width: 700px;padding-bottom: 10px" v-for="(todo,index) in reverseTodos" :key="index">
            <slot :todo="todo" style="">
            </slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserThematicSlot",
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