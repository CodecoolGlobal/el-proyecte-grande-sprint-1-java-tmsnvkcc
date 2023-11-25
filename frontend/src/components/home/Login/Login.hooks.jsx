import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { axiosConfig } from 'config';
import { useNavigate } from 'react-router-dom';
import { serialiseFormData } from 'utilities';

const useHandleFormOnSubmit = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate, reset } = useMutation({
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
    onSuccess: () => {
      // TODO - add userData to userContext for persistent login
      reset();
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
