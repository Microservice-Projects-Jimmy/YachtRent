<template>
  <q-page class="row items-center justify-evenly text-h3 bg-blue-2" padding>
    <h5 class="full-width text-center">
      <p>
        These are 3 users system created as a default, password for all of them
        is "123456",
      </p>
      <br />
      <p>for example to login as admin, username: admin, password:123456</p>
      <p>to login as user, username: user, password:123456</p>
      ...
    </h5>
    <q-card
      class="my-card q-my-sm"
      flat
      bordered
      v-for="user in users"
      :key="user.fullName"
    >
      <q-img
        src="https://images.vexels.com/content/145908/preview/male-avatar-maker-2a7919.png"
      />

      <q-card-section>
        <div class="row no-wrap items-center">
          <div class="col text-h6 ellipsis">{{ user.username }}</div>
        </div>
      </q-card-section>

      <q-card-section class="q-pt-none">
        <div class="text-caption text-grey">
          {{ user.fullName }}
        </div>
      </q-card-section>

      <q-separator />

      <q-card-actions>
        <q-btn flat color="primary"> I am {{ user.username }} </q-btn>
        <q-space />
        <q-btn flat color="accent q-mr-lg" @click="loginButton(user.username)"
          >Login</q-btn
        >
      </q-card-actions>
    </q-card>
  </q-page>
</template>

<script setup lang="ts">
import axios from 'axios';
import { useAppStore } from 'src/stores/app-store';
import { ref } from 'vue';
import { onMounted } from 'vue';
import { User } from 'src/components/models';
import { bus } from 'src/components/models';

const store = useAppStore();
const url = process.env.API;
const users = ref<Array<User>>();

onMounted(() => {
  store.setPage('Users');
  getUsers();
});
const getUsers = () => {
  axios.get(url + '/user/get-all').then((res) => {
    console.log(res.data);
    users.value = res.data;
  });
};
const loginButton = (username: string) => {
  store.setUserName(username);
  store.setDialog(true);
  bus.emit('setUserName');
};
</script>
