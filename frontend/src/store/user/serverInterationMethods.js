import { makePostReq } from "../helpers/request";
import {defaultHttpResHandler} from "../helpers/defaultHttpResHandler";


export const login = async (data) => {
	return await defaultHttpResHandler(async () => {
		return await makePostReq('/login', data);
	});
};

export const register = async (data) => {
	return await defaultHttpResHandler(async () => {
		return await makePostReq('/register', data);
	});
};
