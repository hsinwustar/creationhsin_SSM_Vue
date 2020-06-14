<template>
    <div>
        <div style="width: 700px;padding-bottom: 10px;display: inline" v-for="(todo,index) in reverseTodos" :key="index">
            <slot :todo="todo" style="display: inline-block">
            </slot>
        </div>
    </div>
</template>

<script>
    export default {
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