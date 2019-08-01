import React from 'react';
import { Provider } from "react-redux";
import logo from './logo.svg';
import Mainframe from './mainframe'
import configureStore from './mainframe/store'
import './App.css';

const store = configureStore();

const App: React.FC = () => {
  return (
    <Provider store={store}>
      <Mainframe></Mainframe>
    </Provider>
  );
}

export default App;
