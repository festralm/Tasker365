import store from '@/store';


export const canEnterToUserPortal = () => store.getters.userToken;