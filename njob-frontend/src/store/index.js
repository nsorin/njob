import Vue from 'vue'
import Vuex from 'vuex'
import VuexORM from '@vuex-orm/core'
import VuexORMAxios from '@vuex-orm/plugin-axios'
import database from './database'
import http from '../http/http'

Vue.use(Vuex)
VuexORM.use(VuexORMAxios, { http })

export default new Vuex.Store({
  plugins: [VuexORM.install(database)]
})