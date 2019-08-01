export interface AppState {
    frontEnd: {
        currentPage: String,
        pages?:{
            Login : Page,
            MainFrame : Page
        }
    },
    system: {
        logged: boolean,
        User?: User
    }

};

export interface Page {
    title: String,
    status: PageStatus,
    data?:{}
}

export enum PageStatus {
    SHOW, HIDE, EXTERNAL
}

export interface User{
    username: String
    usertoken: String
    profile: String
}


export const TRY_LOGIN = "TRY_LOGIN";

interface LoginAction{
    type: typeof TRY_LOGIN
    username: String
    password: String
}


export type SystemActionTypes = LoginAction;