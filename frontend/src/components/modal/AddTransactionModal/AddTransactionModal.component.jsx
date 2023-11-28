import {
  useEffect,
  useRef,
} from 'react';
import { InputField } from 'components/form-related';
import './AddTransactionModal.styles.css';

const AddTransactionModal = ({ isModalVisible }) => {
  const dialogRef = useRef(null);

  useEffect(() => {
    console.log(isModalVisible);
    if (dialogRef.current?.open && !isModalVisible) {
      dialogRef.current?.close();
    } else if (!dialogRef.current?.open && isModalVisible) {
      dialogRef.current?.showModal();
    }
  }, [isModalVisible]);

  return (
    <dialog className={'add-transaction-modal'} ref={dialogRef}>
      <form>
        <InputField />
        <InputField />
        <InputField />
        <InputField />
        <InputField />
        <InputField />
        <InputField />
        <InputField />
      </form>
    </dialog>
  );
};

export default AddTransactionModal;
