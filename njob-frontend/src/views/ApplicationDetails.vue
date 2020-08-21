<template>
  <div class="row">
    <div class="col-sm-8 offset-sm-2">
      <div class="card" v-if="application">
        <h5 class="card-header">{{ application.name }}</h5>
        <div class="card-body">
          <router-link
            v-if="application.company"
            :to="`/companies/${application.company_id}`"
          >{{ application.company.name }}</router-link>
          <p class="card-text">
            <span v-for="(filled, index) in stars" :key="index">
              <b-icon-star-fill v-if="filled" />
              <b-icon-star v-else />
            </span>
          </p>
          <p class="card-text">{{ application.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Application from "../store/models/Application";
import { BIconStarFill, BIconStar } from "bootstrap-vue";

const MAX_STARS = 5;

export default {
  name: "application-details",
  components: { BIconStarFill, BIconStar },
  props: { id: Number },
  computed: {
    application() {
      return Application.query().with("company").find(this.id);
    },
    stars() {
      let stars = [];
      for (let i = 1; i <= MAX_STARS; i++) {
        stars.push(this.application.priority >= i);
      }
      return stars;
    },
  },
};
</script>