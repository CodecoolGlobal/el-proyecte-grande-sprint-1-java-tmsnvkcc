import { useQuery } from '@tanstack/react-query';

const getProfileData = (url) => {
  const token = window.localStorage.getItem('token');

  const query = useQuery({
    queryKey:['getProfileData', url],
    queryFn: async () => {
      const response = await fetch(`/api/users/${url}`, {
        headers:{
          'Authorization': `Bearer ${token}`,
          'Content-type': 'application/json',
        },
      });

      const data = await response.json();

      return data;
    },
  });

  return {
    data: query.data,
    isDataLoading: query.isFetching,
    isDataError: query.isError,
    refetch: query.refetch,
  };
};

export default getProfileData;
