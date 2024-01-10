import { MonthlyCharts, ProfitAnalytics } from '@src/components/dashboard/index.js';
import { useGetMonthlyTransactions } from '@src/hooks';
import { useState } from 'react';
import {
  AddTransaction,
  PageTitle,
} from '@src/components/form-related';
import { Window } from '@src/components/dashboard';
import { AddTransactionModal } from '@src/components/modal';
import { useGetDashboardData } from './Dashboard.hooks.jsx';
import './Dashboard.styles.css';

const Dashboard = () => {
  const { data, isLoading, isError } = useGetDashboardData();
  const { transactionsData, isTransactionLoading, refetch } = useGetMonthlyTransactions(new Date().getFullYear(), new Date().getMonth());
  const [isModalVisible, setIsModalVisible] = useState(false);

  const handleOnClick = () => {
    setIsModalVisible(!isModalVisible);
  };

  const listenForEscapeKey = (event) => {
    if (event.key === 'Escape') {
      setIsModalVisible(false);
    }
  };

  if (isLoading) {
    return (
      <div>Loading data...</div>
    );
  }

  const provideIncomeArray = () => {
    return transactionsData?.externalTransactionDTOS.filter((transaction) => transaction.amount > 0);
  };

  if (isError) {
    return (
      <div>An error has happened, please reload the application.</div>
    );
  }

  return (
    <main>
      <div className={!isModalVisible ? 'dashboard-page' : 'dashboard-page blur'}>
        <PageTitle title={'Dashboard'} />
        <div className={'dashboard-window-container'}>
          <Window title={'Current month'} text={''} button={<AddTransaction handleOnClick={handleOnClick} />}>
            <ProfitAnalytics transactionsData={transactionsData} isTransactionLoading={isTransactionLoading} />
          </Window>
          <Window title={'Last 12 months recap'} text={''} >
            <MonthlyCharts transactionArray={provideIncomeArray()} isTransactionLoading={isTransactionLoading}/>
          </Window>
          <Window title={'Test title'} text={'Random test text dev'} />
        </div>
      </div>
      <AddTransactionModal
        isModalVisible={isModalVisible}
        handleOnKeyClose={listenForEscapeKey}
        handleOnClick={handleOnClick}
        data={data}
      />
    </main>
  );
};

export default Dashboard;
