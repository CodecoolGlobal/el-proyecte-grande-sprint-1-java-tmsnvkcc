import { useEffect, useState } from 'react';
import {Overview, SavingsComponent, TrackNavigation} from 'components/track';
import { PageTitle } from 'components/form-related';
import './Track.styles.css';
import useGetMonthlyTransactions from 'hooks/useGetMonthlyTransactions.jsx';

const Track = () => {
  const [currentTile, setCurrentTile] = useState('Overview');
  const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
  const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth());
  const { transactionsData, isTransactionLoading, isTransactionError, refetch } = useGetMonthlyTransactions(selectedYear, selectedMonth);

  useEffect(() => {
    refetch();
  }, [selectedYear, selectedMonth]);


  const componentRenderHandler = () => {
    switch (currentTile) {
    case 'Overview':
      return <Overview transactions={transactionsData} isLoading={isTransactionLoading}/>;

    case 'Spendings':
      return 'Spendings Component Placeholder';

    case 'Income':
      return 'Income Component Placeholder';

    case 'Savings':
      return <SavingsComponent />;

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
      <TrackNavigation
        selectedYear={selectedYear}
        selectedMonth={selectedMonth}
        setSelectedYear={setSelectedYear}
        setSelectedMonth={setSelectedMonth}
        handleClick={handleClick} />
      <div className={'track-content'}>
        {componentRenderHandler()}
      </div>
    </div>
  );
};

export default Track;
