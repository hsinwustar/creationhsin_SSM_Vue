<template>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="图书名称" prop="head">
            <el-input v-model="ruleForm.head"></el-input>
        </el-form-item>

        <el-form-item label="作者" prop="author">
            <el-input v-model="ruleForm.author"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>

    </el-form>
</template>

<script>
    export default {
        data(){
            return{
                ruleForm:{
                    head:'',
                    author:''
                },
                rules:{
                    head:[
                        {required:true,message:'请输入图书名称',trigger:'blur'}
                    ],
                    author:[
                        {required:true,message:'请输入作者名称',trigger:'blur'}
                    ]
                }
            };
        },
        methods:{
            submitForm(formName){
                const _this = this
                this.$refs[formName].validate((valid) =>{
                    console.log(formName)
                    if(valid){
                        axios.post('http://localhost:8181/article/addArticle',this.ruleForm).then(function(resp) {
                            console.log(resp)
                           if (resp) {
                                _this.$alert('《' + _this.ruleForm.head + '》添加成功', 'this is ok', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/marticle')
                                    }
                                })
                            }

                            /*console.log(this.ruleForm)*/

                        })
                        /*console.log(prame);*/
                        /*console.log(resp);*/
                    }else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>

</style>