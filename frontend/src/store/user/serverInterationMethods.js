import { makePostReq } from "../helpers/request";
import {defaultHttpResHandler} from "../helpers/defaultHttpResHandler";


// http request for login into system
export const login = async (data) => {
	return await defaultHttpResHandler(async () => {
		return await makePostReq('/login', data);
	});
};

