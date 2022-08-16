<template>

    <div>
        <!--    功能区-->
        <div class="flex" style="margin: 5px ">
                <el-button type="primary" style="margin-left: 2px" @click="add">新增</el-button>
                <el-button type="primary">导入</el-button>
                <el-button type="primary">导出</el-button>
                <el-upload action="http://localhost:9090/files/upload" style="display: inline; float: right; margin-right: 20px" >
                    <el-button type="success">上传</el-button>
                </el-upload>
            <el-button  color="#626aef" style="float: right;margin-right: 20px;" @click="downloadFiles">下载</el-button>
        </div>
        <div style="margin: 5px">
            <el-input v-model="searchKey" placeholder="请输入内容" style="width: 20%" clearable/>
            <el-button @click="search">
                <el-icon>
                    <Search/>
                </el-icon>
            </el-button>
        </div>
        <!--    显示区-->
        <el-table :data="tableData" stripe border style="width: 100%">
            <el-table-column prop="id" label="ID" sortable/>
            <el-table-column prop="username" label="用户名"/>
            <el-table-column prop="nickname" label="昵称"/>
            <el-table-column prop="age" label="年龄"/>
            <el-table-column prop="sex" label="性别"/>
            <el-table-column prop="address" label="住址"/>

            <el-table-column fixed="right" label="操作" width="120">
                <template #default="scope">
                    <el-button type="warning" size="small" @click="edit(scope.row)">
                        <el-icon>
                            <Edit/>
                        </el-icon>
                    </el-button>
                    <el-popconfirm title="确认删除吗？"
                                   :icon="InfoFilled"
                                   icon-color="#626AEF"
                                   @confirm="remove(scope.row.id)">
                        <template #reference>
                            <el-button type="danger" size="small">
                                <el-icon>
                                    <Delete/>
                                </el-icon>
                            </el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>

        </el-table>

        <div style="padding-top: 10px">
            <el-pagination
                    v-model:currentPage="pageNum"
                    v-model:page-size="pageSize"
                    :page-sizes="[10, 20, 50]"
                    :disabled="disabled"
                    :background="background"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            />

            <el-dialog v-model="dialogVisible"  width="30%">
                <el-form v-model="form" label-width="120px">
                    <el-form-item label="用户名">
                        <el-input v-model="form.username" style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="昵称">
                        <el-input v-model="form.nickname" style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="年龄">
                        <el-input v-model="form.age" style="width: 80%"/>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio-group v-model="form.sex">
                            <el-radio label="男"/>
                            <el-radio label="女"/>
                        </el-radio-group>
                    </el-form-item>
                    <el-form-item label="住址">
                        <el-input type="textarea" v-model="form.address" style="width: 80%"/>
                    </el-form-item>
                </el-form>
                <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="save">提交</el-button>
              </span>
                </template>
            </el-dialog>

            <el-dialog v-model="downloadDialog">
                <li v-for="item in fileList">
                    <a :href="'http://localhost:9090/files/'+item">{{item}}</a>
                    <el-image
                            style="width: 20px; height: 20px; margin-left: 4px"
                            :src="'http://localhost:9090/files/'+item"
                            fit="cover"
                    />
                </li>
            </el-dialog>

        </div>

    </div>
</template>

<script>


    import request from "@/utils/request";

    export default {
        name: 'HomeView',
        components: {},
        data() {
            return {
                form: {},
                dialogVisible: false,
                downloadDialog:false,
                input: '',
                tableData: [],
                pageNum: 1,
                pageSize: 10,
                searchKey: '',
                total: 0,
                fileList:[]

            }
        },
        created() {
            this.load()
        },
        methods: {
            load() {
                request.get("/user", {
                    params:{
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        searchKey: this.searchKey
                    }
                }).then(res => {
                    this.total=res.data.total;
                    this.tableData=res.data.records;

                })
            },

            search() {
                this.load()
            },

            add() {
                this.dialogVisible = true
                this.form = {}
            },
            save() {

                if (this.form.id){
                    request.put("/user", this.form).then(res => {
                        if (res.code==='0'){
                            this.$message({
                                type:"success",
                                message:"更新成功"
                            })
                        }else {
                            this.$message({
                                type:"error",
                                message:res.msg
                            })
                        }
                    })
                    this.load()
                }else {
                    request.post("/user", this.form).then(res => {
                        if (res.code==='0'){
                            this.$message({
                                type:"success",
                                message:"添加成功"
                            })
                        }else {
                            this.$message({
                                type:"error",
                                message:"添加失败"
                            })
                        }
                    })
                    this.load()
                }
                this.dialogVisible = false
                this.form = {}
            },
            edit(row) {
                this.form=JSON.parse(JSON.stringify(row))
                this.dialogVisible=true

            },
            remove(id) {
                request.delete("/user/"+id).then(res=>{
                    if (res.code==='0'){
                        this.$message({
                            type:"success",
                            message:"删除成功"
                        })
                    }else {
                        this.$message({
                            type:"error",
                            message:"删除失败"
                        })
                    }
                })
                this.load()
            },
            handleCurrentChange(){
                this.load()
            },
            handleSizeChange(){
                this.load()
            },
            downloadFiles(){
                this.downloadDialog=true
                request.get("/files/allFlags").then(res=>{
                    this.fileList=res.data
                })
            }

        }
    }
</script>
