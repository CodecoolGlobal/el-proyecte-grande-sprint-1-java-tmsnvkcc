import { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDownLong, faUpLong } from '@fortawesome/free-solid-svg-icons';
import { TransactionCardComponent } from '../index.js'; //FIXME change individual import to icon library config
import './Savings.styles.css';
import { AddLocalTransactionModal } from '../../modal/index.js';

const SavingsComponent = ({ transactions, isLoading }) => {
  const [actualBalance, setActualBalance] = useState(0);
  const [savingsBalance, setSavingsBalance] = useState(0);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [transactionDirection, setTransactionDirection] = useState('expense');
  const getAccountBalance = () => {
    const storedData = JSON.parse(localStorage.getItem('userData'));

    return storedData.account;
    //TODO Replace temporary implementation of account retrieval
  };
  //TODO add loading state handler

  useEffect(() => {
    const retrievedBalances = getAccountBalance();

    setActualBalance(retrievedBalances.actualBalance);
    setSavingsBalance(retrievedBalances.savingsBalance);
  }, []);

  const listenForEscapeKey = (event) => {
    if (event.key === 'Escape') {
      setIsModalVisible(false);
    }
  };
  const handleOnClick = (directionOfButton) => {
    setIsModalVisible(!isModalVisible);
    setTransactionDirection(directionOfButton);
  };

  //TODO Add conditional text rendering for no transactions case
  return (
    <div className={'savings-page-overview'}>
      <div className={'savings-controls-container'}>
        <div className={'savings-balance-container'}>
          <h2>ACTUAL BALANCE</h2>
          {actualBalance}
        </div>
        <div className={'savings-arrow-button-container'}>
          <button type={'button'} onClick={() => handleOnClick('expense')}><FontAwesomeIcon icon={faUpLong} className={'savings-arrow-left'}/></button>
          <button type={'button'} onClick={() => handleOnClick('income')}><FontAwesomeIcon icon={faDownLong} className={'savings-arrow-right'}/></button>
        </div>
        <div className={'savings-balance-container'}>
          <h2>SAVINGS BALANCE</h2>
          {savingsBalance}
        </div>
      </div>
      <div className={'savings-transactions-container'}>
        <div className={'savings-transactions-title'}><h2> Recent local transactions </h2></div>
        <div className={'savings-transactions-scroll-bar'}>
          {transactions.localTransactionDTOS.map((transaction) => <TransactionCardComponent key={transaction.id} transaction={transaction} />)}
        </div>
      </div>
      <AddLocalTransactionModal isModalVisible={isModalVisible} handleOnKeyClose={listenForEscapeKey} handleOnClick={handleOnClick} transactionDirection={transactionDirection} />
    </div>
  );
};

export default SavingsComponent;
