import { useQuery } from '@tanstack/react-query';

const fetchMonthlyTransactions = async (year, month) => {
  try {
    const response = await fetch(`/api/track/${year}/${month}`);

    return await response.json();
  } catch (err) {
    console.log(err);
  }
};
//TODO Wrap fetch to check localstorage first

const useGetMonthlyTransactions = (year, month) => {
  const query = useQuery({
    queryKey:['monthlyTransactions'],
    queryFn:() => fetchMonthlyTransactions(year, month),
  });

  return {
    transactionsData: query.data,
    isTransactionLoading: query.isLoading,
    isTransactionError: query.isError,
  };
};

export default useGetMonthlyTransactions;
