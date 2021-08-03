import Vue from 'vue'

import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({

  state: {

    login_state:false

  },
  mutations: {
    changeLoginState(state,login_state){
        state.login_state = login_state
    }
  }
})