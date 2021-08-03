<template>
  <el-container class="home_container">
    <el-container>
      <!-- 主题区域 -->
      <el-main>
          <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  setup() {},
};
</script>

<style>
.home_container {
  height: 100%;
}

.el-header {
  background-color: royalblue;
  color: #333;
  text-align: center;
  text-anchor: middle;
  font-size: 30px;
}

.el-aside {
  background-color: royalblue;
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #f3e9ee;
  color: #333;
  text-align: center;
}
</style>