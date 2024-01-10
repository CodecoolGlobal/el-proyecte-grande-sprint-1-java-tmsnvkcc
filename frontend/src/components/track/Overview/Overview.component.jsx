import { useUser } from '@src/context/UserContext.jsx';
import { useCurrencyFormatter } from '@src/hooks';
import {
  useEffect,
  useState,
} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { iconLibraryConfig } from '@src/config';
import './Overview.styles.css';

const Overview = ({ transactions, isLoading }) => {
  const [spending, setSpending] = useState(null);
  const [income, setIncome] = useState(null);
  const [plannedSpending, setPlannedSpending] = useState(null);
  const [plannedIncome, setPlannedIncome] = useState(null);
  const [currency, setCurrency] = useState('HUF');
  const { user } = useUser();
  const { formatCurrency } = useCurrencyFormatter();

  const getAmountSumOf = (list) => {
    let sum = 0;

    for (let i = 0; i < list.length; i++) {
      sum += list[i].amount;
    }

    return sum;
  };

  const calculateSpending = (exTransactionList) => {
    return getAmountSumOf(exTransactionList.filter((tr) => tr.amount < 0));
  };

  const calculateIncome = (exTransactionList) => {
    return getAmountSumOf(exTransactionList.filter((tr) => tr.amount > 0));
  };

  const calculatePlannedSpending = (exTransactionList) => {
    return getAmountSumOf(exTransactionList.filter((tr) => tr.amount < 0 && tr.isPlanned));
  };

  const calculatePlannedIncome = (exTransactionList) => {
    return getAmountSumOf(exTransactionList.filter((tr) => tr.amount > 0 && tr.isPlanned));
  };
  //TODO Implement custom hook for helper functions

  useEffect(() => {
    if (!isLoading) {
      const data = transactions.externalTransactionDTOS;

      setSpending(calculateSpending(data));
      setIncome(calculateIncome(data));
      setPlannedSpending(calculatePlannedSpending(data));
      setPlannedIncome(calculatePlannedIncome(data));
    }
  }, [transactions, isLoading]);

  return (
    <div className={'track-page-overview'}>

      {isLoading &&
        <div className={'track-page-overview-loading'}>
          <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
        </div>}

      <div className={'overview-left'}>
        {/*<div className={'left-title'}>
                    <h1>Monthly balance</h1>
                    <hr />
                </div>*/}
        <div className={'left-content'}>
          <div className={'information'}>
            <h2>Total amount of spending this month</h2>
            <h3 className={'spending-color'}>{formatCurrency(spending)}</h3>
          </div>

          <div className={'information'}>
            <h2>Planned spending</h2>
            <h3 className={'spending-color'}>{formatCurrency(plannedSpending)}</h3>
          </div>

          <div className={'information'}>
            <h2>Total amount of income this month</h2>
            <h3 className={'income-color'}>{formatCurrency(income)}</h3>
          </div>

          <div className={'information'}>
            <h2>Planned income</h2>
            <h3 className={'income-color'}>{formatCurrency(plannedIncome)}</h3>
          </div>
        </div>
      </div>
      <div className={'overview-split'}>
        <hr className={'hr-stand'} />
      </div>
      <div className={'overview-right'}>
        {/*<div className={'right-title'}>
                    <h1>Total</h1>
                    <hr />
                </div>*/}
        <div className={'right-content'}>
          <div className={'information'}>
            <h2>Actual Balance</h2>
            <h3>{formatCurrency(user.actualBalance)}</h3>
          </div>
          <div className={'information'}>
            <h2>Savings Balance</h2>
            <h3>{formatCurrency(user.savingsBalance)}</h3>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Overview;
