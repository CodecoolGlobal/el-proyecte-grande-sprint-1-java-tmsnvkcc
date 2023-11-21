import { useState } from 'react';
import { axiosConfig } from 'config';
import { serialiseFormData } from 'utilities';

const useHandleFormSubmit = () => {
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleOnSubmit = async ({ apiUrl, method }, event) => {
    event.preventDefault();
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

    try {
      setLoading(true);
      setErrorMessage('');

      await axiosConfig.request({
        method,
        url: apiUrl,
        data: payload,
      });

      window.location.reload();
    } catch (error) {
      setErrorMessage(error.response.data.message);
    } finally {
      setLoading(false);
    }
  };

  return {
    loading,
    errorMessage,
    handleOnSubmit,
  };
};

export {
  useHandleFormSubmit,
};
