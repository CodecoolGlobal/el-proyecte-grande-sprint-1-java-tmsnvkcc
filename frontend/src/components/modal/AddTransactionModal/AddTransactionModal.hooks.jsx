import { useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import { axiosConfig } from 'config';
import { serialiseFormData } from 'utilities';

const useHandleFormOnSubmit = (handleOnClick) => {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate, reset } = useMutation({
    mutationKey: ['addTransactionForm'],
    mutationFn: async ({ payload }) => {
      setLoading(true);

      await axiosConfig.request({
        method: 'POST',
        url: '/api/transaction/add/external-transaction',
        data: payload,
      });

      return payload;
    },
    onSuccess: (payload) => {
      reset();
      setLoading(false);
      handleOnClick();

      const userData = JSON.parse(localStorage.getItem('userData'));

      userData.account.actualBalance = userData.account.actualBalance + parseInt(payload.amount);
      localStorage.setItem('userData', JSON.stringify(userData));
    },
    onError: (error) => {
      setErrorMessage(error.response.data.message);
      setLoading(false);
    },
  });

  const onSubmit = (event) => {
    event.preventDefault();
    const payload = serialiseFormData(event.target);

    if (payload.amount === '' || payload.dateOfTransaction === '' || payload.categoryId === undefined) {
      setErrorMessage('Make sure to fill in all mandatory fields (amount, date, category) before submitting the form.');

      return;
    }

    payload.isRecurring = payload.isRecurring === 'on';
    payload.isPlanned = Date.parse(payload.dateOfTransaction) > Date.now();

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
