import {getIterationInfo} from "../user/serverInterationMethods";


export const application = {
	state: {
		iterationInfo: null
	},
	mutations: {
		setIterationInfo(store, data) {
			store.iterationInfo = data;
		},
	},
	actions: {
		async loadIterationInfo({commit, getters}) {
			if(!getters.iterationInfo) {
				const iterationInfo = await getIterationInfo();
				commit('setIterationInfo', iterationInfo);
			}
		},
	},
	getters: {
		iterationInfo: (state) => state.iterationInfo
	}
};