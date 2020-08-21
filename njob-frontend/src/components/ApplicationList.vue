<template>
  <div class="list-group list-group-flush">
    <router-link 
    v-for="application in applications"
    :key="application.id"
    tag="button"
    class="list-group-item list-group-item-action"
    :to="`applications/${application.id}`">
      {{ application.name }}
    </router-link>
  </div>
</template>

<script>

export default {
  name: "application-list",
  props: { applications: Array },
};
</script>