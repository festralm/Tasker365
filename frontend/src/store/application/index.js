import {createWorkspace, getWorkspaces, register} from "./serverInterationMethods";


export const application = {
	state: {
		workspaces: []
	},
	mutations: {
		setWorkspaces(state, data) {
			state.workspaces = data;
		}
	},
	actions: {
		// default new user register method
		async register({getters}, data) {
			if(getters.token) return;
			await register(data);
		},

		async loadWorkspaces({commit, getters}) {
			const data = await getWorkspaces(getters.userToken);
			commit('setWorkspaces', data);
		},

		async createWorkspace({getters}, data) {
			await createWorkspace(data, getters.userToken);
		}
	},
	getters: {
		workspaces: (state) => state.workspaces,
	}
};