import React, { useState } from "react";
const LogForm = ({ logs, setLogs }) => {
  const [Running, setRunning] = useState("");
  const [Duration, setDuration] = useState("");
  const [Calories, setCalories] = useState("");
  const [error, setError] = useState("");
  const handleReset = () => {
    setRunning("");
    setDuration("");
    setCalories("");
    setLogs([]);
    setError(""); 
  };
  const handleSubmit = (e) => {
    e.preventDefault();

    if (Running.trim() === "") {
      setError("Exercise type must not be empty.");
      return;
    }
    if (Duration.trim() === "" || Number(Duration) <= 0) {
      setError("Duration must be a positive number.");
      return;
    }
    if (Calories.trim() === "" || Number(Calories) <= 0) {
      setError("Calories must be a positive number.");
      return;
    }
    const log = {
      Running,
      Duration,
      Calories,
    };

    setLogs([...logs, log]);
    setRunning("");
    setDuration("");
    setCalories("");
    setError("");
  };

  return (
    <div className="layout column justify-content-center align-items-center">
      <form data-testid="log-form" onSubmit={handleSubmit}>
        <div className="mb-10">
          <label>
            Exercise Type:
            <input
              type="text"
              name="exerciseType"
              value={Running}
              placeholder="e.g., Running"
              className="ml-50"
              data-testid="input-exerciseType"
              onChange={(e) => setRunning(e.target.value)}
            />
          </label>
        </div>

        <div className="mb-10">
          <label>
            Duration (minutes):
            <input
              type="number"
              name="duration"
              value={Duration}
              placeholder="e.g., 30"
              className="ml-10"
              data-testid="input-duration"
              onChange={(e) => setDuration(e.target.value)}
            />
          </label>
        </div>

        <div className="mb-10">
          <label>
            Calories Burned:
            <input
              type="number"
              name="caloriesBurned"
              value={Calories}
              placeholder="e.g., 300"
              className="ml-30"
              data-testid="input-caloriesBurned"
              onChange={(e) => setCalories(e.target.value)}
            />
          </label>
        </div>
        {error && (
          <p data-testid="error-message" style={{ color: "red" }}>
            {error}
          </p>
        )}

        <button type="submit" data-testid="btn-logActivity" className="mr-10">
          Log Activity
        </button>
        <button type="button" data-testid="btn-resetLog" onClick={handleReset}>
          Reset Log
        </button>
      </form>
    </div>
  );
};

export default LogForm;
