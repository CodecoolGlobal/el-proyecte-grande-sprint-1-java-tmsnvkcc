import { useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import { axiosConfig } from '@src/config';
import { serialiseFormData } from '@src/utilities';

const useHandleFormOnSubmit = (handleOnClick, transactionDirection) => {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate, reset } = useMutation({
    mutationKey: ['addTransactionForm'],
    mutationFn: async ({ payload }) => {
      setLoading(true);

      await axiosConfig.request({
        method: 'POST',
        url: '/api/transaction/add/local-transaction',
        data: payload,
      });
    },
    onSuccess: () => {
      reset();
      setLoading(false);

      handleOnClick();
    },
    onError: (error) => {
      setErrorMessage(error.response.data.message);
      setLoading(false);
    },
  });

  const onSubmit = (event) => {
    event.preventDefault();
    const payload = serialiseFormData(event.target);

    if (payload.amount === '' || payload.dateOfTransaction === '') {
      setErrorMessage('Make sure to fill in all mandatory fields (amount, date, category) before submitting the form.');

      return;
    }

    if (transactionDirection === 'expense') {
      payload.amount *= -1;
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
