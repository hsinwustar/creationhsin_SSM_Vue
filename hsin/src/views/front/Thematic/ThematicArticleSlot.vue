<template>
    <div>
        <div
                style="
                width: 700px;
              "
                v-for="(todo,index) in reverseTodos"
                :key="index">
            <slot :todo="todo">
            </slot>
        </div>
    </div>
</template>

<script>
    export default {
        name: "ThematicArticleSlot",
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