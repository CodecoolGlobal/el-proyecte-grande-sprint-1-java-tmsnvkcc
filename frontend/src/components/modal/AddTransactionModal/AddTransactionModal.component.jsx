import {
  useEffect,
  useRef,
  useState,
} from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  FormError,
  InputField,
  SelectField,
  SingleCheckbox,
  SubmitButton,
} from 'components/form-related';
import { iconLibraryConfig } from 'config';
import { useHandleFormOnSubmit } from './AddTransactionModal.hooks.jsx';
import './AddTransactionModal.styles.css';

const AddTransactionModal = ({ isModalVisible, handleOnClick, handleOnKeyClose }) => {
  const { loading, errorMessage, onSubmit } = useHandleFormOnSubmit();
  const [options, setOptions] = useState([]);
  const dialogRef = useRef(null);

  useEffect(() => {
    const userData = JSON.parse(localStorage.getItem('userData'));

    setOptions(userData.category);
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
      onKeyDown={(event) => handleOnKeyClose(event)}
    >
      <button className={'close-x-button'} onClick={handleOnClick}>X</button>
      <form
        id={'addTransactionForm'}
        onSubmit={(event) => onSubmit(event)}
      >
        <InputField
          type={'text'}
          id={'description'}
          labelContent={'Description'}
          placeholder={'Add a short optional description'}
        />
        <InputField
          type={'number'}
          id={'amount'}
          labelContent={'Transaction amount'}
          placeholder={'Add amount'}
        />
        <InputField
          type={'date'}
          id={'dateOfTransaction'}
          labelContent={'Date of Transaction'}
        />
        <SingleCheckbox
          id={'isRecurring'}
          labelContent={'Is this a monthly recurring item?'}
        />
        <SelectField
          id={'categoryId'}
          options={options}
          labelContent={'Category'}
          defaultValue={'select a category'}
        />
        {!loading ?
          <article>
            <SubmitButton />
            {errorMessage && <FormError errorMessage={errorMessage} />}
          </article> :
          <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'transaction-loading-icon'} />}
      </form>
    </dialog>
  );
};

export default AddTransactionModal;
