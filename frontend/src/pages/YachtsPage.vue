<template>
  <q-page class="row items-center justify-evenly text-h3 bg-red-2" padding>
    <q-card
      class="my-card q-my-sm"
      flat
      bordered
      v-for="yacht in yachts"
      :key="yacht.name"
    >
      <q-img :src="yacht.imagePath" />

      <q-card-section>
        <q-btn
          fab
          color="primary"
          icon="place"
          class="absolute"
          style="top: 0; right: 12px; transform: translateY(-50%)"
        />

        <div class="row no-wrap items-center">
          <div class="col text-h6 ellipsis">{{ yacht.name }}</div>
          <div
            class="col-auto text-grey text-caption q-pt-md row no-wrap items-center"
          >
            <q-icon name="place" />
            250 ft
          </div>
        </div>

        <q-rating v-model="stars" :max="5" size="32px" />
      </q-card-section>

      <q-card-section class="q-pt-none">
        <div class="text-subtitle1">Yacht price ${{ yacht.price }}</div>
        <div class="text-caption text-grey">
          {{ yacht.description }}
        </div>
      </q-card-section>

      <q-separator />

      <q-card-actions>
        <q-btn flat round icon="event" />
        <q-btn flat color="primary"> Reserve </q-btn>
        <q-space />
        <q-btn flat color="negative q-mr-lg">Delete</q-btn>
      </q-card-actions>
    </q-card>
  </q-page>
</template>

<script setup lang="ts">
import axios from 'axios';
import { useAppStore } from 'src/stores/app-store';
import { ref } from 'vue';
import { onMounted } from 'vue';
import { Yacht } from 'src/components/models';

const store = useAppStore();
const url = process.env.API;
const yachts = ref<Array<Yacht>>();
const stars = ref(4);
onMounted(() => {
  store.setPage('Yachts');
  getyachts();
});

const getyachts = () => {
  axios.get(url + '/yacht/get-all').then((res) => {
    console.log(res.data);
    yachts.value = res.data;
  });
};
</script>
