<template>
	<el-container>
		<el-header>
			<el-menu  style="width: 100% ;height: 100%"  router :default-active="this.$router.path" class="el-menu-zh" mode="horizontal"
				@select="handleSelect" background-color="#efc32c" text-color="#45536c" active-text-color="#ffd04b">

				<el-menu-item class="loginButton" index="/login" >后台登录</el-menu-item>
			</el-menu>
		</el-header>
		
		<el-main>
			<Time class="time"/>
      <div class="mainTable">
        <el-table :border="true" :data="tableData"  highlight-current-row
        max-height="500px" :header-cell-style="tableHeaderColor"
                     :default-sort="{prop: 'date', order: 'descending'}">
        <el-table-column  prop="datetime" label="日期" sortable width="100">
        </el-table-column>
        <el-table-column prop="cnytohkd" label="下行" width="100" >
        </el-table-column>
        <el-table-column prop="hkdtocny" label="上行" width="100">
        </el-table-column>
        
      </el-table>
      </div>
		</el-main>
	</el-container>
</template>

<script>
	import axios from 'axios'
	import VueAxios from 'vue-axios'
  import time from './Time.vue'
  import request from "@/utils/request";
	export default {

		name: 'ZHTable',

		data() {
			return {
				tableData: [],
        total:5,
        pageNum:1,
        pageSize:15,
			}
		},
		methods: {

			handleSelect(key, keyPath) {
				console.log(key, keyPath);
			},
      tableHeaderColor({ row, column, rowIndex, columnIndex }) {
        if (rowIndex === 0) {
          return 'background-color: #F0F0F1;color:black;font-size:18px;font-weight: 900;'
        }
      },
      load(){
        var res = request.get("/ERData/page" ,
        { 
            params:
            {
              pageNum:this.pageNum,
              pageSize:this.pageSize,

            }

        })
            .then(res => {

              this.tableData=res.records
              this.total=res.total
            })

      }
		},
    created() {
      this.load();
    },
    components:
		{
			Time:time
		},
	}
</script>

<style scoped>
	.el-container {
		height: 100%;

	}
	.el-main{
		background-color:#45536c
	}

	.el-header{
		background-color: #45536c;
		color: #333;
		line-height: 60px;

	}

	.el-table {
		top: 50px;
    padding: 5px;
    background-color: #efc32c;
    width:max-content;
    height:100%
  }

  .time{color: darkkhaki}

@media screen and (max-width: 500px) {
  .el-table {
    left: 2%;
    margin: auto;
  }

  }
  @media screen and (min-width:500px) {
    .el-table {
      margin: auto;
      left: 3%;
    }
  }



</style>
