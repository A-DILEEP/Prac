import React from "react";

const MovieCard = ({id,title,setMovieList,disabled}) => {

  const addToWatchlist = (id) => {
    // Logic to update movie in movieList to set disabled to true
    setMovieList(prev => prev.map( 
        movie => movie.id === id ? 
          {...movie, disabled:true} 
          : movie 
      )
    );
    // Logic to add movie to watchlist
    //setMovieList(prev => [...prev, {id,title,disabled:true}]);
  }

  const removeFromWatchlist = (id) => {
    // Logic to update movie in movieList to set disabled to false
    setMovieList(prev => prev.map(    
        movie => movie.id === id ? 
          {...movie, disabled:false} 
          : movie 
      )
    );
  }

  return (
    // set all data-testids according to the movie id.
    // Ex: on the movie card the data-testid for movie with id:1  will be movie-card-1
    <div key={id} className="movie-card" data-testid={`movie-card-${id}`}>
      <h3 data-testid={`movie-title-${id}`}>{title}</h3>
      <button className="danger" data-testid={`remove-btn-${id}`} onClick={ () => { removeFromWatchlist(id) } }>
        Remove
      </button>
      <button data-testid={`add-btn-${id}`} onClick={ () => addToWatchlist(id) } disabled={disabled}>
        {/* "Added" */}
        { disabled ? "Added" : "Add to Watchlist" }
      </button>
    </div>
  );
};

export default MovieCard;
