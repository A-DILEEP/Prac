import React from 'react'

function Search({ setSearch }) {
  return (
    <input
      data-testid="filterInput"
      className="large"
      placeholder="Enter Country Name" onChange={(e)=>setSearch(e.target.value)}
    />
  );
}

export default Search;
