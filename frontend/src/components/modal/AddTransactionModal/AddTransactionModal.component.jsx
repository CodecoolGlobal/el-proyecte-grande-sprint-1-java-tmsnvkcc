import {
  useEffect,
  useRef,
} from 'react';
import {
  InputField,
  SelectField,
  SingleCheckbox,
  SubmitButton,
} from 'components/form-related';
import './AddTransactionModal.styles.css';

const AddTransactionModal = ({ isModalVisible, handleOnKeyclose }) => {
  const dialogRef = useRef(null);

  useEffect(() => {
    if (dialogRef.current?.open && !isModalVisible) {
      dialogRef.current?.close();
    } else if (!dialogRef.current?.open && isModalVisible) {
      dialogRef.current?.showModal();
    }
  }, [isModalVisible]);

  return (
    <dialog
      className={'add-transaction-modal'}
      ref={dialogRef}
      onKeyDown={(event) => handleOnKeyclose(event)}
    >
      <form>
        <InputField
          type={'text'}
          id={'transactionDescription'}
          labelContent={'Description'}
          placeholder={'Add a short optional description'}
        />
        <InputField
          type={'number'}
          id={'transactionAmount'}
          labelContent={'Transaction amount'}
          placeholder={'Add the transaction amount'}
        />
        <InputField
          type={'date'}
          id={'transactionDate'}
          labelContent={'Date of Transaction'}
        />
        <SingleCheckbox
          id={'isRecurring'}
          labelContent={'Is this a monthly recurring item?'}
        />
        <SelectField
          id={'transactionCategory'}
          defaultValue={''}
          options={['test2', 'test3']} // TODO - remove hardcoded values with actual data
          labelContent={'Choose category'}
        />
        <SubmitButton />
      </form>
    </dialog>
  );
};


export default AddTransactionModal;
