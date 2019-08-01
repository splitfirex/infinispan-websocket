import { UPDATE_SESSION, PageEnum, CHANGE_VIEW, Session } from './types';
import { func } from "prop-types";

export function updateSession(newSession: Session) {
  return {
    type: UPDATE_SESSION,
    payload: newSession
  };
}

export function changeView(newPage: PageEnum){
    return{
        type: CHANGE_VIEW,
        payload: newPage
    };
}