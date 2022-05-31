import {
	createBoard,
	createTask,
	createWorkspace, getBoardData, getUsers,
	getWorkspaceData,
	getWorkspaces,
	register
} from "./serverInterationMethods";


export const application = {
	state: {
		workspaces: [],
		workspaceData: {},
		boardData: {tasks: []},
		users: []
	},
	mutations: {
		setWorkspaces(state, data) {
			state.workspaces = data;
		},

		setWorkspaceData(state, data) {
			state.workspaceData = data;
		},

		setBoardData(state, data) {
			state.boardData = data;
		},

		setUsers(state, data) {
			state.users = data;
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
		},

		async loadWorkspaceData({commit, getters}, id) {
			const data = await getWorkspaceData(id, getters.userToken);
			commit('setWorkspaceData', data);
		},

		async createBoard({getters}, data) {
			await createBoard(data, getters.userToken);
		},

		async loadBoardData({commit, getters}, id) {
			const data = await getBoardData(id, getters.userToken);
			commit('setBoardData', data);
		},

		async createTask({getters}, data) {
			await createTask(data, getters.userToken);
		},

		async loadUsers({commit, getters}) {
			const data = await getUsers(getters.userToken);
			commit('setUsers', data);
		},
	},
	getters: {
		workspaces: (state) => state.workspaces,
		workspaceData: (state) => state.workspaceData,
		boardData: (state) => state.boardData,
		todoData: (state) => state.boardData.tasks.filter(i => i.status === 'todo'),
		workData: (state) => state.boardData.tasks.filter(i => i.status === 'work'),
		doneData: (state) => state.boardData.tasks.filter(i => i.status === 'done'),
		users: (state) => state.users
	}
};