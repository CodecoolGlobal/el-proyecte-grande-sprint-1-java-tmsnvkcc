import { useQuery } from '@tanstack/react-query';

const getProfileAccounts = () => {
  const query = useQuery({
    queryKey:['getUserAccounts'],
    queryFn: async () => {
      const response = await fetch('/api/users/get-accounts');
      const data = await response.json();

      return data;
    },
  });

  return {
    accountData: query.data,
    isAccountDataLoading: query.isFetching,
    isAccountDataError: query.isError,
    refetch: query.refetch,
  };
};

export default getProfileAccounts;
