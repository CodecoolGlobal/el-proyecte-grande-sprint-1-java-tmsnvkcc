import { useQuery } from '@tanstack/react-query';
// import { axiosConfigWithAuth } from 'config';

const fetchDashboardData = async () => {
  try {
    const token = window.localStorage.getItem('token');

    const response = await fetch('/api/dashboard', {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
      },
    });
    const data = await response.json();

    // const { data } = await axiosConfigWithAuth.request({
    //   method: 'GET',
    //   url: '/api/dashboard',
    // });

    return data;
  } catch (error) {
    console.log(error);
  }
};

const useGetDashboardData = () => {
  const query = useQuery({
    queryKey: ['fetchDashboardData'],
    queryFn:() => fetchDashboardData(),
  });

  return {
    data: query.data,
    isLoading: query.isLoading,
    isError: query.isError,
  };
};

export {
  useGetDashboardData,
};
