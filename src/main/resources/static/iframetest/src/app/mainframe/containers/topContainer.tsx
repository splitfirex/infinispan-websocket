import React from "react";

export const TopContainer: React.FC = () => {
  return (
    <div style={{ backgroundColor: "red", height: "80px" }}>
      <Reloj />
    </div>
  );
};

export const Reloj: React.FC = () => {
  return <div style={{ backgroundColor: "blue", height: "60px", width:"50px", float:"right" }} />;
};

export default TopContainer;
