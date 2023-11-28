import { useState } from 'react';
import { TrackComponentSelector, TrackDateSelector, Overview } from 'components/track';
import { PageTitle } from 'components/form-related';
import './Track.styles.css';
import { useGetAccountDetails, useGetTrackPageData } from 'hooks';
import useGetMonthlyTransactions from 'hooks/useGetMonthlyTransactions.jsx';

const Track = () => {
  const [currentTile, setCurrentTile] = useState('Overview');
  const [everyMonth, setEveryMonth] = useState(["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"])
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
  const [selectedMonth, setSeletedMonth] = useState(new Date().getMonth());

  console.log(useGetTrackPageData(selectedYear,selectedMonth));
  //TODO Implement logic to sort incoming data into different states (account, transactions etc...)

  const componentRenderHandler = () => {
    switch (currentTile) {
    case 'Overview':
      return <Overview />;

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
    <div className={'track-page'}>
      <PageTitle title={'Track'} />
      <TrackDateSelector year={selectedYear} everyMonth={everyMonth} months={selectedMonth} setSelectedYear={setSelectedYear} setSeletedMonth={setSeletedMonth} />
      <div id='track-tile-selector'>
        <TrackComponentSelector buttonLabel='Spendings' clickHandler={handleClick} />
        <TrackComponentSelector buttonLabel='Income' clickHandler={handleClick} />
        <TrackComponentSelector buttonLabel='Savings' clickHandler={handleClick} />
        <TrackComponentSelector buttonLabel='Overview' clickHandler={handleClick} />
      </div>
      {componentRenderHandler()}
    </div>
  );
};

export default Track;
