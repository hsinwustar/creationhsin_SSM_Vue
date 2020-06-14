<template>
    <div>
        <div v-for="(todo,index) in reverseTodos"
             :key="index"

             style="
             background-color: rgb(252,252,252);
             margin-bottom: 20px;
             -webkit-box-shadow: #666 0px 0px 50px;
             -moz-box-shadow: #666 0px 0px 50px;
             box-shadow: darkgrey 0px 0px 15px;
             width:400px;"

        >
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
            },
        },
        computed: {
            reverseTodos() {
                return this.todos.reverse();
            }
        }
    }
</script>

<style>

</style>