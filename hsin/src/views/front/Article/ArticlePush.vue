<template>
    <!--<div>-->
    <!--<h2>这是用户发表新文章文章页面</h2>-->
    <!--<router-link to="/home">回首页</router-link>-->
    <!--</div>-->
    <div class="about">
        <el-form style="width: 90%" :model="articlePushForm" :rules="rules" ref="articlePushForm" label-width="100px"
                 class="demo-ruleForm">
            <el-form-item label="用户id" hidden="true">
                <el-input v-model="articlePushForm.uid=this.uid"></el-input>
            </el-form-item>
            <el-form-item label="标题">
                <el-input v-model="articlePushForm.head"></el-input>
            </el-form-item>
            <el-form-item label="作者" hidden="true">
                <el-input v-model="articlePushForm.author=this.username"></el-input>
            </el-form-item>
            <el-form-item>
                <quill-editor
                        v-model="articlePushForm.content"
                        ref="myQuillEditor"
                        :options="articlePushForm.editorOption"
                        @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
                        @change="onEditorChange($event)">
                </quill-editor>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="submitForm('articlePushForm')">发布文章</el-button>
                <el-button @click="resetForm('articlePushForm')">取消发布</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {quillEditor} from 'vue-quill-editor'
    import 'quill/dist/quill.core.css'
    import 'quill/dist/quill.snow.css'
    import 'quill/dist/quill.bubble.css'

    export default {
        name: 'about',

        data() {
            return {
                describe: '',
                articlePushForm: {
                    uid: '',
                    head: '',
                    author: '',
                    content: null,
                    describe: '',
                    editorOption: {}
                },
                articleId:'',
                rules: {
                    head: [
                        {required: true, message: '标题不能为空', trigger: 'blur'}
                    ],
                    content: [
                        {required: true, message: '内容不能为空', trigger: 'blur'}
                    ]
                }

            }
        },
        components: {
            quillEditor
        },
        methods: {
            onEditorReady(editor) {
                // 准备编辑器
                console.log('111')
            },
            onEditorBlur() {
                // 失去焦点事件
                console.log('111')
            },
            onEditorFocus() {
                // 获得焦点事件
                console.log('222')
            },
            onEditorChange() {
                // 内容改变事件
                console.log('333')
            },

            submitForm(formName) {
                const _this = this
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/article/addArticle', this.articlePushForm).then(function (resp) {
                            console.log("data!!!!!!!!!!!!!!!!!!"+resp.data)
                            _this.articleId = resp.data.articleId
                            console.log("articleId!!!!!!!!!!!!!!!!!!"+_this.articleId)
                            if (resp) {
                                _this.$alert('《' + _this.articlePushForm.head + '》添加成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push({
                                            path: '/articleTransit',
                                            query: {
                                                uid: _this.uid
                                            }
                                        })
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
            let userinfo = this.$local.get('user')
            console.log("thematic::::::" + userinfo)
            if (userinfo) {
                this.uid = userinfo.uid;
                this.username = userinfo.username;
            }
        }

    }

</script>

<style scoped>

</style>