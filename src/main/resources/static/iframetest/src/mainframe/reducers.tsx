import { changeContentDispatch } from './actions'
import { AppState, PageStatus, SystemActionTypes } from './types';

const initialState: AppState = {
    frontEnd: {
        currentPage: "Login",
        pages: {
            Login: {
                status: PageStatus.SHOW,
                title: "Login"
            },
            MainFrame: {
                status: PageStatus.SHOW,
                title: "Mainframe"
            }
        }
    },
    system: {
        logged: false
    }
};


export function tryLogin(
    state = initialState,
    action: SystemActionTypes
): AppState {
    switch (action.type) {
        case "TRY_LOGIN":
            return {
                ...state,
                frontEnd: { currentPage: "Mainrame" },
                system: {
                    logged: true,
                    User: {
                        username: action.username,
                        profile: "Alumno",
                        usertoken: "token123456"
                    }
                }
            };
        default:
            return state;
    }
}
