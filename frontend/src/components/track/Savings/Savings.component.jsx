import {
  useEffect,
  useState,
} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDownLong, faUpLong } from '@fortawesome/free-solid-svg-icons';
import { AddLocalTransactionModal } from '@src/components/modal';
import { iconLibraryConfig } from '@src/config';
import TransactionCard from '../TransactionCard'; //FIXME change individual import to icon library config
import './Savings.styles.css';

const Savings = ({ transactions, isLoading, refetch }) => {
  const [balanceDetails, setBalanceDetails] = useState({ actual: 0, savings: 0 });
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [transactionDirection, setTransactionDirection] = useState('expense');

  const getAccountBalance = () => {
    const storedData = JSON.parse(localStorage.getItem('userData'));

    return storedData;
    //TODO Replace temporary implementation of account retrieval
  };
  //TODO add loading state handler

  useEffect(() => {
    const retrievedBalances = getAccountBalance();

    setBalanceDetails({ actual: retrievedBalances.actualBalance, savings: retrievedBalances.savingsBalance });
  }, []);

  const provideTransactionCards = () => {
    const reversedTransactionArray = transactions.localTransactionDTOS.toReversed();

    return reversedTransactionArray.map((transaction) => <TransactionCard key={transaction.id} transaction={transaction} refetch={refetch}/>);
  };

  const listenForEscapeKey = (event) => {
    if (event.key === 'Escape') {
      setIsModalVisible(false);
    }
  };
  const handleOnClick = (directionOfButton) => {
    setIsModalVisible(!isModalVisible);
    setTransactionDirection(directionOfButton);

    if (isModalVisible) {
      refetch();
    }
  };

  if (isLoading) {
    return (
      <div className={'savings-page-overview'}>
        <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
      </div>);
  }

  //TODO Add conditional text rendering for no transactions case
  return (
    <div className={'savings-page-overview'}>
      <div className={'savings-controls-container'}>
        <div className={'savings-balance-container'}>
          <h2>ACTUAL BALANCE</h2>
          {balanceDetails.actualBalance}
        </div>
        <div className={'savings-arrow-button-container'}>
          <button type={'button'} onClick={() => handleOnClick('expense')}>
            <FontAwesomeIcon icon={faUpLong} className={'savings-arrow-left'}/>
          </button>
          <button type={'button'} onClick={() => handleOnClick('income')}>
            <FontAwesomeIcon icon={faDownLong} className={'savings-arrow-right'}/>
          </button>
        </div>
        <div className={'savings-balance-container'}>
          <h2>SAVINGS BALANCE</h2>
          {balanceDetails.savingsBalance}
        </div>
      </div>
      <div className={'savings-transactions-container'}>
        <div className={'savings-transactions-title'}><h2> Recent local transactions </h2></div>
        <div className={'savings-transactions-scroll-bar'}>
          {provideTransactionCards()}
        </div>
      </div>
      <AddLocalTransactionModal
        isModalVisible={isModalVisible}
        handleOnKeyClose={listenForEscapeKey}
        handleOnClick={handleOnClick}
        transactionDirection={transactionDirection} />
    </div>
  );
};

export default Savings;
