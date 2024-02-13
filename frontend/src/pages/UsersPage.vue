<template>
  <q-page class="row items-center justify-evenly text-h3" padding>
    This is users Page
  </q-page>
</template>

<script setup lang="ts">
import { useAppStore } from 'src/stores/app-store';
import { onMounted } from 'vue';

const store = useAppStore();

onMounted(() => {
  store.setPage('Users');
});
</script>
