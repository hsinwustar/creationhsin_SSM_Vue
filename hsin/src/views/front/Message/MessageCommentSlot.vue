<template>
    <div>
        <div style="width: 700px;

             background-color: rgb(252,252,252);
             border-radius: 1em;
             -webkit-box-shadow: #666 0px 0px 50px;
             -moz-box-shadow: #666 0px 0px 50px;
             box-shadow: darkgrey 0px 0px 15px;
             "
             v-for="(todo,index) in reverseTodos" :key="index">
            <slot :todo="todo" style="">
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