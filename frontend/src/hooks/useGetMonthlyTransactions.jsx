import { useQuery } from '@tanstack/react-query';

const fetchMonthlyTransactions = async (year, month) => {
  try {
    const response = await fetch(`/api/transaction/${year}/${month + 1}`);
    const data = await response.json();

    return data;
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
    refetch: query.refetch,
  };
};

export default useGetMonthlyTransactions;
