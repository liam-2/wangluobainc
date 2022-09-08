<template>
  <div>


    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="ip列表">
        <el-input :rows="6"
                  placeholder="请输入ip，每一行输一个，第一个为源地址" type="textarea" v-model="form.givenIps"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认输入</el-button>
        <el-button @click="onSubmit2">清空输入</el-button>
      </el-form-item>
    </el-form>

    <!--      <div>{{this.theIpList}}</div>-->
    <div id="mapbox" style="height: 600px;width: 80%">

    </div>
  </div>

</template>

<script>
//导入vis的相关组件
import {DataSet, Network} from 'vis';

export default {
  name: "Index.vue",
  components: {DataSet, Network},
  data() {
    return {
      theIpList: [],
      theRelationList: [],
      form: {
        givenIps: ''
      }
      //imgs:[homeImg1,homeImg2,homeImg3]
    }
  },
  methods: {
    onSubmit() {
      //发送一个ajax请求
      this.$http.post("http://localhost:8989/topo/gettopo", this.form).then(res => {
        console.log(res.data);
        //theIpList接收后端传来的ip列表
        this.theIpList = res.data.ipList;
        //theRelationList接收后端传来的节点跟踪列表
        this.theRelationList = res.data.relationList;
        //console.log("输出日志");
        //console.log(this.theIpList);
        //console.log(this.theRelationList);
        //再次绘图
        this.globalTrace();

      });


    },
    onSubmit2() {
      this.form.givenIps = '';
    },
    //绘拓扑图方法
    globalTrace() {
      // create an array with nodes
      var nodes = new DataSet(this.theIpList);
      // create an array with edges
      var edges = new DataSet(this.theRelationList);
      // var edges = new DataSet([
      //   {from: 1, to: 2}
      // ]);
      // var nodes = new DataSet([
      //   {id: 1, label: "node1"},
      //   {id: 2, label: 'Node 2'},
      //   {id: 3, label: 'Node 3'},
      //   {id: 4, label: 'Node 4'},
      //   {id: 5, label: 'Node 5'}
      // ]);
      // create an array with edges
      // var edges = new DataSet([
      //   {from: 1, to: 3},
      //   {from: 1, to: 2},
      //   {from: 2, to: 4},
      //   {from: 2, to: 5}
      // ]);


      // create a network
      var container = document.getElementById('mapbox');
      // provide the data in the vis format
      var data = {
        nodes: nodes,
        edges: edges
      };
      var options = {
        //节点样式
        nodes: {
          shape: "box",//设置节点node样式为矩形
          fixed: true,//节点node固定不可移动
          font: {
            color: "white", //字体的颜色
            size: 30 //显示字体大小
          },
          // scaling: {
          //   min: 30,
          //   max: 32 //缩放效果比例
          // },
        },
        //连接线的样式
        edges: {
          color: {
            color: "rgb(97, 168, 224)",
            highlight: "rgb(97, 168, 224)",
            hover: "green",
            inherit: "from",
            opacity: 1.0
          },
          font: {
            align: "top",//连接线文字位置
          },
          smooth: false, //是否显示方向箭头
          //arrows: {to : true },//箭头指向from节点
        },
        layout: {
          //以分层方式定位节点
          hierarchical: {
            direction: "LR",//分层排序方向
            sortMethod: "directed",//分层排序方法
            levelSeparation: 400//不同级别之间的距离
          },
        },
        interaction: {
          navigationButtons: true,
          // hover: true, //鼠标移过后加粗该节点和连接线
          selectConnectedEdges: false, //选择节点后是否显示连接线
        },

      };
      // initialize your network!
      this.network = new Network(container, data, options);
      // this.network.on("click",e=> this.showDetail(e));//单击事件
      //this.network.on("doubleClick",e=> this.enterService(e))//双击事件
    },
  },
  mounted() {
    this.globalTrace()
  }


}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
