import { useEffect, useState } from 'react';
import './Savings.styles.css';
import { iconLibraryConfig } from 'config';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faDownLong, faUpLong} from '@fortawesome/free-solid-svg-icons';
import {TransactionCardComponent} from "../index.js"; //FIXME change individual import to icon library config

const SavingsComponent = ({ transactions, isLoading }) => {
  const [actualBalance, setActualBalance] = useState(0);
  const [savingsBalance, setSavingsBalance] = useState(0);
  const getAccountBalance = () => {
    const storedData = JSON.parse(localStorage.getItem('userData'));

    return storedData.account;
    //TODO Replace temporary implementation of account retrieval
  };

  useEffect(() => {
    const retrievedBalances = getAccountBalance();

    setActualBalance(retrievedBalances.actualBalance);
    setSavingsBalance(retrievedBalances.savingsBalance);
  }, []);

  return (
    <div className={'savings-page-overview'}>
      <div className={'savings-controls-container'}>
        <div className={'savings-balance-container'}>
          <h2>ACTUAL BALANCE</h2>
          {actualBalance}
        </div>
        <div className={'savings-arrow-button-container'}>
          <FontAwesomeIcon icon={faUpLong} className={'savings-arrow-left'}/>
          <FontAwesomeIcon icon={faDownLong} className={'savings-arrow-right'}/>
        </div>
        <div className={'savings-balance-container'}>
          <h2>SAVINGS BALANCE</h2>
          {savingsBalance}
        </div>
      </div>
      <div className={'savings-transactions-container'}>
        <h2> Recent local transactions </h2>
        {transactions.localTransactionDTOS.map(transaction => <TransactionCardComponent key={transaction.id} transaction={transaction} /> )}
      </div>
    </div>
  );
};

export default SavingsComponent;
