import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  FormError,
  InputField,
  PasswordInputField,
  SubmitButton,
} from 'components/form-related';
import {
  FormSwapButton,
  Title,
} from 'components/home';
import { useHandleFormOnSubmit } from './Login.hooks.jsx';
import { iconLibraryConfig } from 'config';
import './Login.styles.css';

const Login = ({ clickHandler }) => {
  const { loading, errorMessage, onSubmit } = useHandleFormOnSubmit();

  return (
    <>
      <Title title={'Who are you?'} />
      <form
        id={'loginForm'}
        onSubmit={(event) => onSubmit(event)}
      >
        <InputField
          id={'loginEmail'}
          labelContent={'Email'}
          type={'email'}
          placeholder={'Enter your email address...'}
        />
        <PasswordInputField
          id={'loginPassword'}
          labelContent={'Password'}
          placeholder={'Enter your password...'}
        />
        {!loading ?
          <article>
            <SubmitButton />
            {errorMessage && <FormError errorMessage={errorMessage} />}
          </article> :
          <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />}
      </form>
      <article className={'bottom-button-container'}>
        <FormSwapButton buttonName={'reset'} buttonContent={'Forgot password?'} clickHandler={clickHandler} />
        <FormSwapButton buttonName={'register'} buttonContent={'Create account'} clickHandler={clickHandler} />
      </article>
    </>
  );
};

export default Login;
