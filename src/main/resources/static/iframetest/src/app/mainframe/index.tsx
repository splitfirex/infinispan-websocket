import * as React from "react";
import { connect } from "react-redux";
import { AppState } from "./store";

import { SystemState } from "./store/system/types";
import { updateSession } from "./store/system/actions";
import CenterContainer from './containers/centerContainer'


interface AppProps {
  updateSession: typeof updateSession;
  system: SystemState;
}

export type UpdateMessageParam = React.SyntheticEvent<{ value: string }>;

class App extends React.Component<AppProps> {
  state = {
    message: ""
  };

  componentDidMount() {
    this.props.updateSession({
      loggedIn: true,
      session: "my_session",
      userName: "myName"
    });
  }

  render() {
    return (
      <div className="parent">
      <CenterContainer></CenterContainer>
      </div>
    );
  }
}

const mapStateToProps = (state: AppState) => ({
  system: state.system
});

export default connect(
  mapStateToProps,
  { updateSession }
)(App);
