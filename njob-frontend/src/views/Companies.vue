<template>
  <div>
    <div class="row">
      <div class="col-sm-8 offset-sm-2">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import Company from "@/store/models/Company";

export default {
  name: "companies",
  mounted() {
    Company.api().fetch();
  },
};
</script>
