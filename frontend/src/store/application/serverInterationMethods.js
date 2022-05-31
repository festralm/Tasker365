// http request for default register process
import {defaultHttpResHandler} from "@/store/helpers/defaultHttpResHandler";
import {getAuthHeaders, makeGetReq, makePostReq} from "@/store/helpers/request";


export const register = async (data) => {
    return await defaultHttpResHandler(async () => {
        return await makePostReq('/register', data);
    });
};

export const getWorkspaces = async (token) => {
    return await defaultHttpResHandler(async () => {
        return await makeGetReq('/workspace', getAuthHeaders(token));
    });
};

export const createWorkspace = async (data, token) => {
    return await defaultHttpResHandler(async () => {
        return await makePostReq('/workspace', data, getAuthHeaders(token));
    });
};