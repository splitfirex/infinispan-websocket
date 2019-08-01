export const CHANGE_CONTENT = 'CHANGE_CONTENT';

export function changeContentDispatch(text: Window) {
    return { type: CHANGE_CONTENT, content: text }
}