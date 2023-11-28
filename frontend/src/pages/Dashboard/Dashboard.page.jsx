import {
  AddTransaction,
  PageTitle,
} from 'components/form-related';
import {
  Window,
} from 'components/dashboard';
import { AddTransactionModal } from 'components/modal';
import './Dashboard.styles.css';
import { useState } from 'react';

const Dashboard = () => {
  const [isModalVisible, setIsModalVisible] = useState(false);
  const handleOnClick = () => {
    setIsModalVisible(!isModalVisible);
  };

  return (
    <div className={'dashboard-page'}>
      <PageTitle title={'Dashboard'} />
      <div className={'dashboard-window-container'}>
        <Window title={'Aktuális hónap'} text={'Kiadás az előző hónaphoz képest'} button={<AddTransaction onClick={handleOnClick} />} />
        <Window title={'12 Havi visszatekintés'} text={'Kiadások'} />
        <Window title={'Test title'} text={'Random test text dev'} />
      </div>
      <AddTransactionModal isModalVisible={isModalVisible} />
    </div>
  );
};

export default Dashboard;
