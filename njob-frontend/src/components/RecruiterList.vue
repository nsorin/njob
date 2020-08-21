<template>
  <ul class="list-group list-group-flush">
    <li class="list-group-item" v-for="recruiter in recruiters" :key="recruiter.id">
      {{ recruiter.firstName }} {{ recruiter.lastName }}
      <br />
      <a :href="`mailto:${recruiter.email}`">{{ recruiter.email }}</a>
      <br />
      <a class="btn btn-info" :href="`tel:${recruiter.phone}`">
        <b-icon-telephone-fill />
        {{ recruiter.phone }}
      </a>
    </li>
  </ul>
</template>

<script>
import { BIconTelephoneFill } from "bootstrap-vue";

export default {
  name: "recruiter-list",
  components: { BIconTelephoneFill },
  props: { recruiters: Array },
};
</script>