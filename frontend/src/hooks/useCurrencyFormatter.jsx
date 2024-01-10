const useCurrencyFormatter = () => {
  const formatCurrency = (currency) => {
    return currency.toLocaleString('hu-HU', {
      style: 'currency',
      currency: 'HUF',
      maximumFractionDigits: 0
    });

  };

  return {
    formatCurrency,
  };
};

export default useCurrencyFormatter;
