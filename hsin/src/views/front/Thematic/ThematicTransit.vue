<template>

<!--测试页面-->
    <div>
        <span>{{articleData}}</span>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                fit: 'cover',
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                articleData: {
                    articleId: '',
                    uid:'',
                    head: '',
                    author: '',
                    describe:'',
                    content:'',
                    articlestate:'',
                    user:[]
                },
                textarea: '',
                commentForm:{},
                username:'',
                uid:'',
                usericon:''
            }
        },
        methods: {
            findArticleById() {
                const _this = this
                axios.get('http://localhost:8181/article/findArticleById?articleId=' + this.$route.query.articleId).then(function (resp) {
                    _this.articleData = resp.data
                    console.log("gridData:::::" + _this.articleData)
                })
            }
        },
        created() {
            this.findArticleById();

        }
    }
</script>

<style scoped>

</style>