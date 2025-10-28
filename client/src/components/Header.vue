<script setup>
defineProps({
  header_name: {type: String, required: true}
})
</script>

<template>
  <h2 class="text-2xl font-bold">{{ header_name }}</h2>
</template>

<style scoped></style>