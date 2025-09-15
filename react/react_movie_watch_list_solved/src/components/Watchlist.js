import React from "react";
import MovieCard from "./MovieCard";

const Watchlist = ({movieList,setMovieList}) => {
  const clearWatchList = () => {
    setMovieList( prev => prev.map( movie => ({...movie, disabled:false}) ) );
  }

  return (
    <div data-testid="watchlist-container">
      {/* Provide the total count of movies watchlisted */}
      <h2>Watchlist {<span>({movieList.length})</span>}</h2>
      {/* <p data-testid="watchlist-empty">Your watchlist is empty.</p> */}
      <div>
        <div data-testid="movie-container" className="movies-container">
          { movieList.length === 0 ?
             (<p data-testid="watchlist-empty">Your watchlist is empty.</p>)
              : 
              movieList.map((movie) => (
                <MovieCard id={movie.id} title={movie.title} setMovieList={setMovieList} disabled={movie.disabled} />
              ))
          }
        </div>
        <button data-testid="clear-watchlist-btn" onClick={ () => clearWatchList() } >Clear Watchlist</button>
      </div>
    </div>
  );
};

export default Watchlist;
