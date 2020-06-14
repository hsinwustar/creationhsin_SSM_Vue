<template>
  <div id="app">
    <router-view v-if="alive" />
  </div>
</template>

<script>

export default {
    provide() {
        return {
            reload: this.reload
        }
    },
    data() {
        return {
            alive: true
        }
    },
    methods: {
        reload() {
            this.alive= false
            this.$nextTick(() => {
                this.alive = true
            })
        }
    }

}
</script>

<style>


</style>
