<template>
  <ul class="list-group list-group-flush">
    <li v-for="document in documents" :key="document.id" class="list-group-item">
      <b-button @click="$download.get(document.fileKey, document.fileName)">{{ document.name }}</b-button>
    </li>
  </ul>
</template>

<script>
import {BButton} from 'bootstrap-vue'

export default {
  name: "document-list",
  props: { documents: Array },
  components: {BButton},
  methods: {
    downloadDocument(document) {
      this.$download(document.fileKey, document.fileName)
    }
  }
};
</script>