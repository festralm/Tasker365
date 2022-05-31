const apiPrefix = "http://localhost:8080/api";


// makes post request to server
export async function makePostReq(url, data, headers) {
    const requestOptions = {
        method: "POST",
        headers: headers,
        body: JSON.stringify(data)
    };
    const response = await fetch(apiPrefix + url, requestOptions);
    const responseData = await response.json();
    // return responseData.data;
    return responseData;

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
    const responseData = await response.json();
    // return responseData.data;
    return responseData;
}

export function getAuthHeaders(token) {
    return {
        "X-Auth-Token": token
    };
}