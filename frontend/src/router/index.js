import { createRouter, createWebHistory } from 'vue-router';
import HomePage from "@/pages/HomePage";


// creating routes to all pages
const routes = [
	{
		path: '/',
		name: 'home',
		component: HomePage
	},
];

// creating Vue-Router instance with normal history mode
const router = createRouter({
	history: createWebHistory(),
	routes,
});

export default router;