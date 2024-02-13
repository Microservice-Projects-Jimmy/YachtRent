<template>
  <q-page class="row items-center justify-evenly text-h3" padding>
    This is yachts Page
  </q-page>
</template>

<script setup lang="ts">
import { useAppStore } from 'src/stores/app-store';
import { onMounted } from 'vue';

const store = useAppStore();

onMounted(() => {
  store.setPage('Yachts');
});
</script>
