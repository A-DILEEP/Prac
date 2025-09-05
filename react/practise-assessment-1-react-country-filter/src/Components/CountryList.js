import React from "react";
import { response } from "../response";
function CountryList({ search }) {
  const filtered = response.filter((res) =>
    res.toLowerCase().includes(search.toLowerCase())
  );
  return (
    <section>
      <ul className="card country-list" data-testid="countryList">
        {filtered.map((res, index) => (
          <li key={index} className="pa-10 pl-20">
            {res}
          </li>
        ))}
      </ul>
    </section>
  );
}

export default CountryList;
