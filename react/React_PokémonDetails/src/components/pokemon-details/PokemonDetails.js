// import React from 'react';
// import { Pokemon } from '../pokemon/Pokemon';
// export const PokemonDetails = () => {
// 	const [pokemon, setPokemon] = React.useState(null);
// 	const [id, setId] = React.useState(0);
// 	const getPokemonById = id => {
// 	}

// 	return (
// 		<div className="mt-50 layout-column justify-content-center align-items-center" >
// 			<Pokemon pokemon={pokemon} />
// 			<p>
// 				<button data-testid="pokemon-prev">Previous Evolution</button>
// 				<button data-testid="pokemon-next">Next Evolution</button>
// 			</p>
// 			<p>
// 				<input data-testid="id-input" value={id} onChange={() => { }} type="number" placeholder='Pokemon Id'></input><button data-testid="random-pokemon" onClick={() => getPokemonById(id)} className='text'>Get Pokemon</button>
// 			</p>
// 		</div>
// 	)
// }

import React from "react";
import { Pokemon } from "../pokemon/Pokemon";

export const PokemonDetails = () => {
  const [pokemon, setPokemon] = React.useState(null);
  const [id, setId] = React.useState("");

  const getPokemonById = async (pokeId) => {
    if (pokeId < 1 || pokeId > 151) {
      alert("Pokemon ID must be between 1 and 151");
      return;
    }
    const response = await fetch(
      `https://jsonmock.hackerrank.com/api/pokemon?id=${pokeId}`
    );
    const data = await response.json();
    setPokemon(data.data || null);
  };

  const handlePrev = () => {
    if (pokemon?.prev_evolution) {
      const prevId = pokemon.prev_evolution[0].num;
      getPokemonById(prevId);
      setId(prevId);
    }
  };

  const handleNext = () => {
    if (pokemon?.next_evolution) {
      const nextId = pokemon.next_evolution[0].num;
      getPokemonById(nextId);
      setId(nextId);
    }
  };

  return (
    <div className="mt-50 layout-column justify-content-center align-items-center">
      <Pokemon pokemon={pokemon} />
      <p>
        <button
          data-testid="pokemon-prev"
          onClick={handlePrev}
          disabled={!pokemon || !pokemon.prev_evolution}
        >
          Previous Evolution
        </button>
        <button
          data-testid="pokemon-next"
          onClick={handleNext}
          disabled={!pokemon || !pokemon.next_evolution}
        >
          Next Evolution
        </button>
      </p>
      <p>
        <input
          data-testid="id-input"
          value={id}
          onChange={(e) => setId(e.target.value)}
          type="number"
          placeholder="Pokemon Id"
        />
        <button
          data-testid="random-pokemon"
          onClick={() => getPokemonById(id)}
          className="text"
        >
          Get Pokemon
        </button>
      </p>
    </div>
  );
};
