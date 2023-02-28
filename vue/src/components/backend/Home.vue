<template>
  <div class="backendHome">
    <el-container style="height: 745px; border: 1px solid #eee">
      <div style="margin-left: 0%; flex: 1; font-size: 18px">
        <el-aside width="sideWidth + 'px'" style="background-color: rgb(230, 236, 246); height: 100%">

          <el-menu :default-openeds="['1', '3']" style="height: 100%; overflow-x: hidden"
                   background-color="rgb(48, 65, 86)" text-color="#fff" :collapse-transition="false"
                   :collapse="isCollapse">

            <div class="companyIcon" style="height: 60px; line-height: 60px; text-align: center">
              <img src="../../assets/logo.png"
                   style="width: 20px; position: relative; top: 5px; margin-right: 5px">
              <b style="color: white" v-show="!isCollapse">汇率管理系统</b>
            </div>
            <el-submenu index="1">
              <template slot="title"><i class="el-icon-s-home"></i>
                <span>主导航</span>
              </template>
              <el-menu-item-group>
                <template slot="title">数据管理</template>
                <el-menu-item index="1-1">汇率数据表</el-menu-item>
              </el-menu-item-group>
              <!--               <el-menu-item-group title="分组2">
                 <el-menu-item index="1-3">选项3</el-menu-item>
               </el-menu-item-group>
               <el-submenu index="1-4">
                 <template slot="title">选项4</template>
                 <el-menu-item index="1-4-1">选项4-1</el-menu-item> -->
            </el-submenu>

            <el-submenu index="2">
              <template slot="title"><i class="el-icon-menu"></i>
                <span>待开发</span>
              </template>
              <el-menu-item-group>
                <template slot="title">待开发</template>
                <el-menu-item index="2-1">待开发</el-menu-item>
                <el-menu-item index="2-2">？？？</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="分组2">
                <el-menu-item index="2-3">待开发</el-menu-item>
              </el-menu-item-group>
              <el-submenu index="2-4">
                <template slot="title">待开发</template>
                <el-menu-item index="2-4-1">待开发</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-setting"></i>
                <span>设置</span>
              </template>
              <el-menu-item-group>
                <template slot="title">用户管理</template>
                <el-menu-item index="3-1">新增用户</el-menu-item>
                <el-menu-item index="3-2">删除用户</el-menu-item>
              </el-menu-item-group>
            </el-submenu>

          </el-menu>
        </el-aside>
      </div>


      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <div style="margin-left: 0%; flex: 1; font-size: 18px">
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
          </div>
          <el-dropdown @command="exit">
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>HENRY</span>
        </el-header>

        <el-main>
          <!--新增数据表格框-->
          <el-dialog title="新增数据" :visible.sync="dialogFormVisible">
            <el-form :model="form" label-width="120">
              <el-form-item label="id">
                <el-input v-model="form.id" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="日期">
                <el-input v-model="form.datetime" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="上行">
                <el-input v-model="form.hkdtocny" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="下行">
                <el-input v-model="form.cnytohkd" autocomplete="off"></el-input>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="addDataok">确 定</el-button>
            </div>
          </el-dialog>

          <div class="pageHead">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item style="font-size:25px;">汇率数据表</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div class="searchBlock">
            <el-input v-model="inputup" placeholder="请输入要搜索的上行表数据" clearable style="padding:10px 0; width: 200px;"/>
            <el-input v-model="inputdown" placeholder="请输入要搜索的下行表数据" clearable style="padding:10px 0; width: 200px;"/>
            <el-button @click=load type="primary">搜索</el-button>
          </div>
          <div>
            <el-button type="primary" @click="addData">新增<i class="el-icon-circle-plus-outline"/></el-button>
          </div>
          <el-table :data="tableData" :default-sort="{prop: 'date', order: 'descending'}">
            <el-table-column prop="id" label="ID" width="50">
            </el-table-column>
            <el-table-column sortable prop="datetime" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="hkdtocny" label="上行" width="120">
            </el-table-column>
            <el-table-column prop="cnytohkd" label="下行" width="700">
            </el-table-column>

            <!--表格内数据操作-->

            <el-table-column label="操作">
              <template v-slot="scope">
                <el-button type="success" @click="edit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
                <el-button type="danger" @click="deletedata(scope.row.id)">删除 <i class="el-icon-remove-outline"></i></el-button>
              </template>
            </el-table-column>
            <!-- 操作栏 -->
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination :page-sizes="[5, 10, 15, 20]" :page-size="pageSize"
                           layout="total, sizes, prev, pager, next, jumper" :total=total>
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {

    return {
      tableData: [],
      total: 5,
      pageNum: 1,
      pageSize: 5,
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      id:'',
      inputup: '',
      inputdown: '',
      dialogFormVisible: false,
      form: {},

    }
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
      } else {
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
      }
    },
    // 导航栏伸缩

    load() {
      var res = request.get("/ERData/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          hkdtocny: this.inputup,
          cnytohkd: this.inputdown
          //上行下行数据搜索框传参

        }
      })
          .then(res => {
            this.tableData = res.records
            this.total = res.total
          })

    },
    addData() {
      this.dialogFormVisible = true;
      this.form = {}
    //新增数据事件
    },
    addDataok() {
      //确定新增按钮事件
      request.post("/ERData",this.form).then(
          res=>{
            // console.log(res)
            if(this.form.date||this.form.down||this.form.up==null)
              this.$message.error("请检查格式是否正确")

            if(res)
            {
              this.$message.success("新增成功")
              this.load();
            }
            else{
              this.$message({
              type:"error",
              message:"新增失败，请排查原因",

            })
              }
          }

      )

      this.dialogFormVisible=false;
    },
    edit(row)
    {
      this.form=row
      this.dialogFormVisible=true
    //  编辑按钮
    },

    deletedata(id)
    {
      this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        request.delete("/ERData/"+id).then(res=>
        {
          if(res)
          {
            this.$message.success("删除成功")
            this.load()
          }
          else{
            this.$message({
              type:"error",
              message:"删除失败，请排查原因",

            })

          }
        })
      }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'})
      })

    },
    exit(){
      this.$router.push("/")
    }


  },

  mounted() {
    this.load();
  }
}

</script>

<style scoped>

</style>
