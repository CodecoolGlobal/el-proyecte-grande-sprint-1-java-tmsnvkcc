import './TransactionCard.styles.css';
import UseDeleteTransaction from './TransactionCard.hooks.jsx';
import {useEffect, useState} from 'react';

const provideAmountColor = (amount) => {
  return amount > 0 ? 'transaction-card-income' : 'transaction-card-expense';
};

const provideAmountSign = (amount) => {
  return amount > 0 ? '+' : '';
};


const TransactionCardComponent = ({ transaction, refetch }) => {
  const [enableDelete, setEnableDelete] = useState(false);
  const { responseData, responseStatus, isTransactionLoading, isTransactionError } = UseDeleteTransaction(transaction.id, enableDelete);

  const handleClick = () => {
    setEnableDelete(true);
    refetch();
  };

  useEffect(() => {
    if (enableDelete && isTransactionLoading) {
      refetch();
    }
  }, [isTransactionLoading]);


  return (
    <div className={'transaction-card-container'}>
      <div className={'transaction-card-upper-container'}>
        <h2 className={provideAmountColor(transaction.amount)}>{provideAmountSign(transaction.amount)}{transaction.amount}</h2>
        <h2>{transaction.dateOfTransaction}</h2>
        <button type={'button'} onClick={handleClick}> X </button>
      </div>
      <div className={'transaction-card-bottom-container'}>
        <h3>{transaction.description}</h3>
      </div>
    </div>
  );
};

export default TransactionCardComponent;
