import React, { useState } from "react";
import LogForm from "./LogForm";
import LogList from "./LogList";

const FitnessTracker = () => {
  const [logs,setLogs]=useState([]);
  return (
    <div className="pa-20">
      <h1>Track Your Fitness</h1>
      <LogForm logs={logs} setLogs={setLogs}/>
      <LogList logs={logs}/>
    </div>
  );
};

export default FitnessTracker;
