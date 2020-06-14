<template>
    <div class="app">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" style="color: #696969">
            <el-menu-item index="1">
                <router-link :to="{path:'/home'}">首页</router-link>
            </el-menu-item>
            <el-menu-item index="2">
                <router-link :to="{path:'/follow',query:{uid:this.uid}}">关注</router-link>
            </el-menu-item>
            <el-submenu index="3">
                <template slot="title">消息</template>
                <el-menu-item index="3-1" style="color: #696969">
                    <router-link :to="{path:'/message',query:{uid:this.uid}}"><i class="el-icon-chat-square"></i>我收到的评论</router-link>
                </el-menu-item>
                <el-menu-item index="3-2" style="color: #696969">
                    <router-link :to="{path:'/followUser',query:{uid:this.uid}}"><i class="el-icon-user"></i>我关注的用户</router-link>
                </el-menu-item>
            </el-submenu>
            <el-menu-item index="4" class="userInfo">
                <router-link :to="{path:'/user',query:{uid:this.uid}}" class="userInfo">{{username}}个人中心</router-link>
            </el-menu-item>
                <el-button @click="edit()"  type="warning" style="float: right;margin-top: 10px;width: 150px" round>写文章</el-button>
        </el-menu>
        <el-main>
            <router-view></router-view>
        </el-main>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1',
            };

        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            edit() {
                this.$router.push({
                    path: '/articlePush',
                })
            }
        },
        created() {
            let userinfo = this.$local.get('user')

            console.log("homeabout userinfo:::::::" + userinfo)
            if (userinfo) {
                this.username = userinfo.username;
                this.uid = userinfo.uid;
                console.log("homeabout username:::::::" + this.uid)
            }

        }
    }
</script>
<style>

</style>
<style>
    .el-menu-item, .el-submenu__title{
        height: 60px;
        line-height: 60px;
        border-bottom: 2px solid transparent;
        color: #696969;
        font-size: 20px;
    }
    .el-menu--horizontal .el-menu .el-menu-item, .el-menu--horizontal .el-menu .el-submenu__title{
        height: 60px;
        line-height: 60px;
        border-bottom: 2px solid transparent;
        color: #696969;
        font-size: 20px;
        margin-left:10px;
    }
    a{
        text-decoration: none;
        height: 40px;
        line-height: 60px;
        border-bottom: 2px solid transparent;
        color: #696969;
        font-size: 16px;
    }
    .el-menu-item i{
        color: #E6A23C;
    }
    a:hover{
        color:#000 ;
    }
    .el-menu--horizontal>.el-submenu .el-submenu__title {
        height: 60px;
        line-height: 60px;
        border-bottom: 2px solid transparent;
        color: #696969;
        font-size: 17px;

    }
    .el-menu--horizontal>.el-submenu .el-submenu__title a:active{
        height: 60px;
        line-height: 60px;
        border-bottom: 2px solid transparent;
        color: #E6A23C;
        font-size: 17px;
    }
   .router-link-active{
       color: #E6A23C
   }


</style>
