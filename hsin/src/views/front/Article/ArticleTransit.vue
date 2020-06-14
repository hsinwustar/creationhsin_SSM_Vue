<template>
    <div>
        <div style="font-family: 'Microsoft YaHei';font-size: 18px;font-weight: bolder;margin-left: 600px;margin-top: 300px"><i class="el-icon-success" style="color: olivedrab"></i>发布成功，即将跳转跳转~</div>
    </div>
</template>

<script>
    export default {
        name: "ArticleTransit",
        data(){
            return{
                uid:'',
                count:''
            }
        },
        methods:{

        },
        created(){
            const timejump = 20;
    if(!this.timer){
        this.count = timejump ;
        this.show = false;
        this.timer = setInterval(()=>{
            if(this.count > 0 && this.count <= timejump ){
                this.count--;
            }else{
                this.show = true;
                clearInterval(this.timer);
                this.timer = null;
                //跳转的页面写在此处
                this.$router.push({path:'/home'});
            }
        },100)
    }
        }
    }
</script>

<style scoped>

</style>