import { InputField } from 'components/form-related';
import {
  useState,
  useEffect,
  useRef } from 'react';

const AddTransactionModal = ({ isModalVisible }) => {
  const dialogRef = useRef(null);

  useEffect(() => {
    if (dialogRef.current?.open && !isModalVisible) {
      dialogRef.current?.close();
    } else if (!dialogRef.current?.open && isModalVisible) {
      dialogRef.current?.showModal();
    }
  }, [isModalVisible]);

  return (
    <dialog ref={dialogRef} className={'add-transaction-modal'}>
      <form>
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
