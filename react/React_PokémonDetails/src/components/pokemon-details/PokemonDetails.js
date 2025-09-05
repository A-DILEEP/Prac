import React from 'react';
import { Pokemon } from '../pokemon/Pokemon';
export const PokemonDetails = () => {
	const [pokemon, setPokemon] = React.useState(null);
	const [id, setId] = React.useState(0);
	const getPokemonById = id => {
	}


	return (
		<div className="mt-50 layout-column justify-content-center align-items-center" >
			<Pokemon pokemon={pokemon} />
			<p>
				<button data-testid="pokemon-prev">Previous Evolution</button>
				<button data-testid="pokemon-next">Next Evolution</button>
			</p>
			<p>
				<input data-testid="id-input" value={id} onChange={() => { }} type="number" placeholder='Pokemon Id'></input><button data-testid="random-pokemon" onClick={() => getPokemonById(id)} className='text'>Get Pokemon</button>
			</p>
		</div>
	)
}