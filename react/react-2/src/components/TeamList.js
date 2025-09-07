// import React, { useState } from 'react'
// import Team from './Team'

// import './TeamList.css'

// const TeamList = () => {
//   const teamList = [
//     { name: 'Team1',
//       channels: [
//         { name: 'Channel1',
//           id: 1
//         },
//         { name: 'Channel2',
//           id: 2
//         }
//       ]
//     },
//     { name: 'Team2',
//       channels: [
//         { name: 'Channel1',
//           id: 1
//         },
//         { name: 'Channel2',
//           id: 2
//         }
//       ]
//     }
//   ]

//   return (
//     <div className='w-50 mx-auto'>
//       <div className='card w-35 mt-50 mx-auto px-10 py-15'>
//         <div className='layout-column' data-testid='team-list'>
//           { teamList && teamList.map((team, id) => (
//             <Team
//               key={ id }
//               id={ id }
//               team={ team }
//             />
//           ))}
//         </div>
//         <div className='layout-row'>
//           <input
//             placeholder='Enter Team Name'
//             className='team-list-input w-75'
//             data-testid='team-name-input'
//           />
//           <button
//             className='team-list-btn x-small w-35 h-30 pa-6 ma-0 ml-6'
//             data-testid='add-team-btn'
//           >
//             Add Team
//           </button>
//         </div>
//       </div>
//     </div>
//   )
// }

// export default TeamList

import React, { useState } from "react";
import Team from "./Team";

import "./TeamList.css";

const TeamList = () => {
  const [teams, setTeams] = useState([
    {
      name: "Team1",
      channels: [
        { name: "Channel1", id: 1 },
        { name: "Channel2", id: 2 },
      ],
    },
    {
      name: "Team2",
      channels: [
        { name: "Channel1", id: 1 },
        { name: "Channel2", id: 2 },
      ],
    },
  ]);

  const [newTeamName, setNewTeamName] = useState("");

  const isAddTeamEnabled =
    newTeamName.trim() !== "" && !teams.some((t) => t.name === newTeamName);

  const addTeam = () => {
    if (!isAddTeamEnabled) return;
    setTeams([...teams, { name: newTeamName, channels: [] }]);
    setNewTeamName("");
  };

  return (
    <div className="w-50 mx-auto">
      <div className="card w-35 mt-50 mx-auto px-10 py-15">
        <div className="layout-column" data-testid="team-list">
          {teams &&
            teams.map((team, id) => (
              <Team
                key={id}
                id={id}
                team={team}
                teams={teams}
                setTeams={setTeams}
              />
            ))}
        </div>
        <div className="layout-row">
          <input
            placeholder="Enter Team Name"
            className="team-list-input w-75"
            data-testid="team-name-input"
            value={newTeamName}
            onChange={(e) => setNewTeamName(e.target.value)}
          />
          <button
            className="team-list-btn x-small w-35 h-30 pa-6 ma-0 ml-6"
            data-testid="add-team-btn"
            onClick={addTeam}
            disabled={!isAddTeamEnabled}
          >
            Add Team
          </button>
        </div>
      </div>
    </div>
  );
};

export default TeamList;
