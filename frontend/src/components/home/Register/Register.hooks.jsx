import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { axiosConfig } from 'config';
import { serialiseFormData } from 'utilities';

const useHandleFormOnSubmit = () => {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate, reset } = useMutation({
    mutationKey: ['registerUser'],
    mutationFn: async (event) => {
      setLoading(true);
      const payload = serialiseFormData(event.target);

      if (payload.registerEmail === '' || payload.registerPassword === '' || payload.registerPasswordRepeat === '') {
        setErrorMessage('Make sure to fill in all fields before submitting the form.');

        return;
      }

      if (payload.registerPassword !== payload.registerPasswordRepeat) {
        setErrorMessage('Make sure that you have entered the same password twice.');

        return;
      }

      delete payload.registerPasswordRepeat;

      const response = await axiosConfig.request({
        method: 'POST',
        url: '/api/users/register',
        data: payload,
      });

      return response;
    },
    onSuccess: () => {
      reset();
      setLoading(false);
      window.location.reload();
    },
    onError: (error) => {
      setErrorMessage(error.response.data.message);
      setLoading(false);
    },
  });

  const onSubmit = (event) => {
    event.preventDefault();
    mutate(event);
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
