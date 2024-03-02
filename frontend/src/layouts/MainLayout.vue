<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title @click="toHome" class="cursor-pointer">
          Yacht Rent
        </q-toolbar-title>

        <div class="text-h5">{{ store.getPage }}</div>
        <q-space />

        <q-btn
          class="text-h6 q-mr-xl"
          no-caps
          flat
          @click="store.setDialog(!store.getDialog)"
        >
          {{ store.getUser?.username ?? `Login` }}
        </q-btn>
      </q-toolbar>
    </q-header>

    <q-drawer
      class="bg-green-5 text-white"
      v-model="leftDrawerOpen"
      show-if-above
      bordered
    >
      <q-list>
        <q-item-label
          header
          clickable
          class="text-h5 text-white cursor-pointer"
          @click="toHome"
        >
          Sidebar menus
        </q-item-label>

        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>
    <LoginComponent />
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import EssentialLink, {
  EssentialLinkProps,
} from 'components/EssentialLink.vue';

import { useAppStore } from 'src/stores/app-store';
import { useRouter } from 'vue-router';
import LoginComponent from 'src/components/LoginComponent.vue';

const store = useAppStore();
const router = useRouter();

const essentialLinks: EssentialLinkProps[] = [
  {
    title: 'Yachts',
    icon: 'sailing',
    link: '/yachts',
  },
  {
    title: 'Users',
    icon: 'people',
    link: '/users',
  },
];

const leftDrawerOpen = ref(false);

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}
const toHome = () => {
  router.push('/');
  store.setPage('Main');
};
</script>
