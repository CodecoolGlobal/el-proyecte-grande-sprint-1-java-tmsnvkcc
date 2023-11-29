import { useState } from 'react';
import { useMutation } from '@tanstack/react-query';
import { axiosConfig } from 'config';
import { serialiseFormData } from 'utilities';

const useHandleFormOnSubmit = () => {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const { mutate, reset } = useMutation({
    mutationKey: ['addTransactionForm'],
    mutationFn: async ({ payload }) => {
      setLoading(true);

      const response = await axiosConfig.request({
        method: 'POST',
        url: '/api/transaction/add/external-transaction',
        data: payload,
      });

      return response;
    },
    onSuccess: () => {
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
    const userData = JSON.parse(localStorage.getItem('userData')); // TODO - update this once userContext has been set up.
    const payload = serialiseFormData(event.target);

    if (payload.amount === '' || payload.category === '' || payload.dateOfTransaction === '') {
      setErrorMessage('Make sure to fill in all mandatory fields (amount, category, date) before submitting the form.');

      return;
    }

    payload.userId = userData.userId;
    payload.accountId = userData.account.id;
    payload.isRecurring = payload.isRecurring === 'on';
    payload.isPlanned = Date.parse(payload.dateOfTransaction) > Date.now();

    console.log(payload);

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
