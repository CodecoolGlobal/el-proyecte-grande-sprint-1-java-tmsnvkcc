import './TransactionCard.styles.css';

const provideAmountColor = (amount) => {
  return amount > 0 ? 'transaction-card-income' : 'transaction-card-expense';
};

const provideAmountSign = (amount) => {
  return amount > 0 ? '+ ' : '- ';
};

const TransactionCardComponent = ({ transaction }) => {
  console.log(transaction);

  return (
    <div className={'transaction-card-container'}>
      <div className={'transaction-card-upper-container'}>
        <h2 className={provideAmountColor(transaction.amount)}>{provideAmountSign(transaction.amount)}{transaction.amount}</h2>
        <h2>{transaction.dateOfTransaction}</h2>
      </div>
      <div className={'transaction-card-bottom-container'}>
        <h3>{transaction.description}</h3>
      </div>
    </div>
  );
};

export default TransactionCardComponent;
