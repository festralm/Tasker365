import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/pages/HomePage";
import LoginPage from "@/pages/LoginPage";
import RegisterPage from "@/pages/RegisterPage";
import DashboardPage from "@/pages/DashboardPage";
import WorkspacePage from "@/pages/WorkspacePage";
import BoardPage from "@/pages/BoardPage";


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
		path: '/user/dashboard',
		name: 'dashboard',
		component: DashboardPage
	},
	{
		path: '/user/workspace',
		name: 'workspace',
		component: WorkspacePage
	},
	{
		path: '/user/board',
		name: 'board',
		component: BoardPage
	},
];

// creating Vue-Router instance with normal history mode
const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;