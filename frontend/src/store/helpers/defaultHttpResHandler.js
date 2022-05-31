import {InternalServerError, InvalidCredentialsError, TokenInvalidError} from "./defaultErrors";


// function gets async function with http request, execute it and validate by response status code
export async function defaultHttpResHandler(doRequest) {
	let resStatus;
	try {
		let response = await doRequest();
		if (response.status === 200) {
			return response.data;
		} else {
			resStatus = response.status;
		}
	} catch (e) {
		if(!e || !e.response || !e.response.data) {
			throw new InternalServerError();
		}
		resStatus = e.response.status;
	}
	switch (resStatus) {
		case 403:
			throw new InvalidCredentialsError();
		case 500:
			throw new InternalServerError();
		case 401:
			throw new TokenInvalidError();
	}
	throw new InternalServerError();
}