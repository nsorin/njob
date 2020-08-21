<template>
  <div>
    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th>Name</th>
          <th>Address</th>
          <th>Zip Code</th>
          <th>City</th>
        </tr>
      </thead>
      <tbody>
        <router-link
          tag="tr"
          class="clickable"
          v-for="company in companies"
          :key="company.id"
          :to="`/companies/${company.id}`"
        >
          <td>{{ company.name }}</td>
          <td>{{ company.address }}</td>
          <td>{{ company.zipCode }}</td>
          <td>{{ company.city }}</td>
        </router-link>
      </tbody>
    </table>
  </div>
</template>

<script>
import Company from "@/store/models/Company";

export default {
  name: "company-list",
  computed: {
    companies() {
      return Company.all();
    },
  },
};
</script>

<style scoped>
tr.clickable:hover {
  cursor: pointer;
}
</style>