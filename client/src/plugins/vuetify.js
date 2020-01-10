import Vue from 'vue';
import '@mdi/font/css/materialdesignicons.css'
import Vuetify from 'vuetify/lib';

Vue.use(Vuetify);

export default new Vuetify({
  icons: {
    iconfont: 'mdi',
  },
});
