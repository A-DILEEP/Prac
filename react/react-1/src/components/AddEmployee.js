// import React, { Fragment } from "react";

// const AddEmployee = () => {
//   return (
//     <Fragment>
//       <td className="pl-30">
//         <input data-testid="new-employee-name-input" placeholder="Enter Name" />
//       </td>
//       <td className="pl-20">
//         <input
//           data-testid="new-employee-position-input"
//           placeholder="Enter Position"
//         />
//       </td>
//       <td className="pl-20">
//         <input
//           data-testid="new-employee-salary-input"
//           type="number"
//           placeholder="Enter Salary"
//         />
//       </td>
//       <td className="pl-20">
//         <button
//           data-testid="add-new-employee-button"
//           className="x-small w-75 ma-0 px-25"
//         >
//           Add
//         </button>
//       </td>
//     </Fragment>
//   );
// };

// export default AddEmployee;

import React, { Fragment, useState } from "react";

const AddEmployee = ({ appEmployeesList, setAppEmployeesList }) => {
  const [name, setName] = useState("");
  const [position, setPosition] = useState("");
  const [salary, setSalary] = useState("");
  const isAddEnabled = name.trim() && position.trim() && salary;

  const handleAdd = (e) => {
    e.preventDefault();
    if (!isAddEnabled) return;
    const newId =
      appEmployeesList.length > 0
        ? appEmployeesList[appEmployeesList.length - 1].id + 1
        : 0;
    const emp = {
      id: newId,
      name,
      position,
      salary: Number(salary),
    };
    setAppEmployeesList([...appEmployeesList, emp]);
    setName("");
    setPosition("");
    setSalary("");
  };

  return (
    <Fragment>
      <td className="pl-30">
        <input
          data-testid="new-employee-name-input"
          placeholder="Enter Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </td>
      <td className="pl-20">
        <input
          data-testid="new-employee-position-input"
          placeholder="Enter Position"
          value={position}
          onChange={(e) => setPosition(e.target.value)}
        />
      </td>
      <td className="pl-20">
        <input
          data-testid="new-employee-salary-input"
          type="number"
          placeholder="Enter Salary"
          value={salary}
          onChange={(e) => setSalary(e.target.value)}
        />
      </td>
      <td className="pl-20">
        <button
          data-testid="add-new-employee-button"
          className="x-small w-75 ma-0 px-25"
          onClick={handleAdd}
          disabled={!isAddEnabled}
        >
          Add
        </button>
      </td>
    </Fragment>
  );
};

export default AddEmployee;
