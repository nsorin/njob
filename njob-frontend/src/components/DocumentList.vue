<template>
  <ul class="list-group list-group-flush">
    <li v-for="document in documents" :key="document.id" class="list-group-item">
      <a :href="$download(document.fileKey)" :download="document.fileName">{{ document.name }}</a>
    </li>
  </ul>
</template>

<script>
export default {
  name: "document-list",
  props: { documents: Array },
};
</script>