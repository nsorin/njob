<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
import HelloWorld from '@/components/HelloWorld.vue'
import Company from '@/store/models/Company'

export default {
  name: 'Home',
  components: {
    HelloWorld
  },
  mounted() {
    Company.api().fetch()
  }
}
</script>
