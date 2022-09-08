<template>
  <div id="app">
    <el-container>

      <el-main>

        <router-view/>

      </el-main>
      <el-footer align="center">
        <el-row>
          <el-col :span="2"><div class="grid-content bg-purple-light"><el-tag type="success">网络拓扑自动构造</el-tag></div></el-col>

      </el-row>
      </el-footer>
    </el-container>

  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      // 刷新到当前路由
      activeIndex: this.$router.path,
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key).catch(err=>{});
    }
  }
}
</script>

<style>

</style>
