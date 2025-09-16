import React from "react";
import MovieCard from "./MovieCard";
import Watchlist from "./Watchlist";

const movies = [
  { id: 1, title: "Inception", disabled: false },
  { id: 2, title: "Interstellar", disabled: false },
  { id: 3, title: "The Dark Knight", disabled: false },
  { id: 4, title: "Dunkirk", disabled: false },
  { id: 5, title: "The Shawshank Redemption", disabled: false },
];

const WatchlistApp = () => {
  const [movieList, setMovieList] = React.useState(movies);
  //const [watchlist, setWatchlist] = React.useState([]);
  return (
    <div>
      <h2>Movies</h2>
      <div className="movies-container">
        {movieList.map((movie) => (
          <MovieCard id={movie.id} title={movie.title} setMovieList={setMovieList} disabled={movie.disabled} />
        ))}
      </div>

      { /* <Watchlist watchlist={watchlist} setWatchlist={setWatchlist} setMovieList={setMovieList} />
      */ }
      <Watchlist movieList={ movieList.filter( movie => movie.disabled) } setMovieList={setMovieList} />
    </div>
  );
};

export default WatchlistApp;
