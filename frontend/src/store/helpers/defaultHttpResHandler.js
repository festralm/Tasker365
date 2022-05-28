import {
	AuthFailedError,
	InternalServerError,
	InvalidCredentialsError,
	ValidationError
} from "./defaultErrors";


// function gets async function with http request, execute it and validate by response status code
export async function defaultHttpResHandler(doRequest) {
	let resStatus;
	let statusCode;
	try {
		const {data} = await doRequest();
		return data;
	} catch (e) {
		if(!e || !e.response || !e.response.data) {
			throw new InternalServerError();
		}
		resStatus = e.response.status;
		statusCode = e.response.data.statusCode;
	}
	switch (resStatus) {
		case 400:
			switch (statusCode) {
				case 'AuthFailed':
					throw new AuthFailedError();
				case 'InvalidCredentialsError':
					throw new InvalidCredentialsError();
				case 'ValidationError':
					throw new ValidationError();
			}
			break;
		case 500:
			throw new InternalServerError();
	}
	throw new InvalidCredentialsError();
}