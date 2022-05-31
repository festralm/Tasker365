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

export const getWorkspaceData = async (id, token) => {
    return await defaultHttpResHandler(async () => {
        return await makeGetReq('/workspace/' + id, getAuthHeaders(token));
    });
};

export const createWorkspace = async (data, token) => {
    return await defaultHttpResHandler(async () => {
        return await makePostReq('/workspace', data, getAuthHeaders(token));
    });
};

export const createBoard = async (data, token) => {
    return await defaultHttpResHandler(async () => {
        return await makePostReq('/board', data, getAuthHeaders(token));
    });
};

export const getBoardData = async (id, token) => {
    return await defaultHttpResHandler(async () => {
        return await makeGetReq('/board/' + id, getAuthHeaders(token));
    });
};

export const createTask = async (data, token) => {
    return await defaultHttpResHandler(async () => {
        return await makePostReq('/task', data, getAuthHeaders(token));
    });
};