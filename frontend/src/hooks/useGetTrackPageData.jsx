import useGetMonthlyTransactions from './useGetMonthlyTransactions.jsx';
import { useGetAccountDetails } from './index.js';

const useGetTrackPageData = (year, month) => {
  const transactions = useGetMonthlyTransactions(year, month);
  const account = useGetAccountDetails();

  return { 'transactions' : transactions, 'account' : account };
  //TODO Left here for multiple query test purposes - Needs rework
};

export default useGetTrackPageData;
