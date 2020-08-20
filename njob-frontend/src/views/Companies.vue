<template>
  <div>
    <div class="row">
      <div class="col-sm-12">
        <h1>Companies</h1>
      </div>
    </div>
    <div class="row">
      <div class="col-sm-8 offset-sm-2">
        <company-list />
      </div>
    </div>
  </div>
</template>

<script>
import CompanyList from "@/components/CompanyList.vue";
import Company from "@/store/models/Company";

export default {
  name: "companies",
  components: {
    CompanyList,
  },
  mounted() {
    Company.api().fetch();
  },
};
</script>
