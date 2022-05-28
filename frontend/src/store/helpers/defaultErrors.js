
// Internal Server exception
export class InternalServerError extends Error {
    constructor(...args) {
        super(...args);
        this.message = 'Внутрення ошибка сервера';
    }
}

export class InvalidCredentialsError extends Error {
    constructor(...args) {
        super(...args);
        this.message = 'Введены некорректные данные';
    }
}

export class AuthFailedError extends Error {
    constructor(...args) {
        super(...args);
        this.message = 'Неверный логин или пароль';
    }
}

export class ValidationError extends Error {
    constructor(...args) {
        super(...args);
        this.message = 'Ошибка валидации';
    }
}