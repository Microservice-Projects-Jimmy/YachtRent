import { defineStore } from 'pinia';
interface User {
  username: string;
  token: string;
  fullName: string;
}

export const useAppStore = defineStore('app', {
  state: () => ({
    user: {} as User,
    page: 'Main',
    dialog: false,
    username: '',
  }),

  getters: {
    getPage(state) {
      return state.page;
    },
    getDialog(state) {
      return state.dialog;
    },
    getUser(state) {
      return state.user;
    },
    getUsername(state) {
      return state.username;
    },
  },

  actions: {
    setPage(name: string) {
      this.page = name;
    },
    setDialog(status: boolean) {
      this.dialog = status;
    },
    setUser(user: User) {
      this.user = user;
    },
    setUserName(username: string) {
      this.username = username;
    },
  },
});
