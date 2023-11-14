import { useState } from 'react';
import './Track.styles.css';

const Track = () => {
  const [currentTile, setCurrentTile] = useState('Overview');

  return (
    <div>
      <div id='track-tile-selector'>
        <button onClick={() => setCurrentTile('Spendings')}> Spendings </button>
        <button onClick={() => setCurrentTile('Income')}> Income </button>
        <button onClick={() => setCurrentTile('Savings')}> Savings </button>
        <button onClick={() => setCurrentTile('Overview')}> Overview </button>
      </div>

    </div>
  );
};

export default Track;
