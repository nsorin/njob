<template>
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
    <div class="card-header">Documents</div>
    <document-list v-if="application.documents.length > 0" :documents="application.documents"></document-list>
    <div v-else class="list-group-item">
      <i>No documents</i>
    </div>
  </div>
</template>

<script>
import Application from "../store/models/Application";
import { BIconStarFill, BIconStar } from "bootstrap-vue";
import DocumentList from "../components/DocumentList"

const MAX_STARS = 5;

export default {
  name: "application-details",
  components: { BIconStarFill, BIconStar, DocumentList },
  props: { id: Number },
  computed: {
    application() {
      return Application.query().with(["company", "documents"]).find(this.id);
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