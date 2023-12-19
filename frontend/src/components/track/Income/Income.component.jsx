import { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { iconLibraryConfig } from '@src/config';
import './Income.styles.css';

const Income = ({ transactions, isLoading }) => {
  const [balanceDetails, setBalanceDetails] = useState({ actual: 0, savings: 0 });
  const [income, setIncome] = useState('');
  const [incomeList, setIncomeList] = useState('');
  const [categories, setCategories] = useState('');
  const [currency, setCurrency] = useState('HUF');

  const getAmountSumOf = (list) => {
    let sum = 0;

    for (let i = 0; i < list.length; i++) {
      sum += list[i].amount;
    }

    return sum;
  };

  const getIncomes = (exTransactionList) => {
    return exTransactionList.filter((tr) => tr.amount > 0).reverse();
  };

  const calculateIncome = (exTransactionList) => {
    return getAmountSumOf(getIncomes(exTransactionList));
  };

  const getCategoryNames = (exTransactionList) => {
    const group = [];

    getIncomes(exTransactionList)
      .forEach((tr) => !group.includes(tr.categoryName) && group.push(tr.categoryName));

    return group;
  };

  const calculateSumForCategories = (categoryNameList, exTransactionList) => {
    const result = [];
    const incomeList = getIncomes(exTransactionList);

    for (let i = 0; i < categoryNameList.length; i++) {
      const transactionsByCategory = incomeList
        .filter((tr) => tr.categoryName === categoryNameList[i]);

      result.push({ name: categoryNameList[i], sum: getAmountSumOf(transactionsByCategory) });
    }

    return result;
  };

  useEffect(() => {
    if (!isLoading) {
      const data = transactions.externalTransactionDTOS;

      setIncome(calculateIncome(data));
      setIncomeList(getIncomes(data));
      // setCategoryNames();
      const categoryNames = getCategoryNames(data);
      setCategories(calculateSumForCategories(categoryNames, data));

      const userData = JSON.parse(localStorage.getItem('userData'));
      setBalanceDetails({ actual: userData.actualBalance, savings: userData.savingsBalance });
    }
  }, [transactions, isLoading]);

  // TODO: refactor component (create hook)

  return (
    <div className={'track-page-income'}>
      {isLoading &&
        <div className={'loading'}>
          <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
        </div>}

      <div className={'income-left'}>
        <div className={'left-content'}>
          <div className={'information'}>
            <div className={'title'}>
              <span>{income} {currency}</span>
              <button>
                <FontAwesomeIcon icon={iconLibraryConfig.faPlus} />
                <span>Add new income</span>
              </button>
            </div>
            {incomeList && incomeList.map((income) => <p key={income.id}>{income.amount} {currency}</p>)}
          </div>
        </div>
      </div>
      <div className={'income-right'}>
        <div className={'right-content'}>
          {categories && categories.map((cat) => {
            return (
              <div key={cat.name} className={'information'}>
                <h3 className={'category-name'}>{cat.name}</h3>
                <h3 className={'category-amount income-color'}>{cat.sum} {currency}</h3>
              </div>
            );
          })}
        </div>
      </div>
      <div className={'income-right'}>
        <div className={'right-content'}>
          <div className={'balance-content'}>
            <div className={'information'}>
              <h2>Actual Balance</h2>
              <h3>{balanceDetails.actualBalance} {currency}</h3>
            </div>
            <div className={'information'}>
              <h2>Savings Balance</h2>
              <h3>{balanceDetails.savingsBalance} {currency}</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Income;
