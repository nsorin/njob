<template>
  <div class="card" v-if="company">
    <h5 class="card-header">{{ company.name }}</h5>
    <div class="card-body">
      <p class="card-text" v-if="company.webSite">
        <a :href="company.webSite">{{ company.webSite }}</a>
      </p>
      <p class="card-text">
        {{ company.address }}
        <br />
        {{ company.zipCode }} {{ company.city }}
      </p>
    </div>

    <div class="card-header">Recruiters</div>
    <recruiter-list v-if="company.recruiters.length > 0" :recruiters="company.recruiters" />
    <div v-else class="card-body">
      <i>No recruiters</i>
    </div>

    <div class="card-header">Applications</div>
    <application-list v-if="company.applications.length > 0" :applications="company.applications" />
    <div v-else class="list-group-item">
      <i>No applications</i>
    </div>
  </div>
</template>

<script>
import Company from "../store/models/Company";
import RecruiterList from "./RecruiterList";
import ApplicationList from "./ApplicationList";

export default {
  name: "company-details",
  components: { ApplicationList, RecruiterList },
  props: { id: Number },
  computed: {
    company() {
      return Company.query().with(["recruiters", "applications"]).find(this.id);
    },
  },
};
</script>