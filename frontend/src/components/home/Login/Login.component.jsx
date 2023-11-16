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
import { iconLibrary } from 'config';
import './Login.styles.css';

const Login = ({ clickHandler }) => {
  const { loading, errorMessage, handleOnSubmit } = useHandleFormSubmit();

  return (
    <>
      <Title title={'Who are you?'} />
      <form
        id={'login-form'}
        onSubmit={(event) => handleOnSubmit({ apiUrl: '/api/user/login', method: 'POST', navigateUrl: '/dashboard' }, event)}
      >
        <InputField
          id={'login-email'}
          labelContent={'Email'}
          type={'email'}
          placeholder={'Enter your email address...'}
        />
        <InputField
          id={'login-password'}
          labelContent={'Password'}
          type={'password'}
          placeholder={'Enter your password...'}
        />
        {!loading ?
          <article>
            <SubmitButton />
            {errorMessage && <FormError errorMessage={errorMessage} />}
          </article> :
          <FontAwesomeIcon icon={iconLibrary.faCircleNotch} spin className={'loading-icon'} />}
      </form>
      <article className={'bottom-button-container'}>
        <FormSwapButton buttonName={'reset'} buttonContent={'Forgot password?'} clickHandler={clickHandler} />
        <FormSwapButton buttonName={'register'} buttonContent={'Create account'} clickHandler={clickHandler} />
      </article>
    </>
  );
};

export default Login;
