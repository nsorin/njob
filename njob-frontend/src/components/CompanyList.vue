<template>
  <div>
    <table class="table table-striped">
      <thead>
        <tr>
          <th>Name</th>
          <th>Address</th>
          <th>Zip Code</th>
          <th>City</th>
        </tr>
      </thead>
      <tbody>
          <tr v-for="company in companies" :key="company.id">
            <td>{{ company.name }}</td>
            <td>{{ company.address }}</td>
            <td>{{ company.zipCode }}</td>
            <td>{{ company.city }}</td>
          </tr>
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
          return Company.all()
      }
  }
};
</script>

<style scoped>
</style>