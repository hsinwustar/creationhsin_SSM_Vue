<template>
    <div class="books">
        <el-table
                ref="filterTable"
                :data="tables"
                style="width: 100%">
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="文章名">
                            <span>{{ props.row.head }}</span>
                        </el-form-item>
                        <el-form-item label="用户名">
                            <span>{{ props.row.author }}</span>
                        </el-form-item>
                        <el-form-item label="文章 ID">
                            <span>{{ props.row.articleId }}</span>
                        </el-form-item>
                        <el-form-item label="用户 ID">
                            <span>{{ props.row.uid }}</span>
                        </el-form-item>
                        <el-form-item label="文章专题">
                            <span>{{ props.row.thm_name }}</span>
                        </el-form-item>
                        <el-form-item label="文章地址">
                            <span>{{ props.row.address }}</span>
                        </el-form-item>
                        <el-form-item label="文章简述">
                            <span>{{props.row.content}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
                    prop="articleId"
                    label="编号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="head"
                    label="文章名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="author"
                    label="作者"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="articlestate"
                    label="文章状态"
                    width="100"
                    :filters="[{ text: '网审合格', value: '网审合格' }, { text: '封禁', value: '封禁' },{ text: '等待网审', value: '等待网审' }]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                            effect="dark"
                            :type="scope.row.articlestate === '网审合格' ? 'primary' :( scope.row.articlestate === '封禁' ? 'danger': (scope.row.articlestate ==='等待网审' ? 'info':'success'))"
                            disable-transitions>{{scope.row.articlestate}}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    align="right">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="search"
                            size="mini"
                            placeholder="输入关键字搜索"/>
                </template>
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="edit(scope.row)">Edit
                    </el-button>

                    <el-dialog v-model="dialogFormVisible" title="修改文章信息" :visible.sync="dialogFormVisible">
                        <el-form :model="articleForm" ref="articleForm">
                            <el-form-item label="文章id">
                                <el-input v-model="articleForm.articleId"></el-input>
                            </el-form-item>
                            <el-form-item label="文章网审是否合格" prop="articlestate">
                                <template slot-scope="scope">
                                    <el-switch v-model="articleForm.articlestate" autocomplete="off"
                                               active-color="#7958b5"
                                               active-text="网审合格" active-value="网审合格"
                                               inactive-color="#e8e4f3" inactive-text="封禁"
                                               inactive-value="封禁"></el-switch>
                                </template>
                            </el-form-item>
                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogClose('articleForm')">取 消</el-button>
                            <el-button type="primary" @click="submit('articleForm')">确 定</el-button>
                        </div>
                    </el-dialog>

                    <el-button
                            size="mini"
                            type="danger"
                            @click="deleteArticle(scope.row)">Delete
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="pagination">
            <el-pagination
                    background
                    @current-change="handleCurrentChange"
                    :current-page="pageConf.pageCode"
                    layout="total, sizes, prev, pager, next, jumper"
                    :page-size="pageConf.pageSize"
                    :total="pageConf.totalPage"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import Qs from 'qs';

    export default {
        data() {
            return {
                dialogFormVisible: false,
                dataAll: [],
                // // tableDataEnd:[],
                dataEnd: '',
                search: '',
                articleForm: {
                    articlestate: '',
                    articleId: ''
                },
                tableData: [
                    {
                        articleId: '',
                        head: '',
                        uid: '',
                        author: '',
                        articlestate: '',
                        content: ''
                    }],
                /*state: {
                        articlestate:''
                    },*/
                //定义分页Config
                pageConf: {
                    //设置一些初始值(会被覆盖)
                    pageCode: 1, //当前页
                    pageSize: 10, //每页显示的记录数
                    totalPage: 1, //总记录数
                    handleCurrentChange: function () {
                        console.log("页码改变了");
                    }
                }
            }
        },
        methods: {
            filterTag(value, row) {
                return row.articlestate === value;
            },
            deleteArticle(row) {
                const _this = this
                axios.delete('http://localhost:8181/article/deleteArticleById?articleId=' + row.articleId).then(function (resp) {
                    _this.$alert('《' + row.head + '》删除成功！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            window.location.reload()
                        }
                    })
                })
            },
            edit(row) {
                // this.$route.query.row.uid;

                const _this = this
                _this.dialogFormVisible = true;
                axios.get('http://localhost:8181/article/findArticleById?articleId=' + row.articleId).then(function (resp) {
                    _this.articleForm = resp.data
                })


                // console.log(this.$refs);
            },
            submit(formname) {
                const _this = this

                this.$refs[formname].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/article/updateArticleMessage', this.articleForm).then(function (resp) {
                            console.log(resp)
                            window.location.reload()
                            _this.articleForm.show = false;
                        })
                    } else {
                        return false;
                    }
                });
            },

            // 获取所有数据
            findAll() {
                axios.post('http://localhost:8181/article/findAll').then(result => {
                    this.dataEnd = result.data;
                });
            },

            findByPage(pageCode, pageSize) {
                axios.post('http://localhost:8181/article/findByPage?pageCode=' + pageCode + '&pageSize=' + pageSize).then(result => {
                    console.log(this.pageConf.pageCode)
                    this.pageConf.totalPage = result.data.total;
                    this.tableData = result.data.rows;
                });
            },
            //当前页改变时触发的函数
            handleCurrentChange(val) {
                console.log(val)
                this.findByPage(val, this.pageConf.pageSize);
            },
        },
        computed: {
            // 模糊搜索
            tables() {
                const search = this.search
                if (search) {
                    // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                    // 注意： filter() 不会对空数组进行检测。
                    // 注意： filter() 不会改变原始数组。
                    this.dataAll = []
                    this.dataAll = this.dataEnd.filter(data1 => {
                        // some() 方法用于检测数组中的元素是否满足指定条件;
                        // some() 方法会依次执行数组的每个元素：
                        // 如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测;
                        // 如果没有满足条件的元素，则返回false。
                        // 注意： some() 不会对空数组进行检测。
                        // 注意： some() 不会改变原始数组。
                        return Object.keys(data1).some(key => {
                            // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
                            // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
                            return String(data1[key]).toLowerCase().indexOf(search) > -1
                        })
                    })
                    return this.dataAll.reverse()

                }
                return this.tableData.reverse()
            }
        },
        created() {
            this.findAll();
            this.findByPage(this.pageConf.pageCode, this.pageConf.pageSize);
        }
    }
</script>

<style>
    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>