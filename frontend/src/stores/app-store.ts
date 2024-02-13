import { defineStore } from 'pinia';

export const useAppStore = defineStore('app', {
  state: () => ({
    user: '',
    page: 'Main',
  }),

  getters: {
    getPage(state) {
      return state.page;
    },
  },

  actions: {
    setPage(name: string) {
      this.page = name;
    },
  },
});
