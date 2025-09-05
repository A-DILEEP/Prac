import React from "react";
import "../App.css";

const LogList = ({logs}) => {
  return (
    <div data-testid="log-list">
      <h2>Activity Log</h2>
      {logs.map((log)=>(
      <ul className="pl-0 log-entry-ul" key={log.id}>
        <li data-testid="log-entry" className="log-entry-li mb-10 pa-10">
          <p>
            <strong>Exercise:</strong> {log.Running}
          </p>
          <p>
            <strong>Duration:</strong> {log.Duration} minutes
          </p>
          <p>
            <strong>Calories Burned:</strong> {log.Calories} kcal
          </p>
        </li>
      </ul>
      ))}
      {/* <p>No activities logged yet.</p> */}
    </div>
  );
};

export default LogList;
