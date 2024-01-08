import { useUser } from '@src/context/UserContext.jsx';
import {
  useEffect,
  useState,
} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { iconLibraryConfig } from '@src/config';
import './Spendings.styles.css';

const Spendings = ({ transactions, isLoading }) => {
  const [spending, setSpending] = useState('');
  const [spendingList, setSpendingList] = useState('');
  const [categories, setCategories] = useState('');
  const [currency, setCurrency] = useState('HUF');
  const { user } = useUser();

  const getAmountSumOf = (list) => {
    let sum = 0;

    for (let i = 0; i < list.length; i++) {
      sum += list[i].amount;
    }

    return sum;
  };

  const getSpendings = (exTransactionList) => {
    return exTransactionList.filter((tr) => tr.amount < 0).reverse();
  };

  const calculateSpending = (exTransactionList) => {
    return getAmountSumOf(getSpendings(exTransactionList));
  };

  const getCategoryNames = (exTransactionList) => {
    const group = [];

    getSpendings(exTransactionList)
      .forEach((tr) => !group.includes(tr.categoryName) && group.push(tr.categoryName));

    return group;
  };

  const calculateSumForCategories = (categoryNameList, exTransactionList) => {
    const result = [];
    const spendingList = getSpendings(exTransactionList);

    for (let i = 0; i < categoryNameList.length; i++) {
      const transactionsByCategory = spendingList
        .filter((tr) => tr.categoryName === categoryNameList[i]);

      result.push({ name: categoryNameList[i], sum: getAmountSumOf(transactionsByCategory) });
    }

    return result;
  };

  useEffect(() => {
    if (!isLoading) {
      const data = transactions.externalTransactionDTOS;

      setSpending(calculateSpending(data));
      setSpendingList(getSpendings(data));
      // setCategoryNames();
      const categoryNames = getCategoryNames(data);

      setCategories(calculateSumForCategories(categoryNames, data));

    }
  }, [transactions, isLoading]);

  // TODO: refactor component (create hook)

  return (
    <div className={'track-page-spendings'}>
      {isLoading &&
        <div className={'loading'}>
          <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
        </div>}
      <div className={'spending-left'}>
        <div className={'left-content'}>
          <div className={'information'}>
            <div className={'title'}>
              <span>{spending * -1} {currency}</span>
              <button>
                <FontAwesomeIcon icon={iconLibraryConfig.faPlus} />
                <span>Add new expense</span>
              </button>
            </div>
            {spendingList && spendingList.map((spending) => <p key={spending.id}>{spending.amount * -1} {currency}</p>)}
          </div>
        </div>
      </div>
      <div className={'spending-right'}>
        <div className={'right-content'}>
          {categories && categories.map((cat) => {
            return (
              <div key={cat.name}
                className={'information'}>
                <h3 className={'category-name'}>{cat.name}</h3>
                <h3 className={'category-amount spending-color'}>{cat.sum * -1} {currency}</h3>
              </div>
            );
          })}
        </div>
      </div>
      <div className={'spending-right'}>
        <div className={'right-content'}>
          <div className={'balance-content'}>
            <div className={'information'}>
              <h2>Actual Balance</h2>
              <h3>{user.actualBalance} {currency}</h3>
            </div>
            <div className={'information'}>
              <h2>Savings Balance</h2>
              <h3>{user.savingsBalance} {currency}</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Spendings;
