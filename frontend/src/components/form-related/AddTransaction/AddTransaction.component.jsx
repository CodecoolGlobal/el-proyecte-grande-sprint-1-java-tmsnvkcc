import './AddTransaction.styles.css';
import { InputField } from '../index.js';

const AddTransaction = () => {
  return (
    <>
      <button className={'add-transaction'} type={'button'}>ADD TRANSACTION</button>
      <modal className={'add-transaction-modal'}>
        <InputField />
        <InputField />
        <InputField />
        <InputField />
        <InputField />
      </modal>
    </>
  );
};

export default AddTransaction;
