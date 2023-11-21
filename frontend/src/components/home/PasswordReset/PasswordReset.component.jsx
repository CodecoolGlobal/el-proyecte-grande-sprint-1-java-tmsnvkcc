import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  FormError,
  InputField,
  SubmitButton,
} from 'components/form-related';
import {
  FormSwapButton,
  Title,
} from 'components/home';
import { useHandleFormSubmit } from 'hooks';
import { iconLibraryConfig } from 'config';
import './PasswordReset.styles.css';

const PasswordReset = ({ clickHandler }) => {
  const { loading, errorMessage, handleOnSubmit } = useHandleFormSubmit();

  return (
    <>
      <Title title={'Who are you?'} />
      <form
        id={'reset-form'}
        onSubmit={(event) => handleOnSubmit({ apiUrl: '/api/users/password-reset', method: 'PUT', navigateUrl: '/' }, event)}
      >
        <InputField
          id={'reset-email'}
          labelContent={'Email'}
          type={'email'}
          placeholder={'Enter your email address...'}
        />
        {!loading ?
          <article>
            <SubmitButton />
            {errorMessage && <FormError errorMessage={errorMessage} />}
          </article> :
          <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />}
      </form>
      <article className={'bottom-button-container'}>
        <FormSwapButton buttonName={'login'} buttonContent={'Sign in'} clickHandler={clickHandler} />
        <FormSwapButton buttonName={'register'} buttonContent={'Create account'} clickHandler={clickHandler} />
      </article>
    </>
  );
};

export default PasswordReset;
