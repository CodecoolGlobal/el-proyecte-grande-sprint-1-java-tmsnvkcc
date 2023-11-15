import { useQuery } from '@tanstack/react-query';

const fetchAccountDetail = async () => {
  try {
    const response = await fetch('/api/account');

    return await response.json();
  } catch (err) {
    console.log(err);
  }
};
//TODO Wrap fetch to check localstorage first

const useGetAccountDetails = () => {
  const query = useQuery({
    queryKey:'accountDetails',
    queryFn:fetchAccountDetail,
  });

  return {
    accountData: query.data,
    isAccountLoading: query.isLoading,
    isAccountError: query.isError,
  };
};

export default useGetAccountDetails;
