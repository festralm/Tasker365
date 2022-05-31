const apiPrefix = "http://localhost:8080/api";


// makes post request to server
export async function makePostReq(url, data, headers) {
    if (data) {
        if (headers) {
            headers["Content-Type"] = "application/json;charset=UTF-8";
        } else {
            headers = {"Content-Type": "application/json;charset=UTF-8"};
        }
    }
    const requestOptions = {
        method: "POST",
        headers: headers,
        body: JSON.stringify(data)
    };
    const response = await fetch(apiPrefix + url, requestOptions);
    let responseData = null;
    try {
        responseData = await response.json();
    } catch (e) {
        console.log(e);
    }
    if (response.status === 200 && !responseData) {
        return {data: {}, status: response.status};
    }
    return {data: responseData, status: response.status};
    /*
    let xmlHttp = new XMLHttpRequest();
    url = apiPrefix + url;
    xmlHttp.open("POST", url, false);
    xmlHttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    let body = data;
    console.log(headers);
    try {
        xmlHttp.send(JSON.stringify(body));
        console.log(xmlHttp.response);
        return JSON.parse(xmlHttp.response);
    } catch (e) {
        console.log(e);
    }
     */

}

// makes get request to server
export async function makeGetReq(url, headers) {
    const requestOptions = {
        method: "GET",
        headers: headers,
    };
    const response = await fetch(apiPrefix + url, requestOptions);
    let responseData = null;
    try {
        responseData = await response.json();
    } catch (e) {
        console.log(e);
    }
    if (response.status === 200 && !responseData) {
        return {data: {}, status: response.status};
    }
    return {data: responseData, status: response.status};
}

export function getAuthHeaders(token) {
    return {
        "X-Auth-Token": token
    };
}