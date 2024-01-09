import './ProfitAnalytics.css';
import { useEffect, useState } from 'react';

const ProfitAnalytics = ({ transactionsData, isTransactionLoading }) => {
  const [profit, setProfit] = useState(0);

  const calculateProfit = () => {
    if (!transactionsData.externalTransactionDTOS) return 0;
    const allExpenseThisMonth = transactionsData.externalTransactionDTOS.filter((transaction) => transaction.amount > 0).reduce((acc, curr) => acc + curr.amount, 0);
    const allIncomeThisMonth = transactionsData.externalTransactionDTOS.filter((transaction) => transaction.amount < 0).reduce((acc, curr) => acc + curr.amount, 0);

    return allIncomeThisMonth - allExpenseThisMonth;
  };

  useEffect(() => {
    setProfit(calculateProfit());
  }, [isTransactionLoading]);

  return (
    <div className={'profit-analytics-container'}>
      <p>Total profit this month</p>
      <p className={profit > 0 ? 'profit-sum positive-profit' : 'profit-sum negative-profit'} >{ profit }</p>
    </div>
  );
};

export default ProfitAnalytics;

