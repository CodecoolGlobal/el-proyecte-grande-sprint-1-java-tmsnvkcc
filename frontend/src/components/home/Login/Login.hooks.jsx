import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useMutation } from '@tanstack/react-query';
import { axiosConfig } from 'config';
import { useUser } from 'context/UserContext.jsx';
import { serialiseFormData } from 'utilities';

const useHandleFormOnSubmit = () => {
  const { setUser } = useUser();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate } = useMutation({
    mutationKey: ['loginForm'],
    mutationFn: async ({ payload }) => {
      setLoading(true);

      const response = await axiosConfig.request({
        method: 'POST',
        url: '/api/users/login',
        data: payload,
      });

      return response;
    },
    onSuccess: ({ data }) => {
      const userData = {
        userId: data.id,
        userName: data.userName,
        email: data.email,
        dateOfReg: data.dateOfRegistration,
        category: data.categories,
        account: data.accountData,
      };

      window.localStorage.setItem('userData', JSON.stringify(userData));
      setUser({ userId: userData.userId, email: userData.email });
      window.localStorage.setItem('token', data.jwtResponse.jwt);

      setLoading(false);
      navigate('/dashboard');
    },
    onError: (error) => {
      setErrorMessage(error.response.data.message);
      setLoading(false);
    },
  });

  const onSubmit = (event) => {
    event.preventDefault();
    const payload = serialiseFormData(event.target);

    if (payload.loginEmail === '' || payload.loginPassword === '') {
      setErrorMessage('Make sure to fill in all fields before submitting the form.');

      return;
    }

    mutate({ payload });
  };

  return {
    loading,
    errorMessage,
    onSubmit,
  };
};

export {
  useHandleFormOnSubmit,
};
