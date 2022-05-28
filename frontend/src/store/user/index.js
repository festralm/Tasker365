import Cookies from 'js-cookie';
import {
	login,
	register,
} from "./serverInterationMethods";


export const user = {
	state: {
		token: null,
	},
	mutations: {
		setToken(state, data) {
			state.token = data;
		},
	},
	actions: {
		async loadUserToken({getters, commit}) {
			if(getters.token) return;
			let token = Cookies.get('token');
			if(token) {
				commit('setToken', token);
			}
		},
		async login({getters, commit}, data) {
			if(getters.token) return;
			let {token} = await login(data);
			Cookies.set('token', token);
			commit('setToken', token);
		},
		async register({getters}, data) {
			if(getters.token) return;
			await register(data);
		},
		logout({commit}) {
			Cookies.remove('token');
			commit('setToken', null);
		},
	},
	getters: {
		userToken: (state) => state.token,
		profileData: (state) => state.profileData,
	}
};