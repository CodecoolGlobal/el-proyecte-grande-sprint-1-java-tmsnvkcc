import { useState } from 'react';
import { TrackComponentSelector } from 'components/track';
import { PageTitle } from 'components/form-related';
import './Track.styles.css';
import { useGetAccountDetails } from 'hooks';
import useGetMonthlyTransactions from 'hooks/useGetMonthlyTransactions.jsx';

const Track = () => {
  const [currentTile, setCurrentTile] = useState('Overview');
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
  const [selectedMonth, setSeletedMonth] = useState(new Date().getMonth());
  const { accountData, isAccountLoading, isAccountError } = useGetAccountDetails();
  const { transactionData, isTransactionLoading, isTransactionError } = useGetMonthlyTransactions(selectedYear, selectedMonth);

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

  console.log(accountData);
  console.log(transactionData); // TODO Tesing purposes only

  return (
    <div>
      <PageTitle title={'Track'} />
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
