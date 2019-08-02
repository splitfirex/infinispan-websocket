import React from "react";
import {Tabs, Tab, DragTabList, DragTab, PanelList, Panel, ExtraButton} from 'react-tabtab';

export const CenterContainer: React.FC = () => {
  return (
    <Tabs defaultActiveKey="profile" id="uncontrolled-tab-example">
      <Tab eventKey="home" title="Home">
        <iframe src="/" height="100%" width="100%" title="prueba"/>
      </Tab>
      <Tab eventKey="profile" title="Profile" />
      <Tab eventKey="contact" title="Contact" disabled />
    </Tabs>
  );
};

export default CenterContainer;
