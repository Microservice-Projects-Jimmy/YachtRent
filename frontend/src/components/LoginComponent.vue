<template>
  <div>
    <q-dialog v-model="store.dialog">
      <q-card class="row justify-center" style="width: 700px; max-width: 80vw">
        <div class="q-pa-xl" style="max-width: 600px; width: 600px">
          <h3>Login</h3>
          <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-md">
            <q-input
              outlined
              v-model="username"
              label="Username"
              hint="Username"
              lazy-rules
              :rules="[
                (val) => (val && val.length > 0) || 'Please type your username',
              ]"
            />

            <q-input
              outlined
              type="password"
              v-model="password"
              label="Password"
              lazy-rules
              :rules="[
                (val) =>
                  (val !== null && val !== '') || 'Please type your password',
              ]"
            />

            <div>
              <q-btn label="Submit" type="submit" color="primary" />
              <q-btn
                label="Reset"
                type="reset"
                color="negative"
                class="q-ml-sm"
              />
              <q-btn
                color="secondary"
                class="float-right"
                @click="store.setDialog(false)"
                >Close</q-btn
              >
            </div>
          </q-form>
        </div>
      </q-card>
    </q-dialog>
  </div>
</template>

<script lang="ts" setup>
import {  ref } from 'vue';
import { useAppStore } from 'src/stores/app-store';
import { reactive } from 'vue';
import axios from 'axios';
import { useQuasar } from 'quasar';
import { bus } from './models';
const $q = useQuasar();

const store = useAppStore();
const username = ref('');
const password = ref('123456');
const url = process.env.API;

const data = reactive({
  username: username,
  password: password,
});
const onSubmit = () => {
  console.log('submitted');
  axios.post(url + '/user/login', data).then((res) => {
    console.log(res.data);
    store.setUser(res.data);
    store.setDialog(false);
    $q.notify({
      type: 'positive',
      progress: true,
      message: res.data.username + ' has logged in.',
      classes: 'custom-notify',
    });
  });
};
const onReset = () => {
  username.value = '';
  password.value = '';
};

bus.on('setUserName', () => {
  username.value = store.getUsername;
  password.value = '123456';
});
</script>
