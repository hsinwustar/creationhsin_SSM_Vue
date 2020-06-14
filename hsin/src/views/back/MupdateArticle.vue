<template>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm">

        <el-form-item label="图书编号">
            <el-input v-model="ruleForm.articleId" readOnly></el-input>
        </el-form-item>

        <el-form-item label="图书名称" prop="name">
            <el-input v-model="ruleForm.head"></el-input>
        </el-form-item>

        <el-form-item label="作者" prop="author">
            <el-input v-model="ruleForm.author"></el-input>
        </el-form-item>

        <el-form-item label="校验是否通过">
            <template>
                <el-radio v-model="ruleForm.articlestate" label="封禁">封禁</el-radio>
                <el-radio v-model="ruleForm.articlestate" label="网审合格">网审合格</el-radio>
            </template>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>

    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
                    articleId: '',
                    head: '',
                    author: '',
                    articlestate: ''
                },
                rules: {
                    head: [
                        {required: true, message: '图书名称不能为空', trigger: 'blur'}
                    ],
                    author: [
                        {required: true, message: '简介不能为空', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/article/updateArticleMessage', this.ruleForm).then(function (resp) {
                            if (resp) {
                                _this.$alert('《' + _this.ruleForm.head + '》修改成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/marticle')
                                    }
                                })
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const _this = this
            axios.get('http://localhost:8181/article/findArticleById?articleId=' + this.$route.query.articleId).then(function (resp) {
                _this.ruleForm = resp.data
            })
        }
    }
</script>

<style scoped>

</style>