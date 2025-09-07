// import React from "react";
// import "./index.css"

// export default function KanbanBoard(props) {

// 	let [tasks, setTasks] = React.useState([
// 		{ name: '1', stage: 0 },
// 		{ name: '2', stage: 0 },
// 	])

// 	let [stagesNames, setStagesNames] = React.useState(['Backlog', 'To Do', 'Ongoing', 'Done']);

// 	let stagesTasks = [];
// 	for (let i = 0; i < stagesNames.length; ++i) {
// 		stagesTasks.push([]);
// 	}
// 	for (let task of tasks) {
// 		const stageId = task.stage;
// 		stagesTasks[stageId].push(task);
// 	}

// 	return (
// 		<div className="mt-20 layout-column justify-content-center align-items-center">
// 			<section className="mt-50 layout-row align-items-center justify-content-center">
// 				<input id="create-task-input" type="text" className="large" placeholder="New task name" data-testid="create-task-input" />
// 				<button type="submit" className="ml-30" data-testid="create-task-button">Create task</button>
// 			</section>

// 			<div className="mt-50 layout-row">
// 				{stagesTasks.map((tasks, i) => {
// 					return (
// 						<div className="card outlined ml-20 mt-0" key={`${i}`}>
// 							<div className="card-text">
// 								<h4>{stagesNames[i]}</h4>
// 								<ul className="styled mt-50" data-testid={`stage-${i}`}>
// 									{tasks.map((task, index) => {
// 										return <li className="slide-up-fade-in" key={`${i}${index}`}>
// 											<div className="li-content layout-row justify-content-between align-items-center">
// 												<span data-testid={`${task.name.split(' ').join('-')}-name`}>{task.name}</span>
// 												<div className="icons">
// 													<button className="icon-only x-small mx-2" data-testid={`${task.name.split(' ').join('-')}-back`}>
// 														<i className="material-icons">arrow_back</i>
// 													</button>
// 													<button className="icon-only x-small mx-2" data-testid={`${task.name.split(' ').join('-')}-forward`}>
// 														<i className="material-icons">arrow_forward</i>
// 													</button>
// 													<button className="icon-only danger x-small mx-2" data-testid={`${task.name.split(' ').join('-')}-delete`}>
// 														<i className="material-icons">delete</i>
// 													</button>
// 												</div>
// 											</div>
// 										</li>
// 									})}
// 								</ul>
// 							</div>
// 						</div>
// 					)
// 				})}
// 			</div>
// 		</div>
// 	)
// }

import React from "react";
import "./index.css";

export default function KanbanBoard() {
  const [tasks, setTasks] = React.useState([
    { name: "1", stage: 0 },
    { name: "2", stage: 0 },
  ]);
  const [stagesNames] = React.useState(["Backlog", "To Do", "Ongoing", "Done"]);
  const [newTaskName, setNewTaskName] = React.useState("");
  const addTask = () => {
    if (newTaskName.trim() === "") return;
    setTasks([...tasks, { name: newTaskName, stage: 0 }]);
    setNewTaskName("");
  };
  const moveTask = (index, direction) => {
    setTasks((prevTasks) => {
      const updatedTasks = [...prevTasks];
      let newStage = updatedTasks[index].stage + direction;
      if (newStage < 0) newStage = 0;
      if (newStage > stagesNames.length - 1) newStage = stagesNames.length - 1;
      updatedTasks[index].stage = newStage;
      return updatedTasks;
    });
  };
  const deleteTask = (index) => {
    setTasks((prevTasks) => prevTasks.filter((_, i) => i !== index));
  };
  const stagesTasks = stagesNames.map((_, i) =>
    tasks.filter((task) => task.stage === i)
  );

  return (
    <div className="mt-20 layout-column justify-content-center align-items-center">
      <section className="mt-50 layout-row align-items-center justify-content-center">
        <input
          id="create-task-input"
          type="text"
          className="large"
          placeholder="New task name"
          data-testid="create-task-input"
          value={newTaskName}
          onChange={(e) => setNewTaskName(e.target.value)}
        />
        <button
          type="submit"
          className="ml-30"
          data-testid="create-task-button"
          onClick={addTask}
        >
          Create task
        </button>
      </section>
      <div className="mt-50 layout-row">
        {stagesTasks.map((tasksInStage, i) => (
          <div className="card outlined ml-20 mt-0" key={i}>
            <div className="card-text">
              <h4>{stagesNames[i]}</h4>
              <ul className="styled mt-50" data-testid={`stage-${i}`}>
                {tasksInStage.map((task, index) => {
                  const globalIndex = tasks.findIndex(
                    (t) => t.name === task.name && t.stage === i
                  );
                  const taskId = `${task.name.split(" ").join("-")}-name`;
                  const backId = `${task.name.split(" ").join("-")}-back`;
                  const forwardId = `${task.name.split(" ").join("-")}-forward`;
                  const deleteId = `${task.name.split(" ").join("-")}-delete`;

                  return (
                    <li className="slide-up-fade-in" key={`${i}${index}`}>
                      <div className="li-content layout-row justify-content-between align-items-center">
                        <span data-testid={taskId}>{task.name}</span>
                        <div className="icons">
                          <button
                            className="icon-only x-small mx-2"
                            data-testid={backId}
                            disabled={task.stage === 0}
                            onClick={() => moveTask(globalIndex, -1)}
                          >
                            <i className="material-icons">arrow_back</i>
                          </button>
                          <button
                            className="icon-only x-small mx-2"
                            data-testid={forwardId}
                            disabled={task.stage === stagesNames.length - 1}
                            onClick={() => moveTask(globalIndex, 1)}
                          >
                            <i className="material-icons">arrow_forward</i>
                          </button>
                          <button
                            className="icon-only danger x-small mx-2"
                            data-testid={deleteId}
                            onClick={() => deleteTask(globalIndex)}
                          >
                            <i className="material-icons">delete</i>
                          </button>
                        </div>
                      </div>
                    </li>
                  );
                })}
              </ul>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
