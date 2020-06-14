<template>
    <div>
        <!--<el-button @click="resetDateFilter">清除日期过滤器</el-button>-->
        <!--<el-button @click="clearFilter">清除所有过滤器</el-button>-->
        <el-table
                ref="filterTable"
                :data="tables"
                style="width: 100%">
            <el-table-column
                    prop="uid"
                    label="id"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="userstatus"
                    label="用户状态"
                    width="100"
                    :filters="[{ text: '正常使用', value: '正常使用' }, { text: '封禁中', value: '封禁中' }]"
                    :filter-method="filterStatus"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                            effect="dark"
                            :type="scope.row.userstatus === '正常使用' ? 'success' : 'danger'"
                            disable-transitions>{{scope.row.userstatus}}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="姓名"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="useremail"
                    label="邮箱"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="useraddress"
                    label="地址"
                    :formatter="formatter">
            </el-table-column>
            <el-table-column
                    prop="usertype"
                    label="权限"
                    width="100"
                    :filters="[{ text: '普通用户', value: '普通用户' }, { text: '超级管理员', value: '超级管理员' },{ text: '2', value: '2' }]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                            effect="plain"
                            :type="scope.row.usertype === '普通用户' ? 'primary' :( scope.row.usertype === '超级管理员' ? 'danger': (scope.row.usertype ==='2' ? 'primary':'success'))"
                            disable-transitions>{{scope.row.usertype}}
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

                    <el-dialog v-model="dialogFormVisible" title="修改用户信息" :visible.sync="dialogFormVisible">
                        <el-form :model="userForm" :rules="rules" ref="userForm">
                            <el-form-item label="用户id" hidden="true">
                                <el-input v-model="userForm.uid"></el-input>
                            </el-form-item>
                            <el-form-item label="用户">
                                <el-input v-model="userForm.username" readOnly>{{tableData.username}}</el-input>
                            </el-form-item>
                            <el-form-item label="账号是否封禁（ON封禁，OFF解封）" :label-width="300" prop="userstatus">
                                <template slot-scope="scope">
                                    <el-switch v-model="userForm.userstatus" autocomplete="off" active-color="#7958b5"
                                               active-text="封禁中" active-value="封禁中"
                                               inactive-color="#e8e4f3" inactive-text="正常使用"
                                               inactive-value="正常使用"></el-switch>
                                </template>
                            </el-form-item>
                            <el-form-item label="人员权限" :label-width="formLabelWidth" prop="usertype">
                                <el-radio-group v-model="userForm.usertype" autocomplete="off">
                                    <el-radio label="普通用户"></el-radio>
                                    <el-radio label="超级管理员"></el-radio>
                                </el-radio-group>
                            </el-form-item>

                        </el-form>
                        <div slot="footer" class="dialog-footer">
                            <el-button @click="dialogClose('userForm')">取 消</el-button>
                            <el-button type="primary" @click="submit('userForm')">确 定</el-button>
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
        <!--分页处理-->
        <div class="pagination">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageConf.pageCode"
                    layout="total, sizes, prev, pager, next, jumper"
                    :page-sizes="pageConf.pageOption"
                    :page-size="pageConf.pageSize"
                    :total="pageConf.totalPage"
            >
            </el-pagination>
        </div>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                dialogFormVisible: false,
                dataAll: [],
                dataEnd: '',
                // flag:false,
                tableData: [{
                    uid: '1',
                    username: '李明',
                    useremail: 'liming@163.com',
                    useraddress: '上海市普陀区金沙江路 1518 弄',
                    usertype: '家',
                    userstatus: '正常使用',
                }],
                search: '',
                pageConf: {
                    //设置一些初始值(会被覆盖)
                    pageCode: 1, //当前页
                    pageSize: 4, //每页显示的记录数
                    totalPage: 12, //总记录数
                    pageOption: [4, 10, 20], //分页选项
                    handleCurrentChange: function () {
                        console.log("页码改变了");
                    }
                },
                userForm: {
                    uid: '',
                    userstatus: '',
                    usertype: ''
                },
                formLabelWidth: '120px',
                rules: {
                    userstatus: [
                        {required: true, message: '状态不能为空', trigger: 'blur'}
                    ],
                    usertype: [
                        {required: true, message: '权限不能为空', trigger: 'blur'}
                    ]
                }

            }
        },

        methods: {
            formatter(row, column) {
                return row.useraddress;
            },
            filterTag(value, row) {
                return row.usertype === value;
            },
            filterStatus(value, row) {
                return row.userstatus === value;
            },
            /*分页处理*/
            //pageSize改变时触发的函数
            handleSizeChange(val) {
                console.log(val)
                this.findByPage(this.pageConf.pageCode, val);
            },
            //当前页改变时触发的函数
            handleCurrentChange(val) {
                console.log(val)
                this.findByPage(val, this.pageConf.pageSize);
            },
            /*获取用户信息*/
            // 获取所有数据
            findAll() {
                axios.post('http://localhost:8181/user/findAll').then(result => {
                    //this.tableData = result.data;
                    this.dataEnd = result.data;

                });
            },
            findByPage(pageCode, pageSize) {
                axios.post('http://localhost:8181/user/findByPage?pageCode=' + pageCode + '&pageSize=' + pageSize).then(result => {
                    // console.log(this.pageConf.pageCode)
                    this.pageConf.totalPage = result.data.total;
                    this.tableData = result.data.rows;
                    console.log("tabledata::::::" + this.tableData)

                });
            },
            deleteArticle(row) {
                const _this = this
                axios.delete('http://localhost:8181/user/deleteUserById?uid=' + row.uid).then(function (resp) {
                    _this.$alert('用户' + row.username + '(' + row.useremail + ')删除成功！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            window.location.reload()
                        }
                    })
                })
            },
            edit(row) {
                const _this = this
                _this.dialogFormVisible = true;
                axios.get('http://localhost:8181/user/findUserById?uid=' + row.uid).then(function (resp) {
                    console.log("edit点击后获取的uid::::::" + row.uid)
                    console.log("edit点击后获取的resp::::::" + resp)
                    _this.userForm = resp.data
                    console.log("edit里的userForm:::::::" + _this.userForm)
                })
            },
            dialogClose(userForm) {
                const _this = this
                _this.dialogFormVisible = false; //dialog关闭
                this.$refs.userForm.resetFields(); //表单重置
            },
            submit(formname) {
                const _this = this
                this.$refs[formname].validate((valid) => {
                    console.log("submit点击后获取的valid::::::" + valid)
                    console.log("submit点击后获取的formname::::::" + valid)
                    if (valid) {
                        axios.put('http://localhost:8181/user/updateUserMessage', this.userForm).then(function (resp) {
                            console.log(resp)
                            window.location.reload()
                            _this.userForm.show = false;
                        })
                    } else {
                        return false;
                    }
                });

            },


        },
        computed: {
            // 模糊搜索
            tables() {
                const search = this.search
                if (search) {
                    this.dataAll = []
                    this.dataAll = this.dataEnd.filter(data => {
                        return Object.keys(data).some(key => {
                            return String(data[key]).toLowerCase().indexOf(search) > -1
                        })
                    })
                    return this.dataAll
                }
                return this.tableData
            }
        },
        created() {
            this.findAll();
            this.findByPage(this.pageConf.pageCode, this.pageConf.pageSize);
        }
    }
</script>
