import { useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import { axiosConfig } from 'config';
import { serialiseFormData } from 'utilities';

const useHandleFormOnSubmit = (editHandler) => {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate, reset } = useMutation({
    mutationKey: ['profileEdit'],
    mutationFn: async ({ payload }) => {
      setLoading(true);

      const response = await axiosConfig.request({
        method: 'PUT',
        url: '/api/users/update-profile',
        data: payload,
      });

      const userData = {
        userId: response.data.id,
        userName: response.data.userName,
        email: response.data.email,
        dateOfReg: response.data.dateOfRegistration,
        account: response.data.accountData,
      };

      localStorage.setItem('userData', JSON.stringify(userData));

      return response;
    },
    onSuccess: () => {
      editHandler();
      reset();
      setLoading(false);
    },
    onError: (error) => {
      setErrorMessage(error.response.data.message);
      setLoading(false);
    },
  });

  const onSubmit = (event) => {
    event.preventDefault();
    const payload = serialiseFormData(event.target);

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
