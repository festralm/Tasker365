import store from '@/store';


// check if user has token - authorized
export const canEnterToUserPortal = () => store.getters.userToken;