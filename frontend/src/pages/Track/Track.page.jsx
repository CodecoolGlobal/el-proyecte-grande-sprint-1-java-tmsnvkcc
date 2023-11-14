import { useState } from 'react';
import './Track.styles.css';
import { TrackComponentSelector } from '../../components/track/index.js';

const Track = () => {
  const [currentTile, setCurrentTile] = useState('Overview');

  const componentRenderHandler = () => {
    switch (currentTile) {
    case 'Overview':
      return 'Overview Component Placeholder';

    case 'Spendings':
      return 'Spendings Component Placeholder';

    case 'Income':
      return 'Income Component Placeholder';

    case 'Savings':
      return 'Savings Component Placeholder';

    default:
      return 'Error';
    }
  };

  const handleClick = (tileName) => {
    setCurrentTile(tileName);
  };

  return (
    <div>
      <div id='track-tile-selector'>
        <TrackComponentSelector buttonLabel='Spendings' clickHandler={handleClick}/>
        <TrackComponentSelector buttonLabel='Income' clickHandler={handleClick}/>
        <TrackComponentSelector buttonLabel='Savings' clickHandler={handleClick}/>
        <TrackComponentSelector buttonLabel='Overview' clickHandler={handleClick}/>
      </div>
      {componentRenderHandler()}
    </div>
  );
};

export default Track;
