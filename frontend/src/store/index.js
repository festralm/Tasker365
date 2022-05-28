import { createStore } from 'vuex'
import {user} from "./user";
import {application} from "./application";


// creating Vuex store instance
const store = createStore({
	state: {},
	mutations: {},
	actions: {},
	modules: {
		user, application
	}
});

//store.dispatch('loadUserToken');

export default store;