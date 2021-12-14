<template>
<button @click='displayComponent'>
  <a class="float">
    <i class="fa fa-plus my-float"></i>
  </a>
</button>

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
  bottom:50px;
  right:50px;
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