import Cookies from 'js-cookie';
import {
	login,
} from "./serverInterationMethods";


// store with all authenticated user data
export const user = {
	state: {
		// authorization user token for api
		token: null,
	},
	mutations: {
		// setting token state
		setToken(state, data) {
			state.token = data;
		},
	},
	actions: {
		// gets user auth token from cookies if needed
		async loadUserToken({getters, commit}) {
			if(getters.token) return;
			let token = Cookies.get('token');
			if(token) {
				commit('setToken', token);
			}
		},
		// default user authorization method. Saves token in cookies if success
		async login({getters, commit}, data) {
			if(getters.token) return;
			let ret = await login(data);
			Cookies.set('token', ret.token);
			commit('setToken', ret.token);
		},
		// default logout method. Clear auth token from Storage (cookies by default)
		logout({commit}) {
			Cookies.remove('token');
			commit('setToken', null);
		},
	},
	getters: {
		// get default user auth token
		userToken: (state) => state.token,
	}
};