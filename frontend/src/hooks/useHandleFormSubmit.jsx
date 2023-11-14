import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { serialiseFormData } from 'utilities';

const useHandleFormSubmit = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleOnSubmit = async ({ apiUrl, navigateUrl }, event) => {
    event.preventDefault();

    try {
      setLoading(true);

      const payload = serialiseFormData(event.target);
      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(payload),
      });
      const data = await response.json();

      if (!response.ok) {
        setError(data.errorMessage);
      }

      if (response.ok) {
        navigate(navigateUrl);
      }
    } catch (error) {
      console.error(error);
      setError('An unexpected error has happened. We ask you to refresh your browser and try again.');
    } finally {
      setLoading(false);
    }
  };

  return {
    loading,
    error,
    handleOnSubmit,
  };
};

export default useHandleFormSubmit;
