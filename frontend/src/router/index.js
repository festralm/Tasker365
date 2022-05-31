import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/pages/HomePage";
import LoginPage from "@/pages/LoginPage";
import RegisterPage from "@/pages/RegisterPage";
import DashboardPage from "@/pages/DashboardPage";
import WorkspacePage from "@/pages/WorkspacePage";
import BoardPage from "@/pages/BoardPage";
import App from "@/App";
import {canEnterToUserPortal} from "@/router/helpers";
import store from '../store';


// creating routes to all pages
const routes = [
	{
		path: '/',
		name: 'home',
		component: HomePage
	},
	{
		path: '/login',
		name: 'login',
		component: LoginPage
	},
	{
		path: '/register',
		name: 'register',
		component: RegisterPage
	},
	{
		path: '/user',
		component: App,
		children: [
			{
				path: 'dashboard',
				name: 'dashboard',
				component: DashboardPage
			},
			{
				path: 'workspace/:workspaceId',
				name: 'workspace',
				props: true,
				component: WorkspacePage
			},
			{
				path: 'board/:boardId',
				name: 'board',
				props: true,
				component: BoardPage
			},
		],
		beforeEnter(from, to, next) {
			if(canEnterToUserPortal()) {
				next();
			} else {
				store.dispatch('loadUserToken');
				if(!canEnterToUserPortal()) {
					next({path: '/login'});
				}
				next();
			}
		}
	},
];

// creating Vue-Router instance with normal history mode
const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;