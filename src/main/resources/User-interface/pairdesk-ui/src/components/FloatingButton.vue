<template>
  <a style="padding:10px;display:block;" href="http://androidcss.com/css/css3-floating-button-tutorial/" target="_blank">Click here for complete tutorial</a>

  <!-- Code begins here -->

  <a href="#" class="float">
    <i class="fa fa-plus my-float"></i>
  </a>
</template>

<script>
export default {
  name: "FloatingButton"
}
</script>

<style scoped>
*{padding:0;margin:0;}

body{
  font-family:Verdana, Geneva, sans-serif;
  font-size:18px;
  background-color:#CCC;
}

.float{
  position:fixed;
  width:60px;
  height:60px;
  bottom:40px;
  right:40px;
  background-color:#0C9;
  color:#FFF;
  border-radius:50px;
  text-align:center;
  box-shadow: 2px 2px 3px #999;
}

.my-float{
  margin-top:22px;
}
</style>