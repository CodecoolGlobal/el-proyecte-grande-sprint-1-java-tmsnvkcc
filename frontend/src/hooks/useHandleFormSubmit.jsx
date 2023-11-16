import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { serialiseFormData } from 'utilities';

const useHandleFormSubmit = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleOnSubmit = async ({ apiUrl, method, navigateUrl }, event) => {
    event.preventDefault();

    try {
      setLoading(true);

      const payload = serialiseFormData(event.target);
      const response = await fetch(apiUrl, {
        method,
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(payload),
      });
      const data = await response.json();

      if (!response.ok) {
        setErrorMessage(data.message);
      }

      if (response.ok) {
        navigate(navigateUrl);
      }

    } catch (error) {
      setErrorMessage(error.message);
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

export default useHandleFormSubmit;
