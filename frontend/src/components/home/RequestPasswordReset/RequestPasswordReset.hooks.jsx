import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { axiosConfig } from '../../../config/index.js';
import { serialiseFormData } from '../../../utilities/index.js';

const useHandleFormOnSubmit = (handleModal) => {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate, reset } = useMutation({
    mutationKey: ['forgottenPasswordForm'],
    mutationFn: async ({ payload }) => {
      setLoading(true);

      const response = await axiosConfig.request({
        method: 'POST',
        url: '/api/users/password-reset',
        data: payload,
      });

      return response;
    },
    onSuccess: () => {
      reset();
      setLoading(false);
      handleModal();
    },
    onError: (error) => {
      setErrorMessage(error.response.data.message);
      setLoading(false);
    },
  });

  const onSubmit = (event) => {
    event.preventDefault();
    const payload = serialiseFormData(event.target);

    if (payload.resetEmail === '') {
      setErrorMessage('Make sure to fill in the email field before submitting the form.');

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
