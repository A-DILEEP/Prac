import React, { Fragment } from 'react'

const Employee = ({idx,appEmployeesList,setAppEmployeesList}) => {
  const[isEditing,setIsEditing] = React.useState(false);
  const [salary,setSalary] = React.useState(appEmployeesList[idx].salary);
  const [enableSave,setEnableSave] = React.useState(false);
  return (
    <Fragment>
      <td>{appEmployeesList[idx].name}</td>
      <td className='pl-20'>{appEmployeesList[idx].position}</td>
      <td className='pl-20'>
        { !isEditing ?
      (<div
        data-testid={'employee-salary-div-' + idx}
        onClick={() => setIsEditing(true)}
      >
        {appEmployeesList[idx].salary}
      </div>)
      :
      (<input
          data-testid={ 'employee-salary-input-' + idx }
          type='number' value={salary}  
          onChange={(e) => {setSalary(e.target.value);
            setEnableSave(true);
          }}
       
      /> )
      }
      </td>
      <td className='pl-20'>
        <button
          className={ 'x-small w-75 ma-0 px-25' }
          data-testid={ 'employee-save-button-' + idx }      
          disabled= {!enableSave}     
          onClick={() => {
            let newList = [...appEmployeesList];
            newList[idx].salary = salary;
            setAppEmployeesList(newList);
            setIsEditing(false);
          }
        }
        >
          Save
        </button>
      </td>
    </Fragment>
  )
}


export default Employee
