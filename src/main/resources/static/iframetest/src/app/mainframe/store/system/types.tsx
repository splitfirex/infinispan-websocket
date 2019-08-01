// Describing the shape of the system's slice of state
export interface SystemState {
    session?: Session;
    currentPage: PageEnum;
    pages: {
        LOGIN: Pages;
        MAIN_FRAME: Pages;
    }
}

// Describing the different ACTION NAMES available
export const UPDATE_SESSION = "UPDATE_SESSION";
export const CHANGE_VIEW = "CHANGE_VIEW";

export enum PageEnum {
    LOGIN, MAIN_FRAME
}

export interface Session {
    loggedIn: boolean;
    session: string;
    userName: string;
}

export interface Pages {
    title: String
}

export interface UpdateSessionAction {
    type: typeof UPDATE_SESSION;
    payload: Session;
}

export interface ChangeViewAction {
    type: typeof CHANGE_VIEW;
    payload: PageEnum;
}

export type SystemActionTypes = UpdateSessionAction | ChangeViewAction;
