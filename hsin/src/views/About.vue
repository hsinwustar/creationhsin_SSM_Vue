<template>
  <div class="about">
    <el-container style="height: 720px; border: 1px solid #eee">

      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

        <el-menu router :default-openeds="openeds">
          <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''"v-if="item.show" >
            <template slot="title">{{item.name}}</template>
            <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path"
                          :class="$route.path==item2.path?'is-active':''">{{item2.name}}</el-menu-item>
          </el-submenu>
        </el-menu>

      </el-aside>

      <el-main>
        <router-view></router-view>
      </el-main>

    </el-container>
  </div>
</template>
<script>
  export default {
      data() {
          return {
              /*tableData: Array(20).fill(item),*/
              openeds: ['0','1','2','3','4'],
              uniqueOpened: false
          }
      }
  }
</script>

<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
