import { useState } from 'react';
import {
  AddTransaction,
  PageTitle,
} from 'components/form-related';
import { Window } from 'components/dashboard';
import { AddTransactionModal } from 'components/modal';
import './Dashboard.styles.css';

const Dashboard = () => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const handleOnClick = () => {
    setIsModalVisible(!isModalVisible);
  };

  const listenForEscapeKey = (event) => {
    if (event.key === 'Escape') {
      setIsModalVisible(false);
    }
  };

  return (
    <main>
      <div className={!isModalVisible ? 'dashboard-page' : 'dashboard-page blur'}>
        <PageTitle title={'Dashboard'} />
        <div className={'dashboard-window-container'}>
          <Window title={'Current month'} text={'Data...'} button={<AddTransaction handleOnClick={handleOnClick} />} />
          <Window title={'Last 12 months recap'} text={'Expenses...'} />
          <Window title={'Test title'} text={'Random test text dev'} />
        </div>
      </div>
      <AddTransactionModal isModalVisible={isModalVisible} handleOnKeyClose={listenForEscapeKey} handleOnClick={handleOnClick} />
    </main>
  );
};

export default Dashboard;
