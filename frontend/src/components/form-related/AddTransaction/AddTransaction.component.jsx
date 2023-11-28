import './AddTransaction.styles.css';

const AddTransaction = ({ onClick }) => {
  return (
    <button
      className={'add-transaction'}
      type={'button'}
      onClick={onClick}
    >ADD TRANSACTION</button>
  );
};

export default AddTransaction;
