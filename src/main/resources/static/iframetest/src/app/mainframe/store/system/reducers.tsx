import { UPDATE_SESSION, SystemState, SystemActionTypes, CHANGE_VIEW, PageEnum } from './types';

const initialState: SystemState = {
    currentPage: PageEnum.LOGIN,
    pages: {
        LOGIN: { title: "Login" },
        MAIN_FRAME: { title: "Login" }
    }
};

export function systemReducer(
    state = initialState,
    action: SystemActionTypes
): SystemState {
    switch (action.type) {
        case UPDATE_SESSION: {
            return {
                ...state,
                session : action.payload
            };
        }
        case CHANGE_VIEW:{
            return {
                ...state,
                currentPage : action.payload
            }
        }
        default:
            return state;
    }
}
