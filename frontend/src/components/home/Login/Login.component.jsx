import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  FormError,
  InputField,
  SubmitButton,
} from 'components/form-related';
import { FormSwapButton } from 'components/home';
import { useHandleFormSubmit } from 'hooks';
import { iconLibrary } from 'config';
import './Login.styles.css';

const Login = ({ clickHandler }) => {
  const { loading, errorMessage, handleOnSubmit } = useHandleFormSubmit();

  return (
    <section className={'login-form-container'}>
      <h1>Who are you?</h1>
      <form id={'login-form'} onSubmit={(event) => handleOnSubmit({ apiUrl: '/api/user/login', method: 'POST', navigateUrl: '/dashboard' }, event)}>
        <InputField
          id={'email'}
          labelContent={'Email'}
          type={'email'}
          placeholder={'Enter your email address...'}
        />
        <InputField
          id={'password'}
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
        <FormSwapButton buttonName={'reset'} buttonContent={'Forgot email?'} clickHandler={clickHandler} />
        <FormSwapButton buttonName={'register'} buttonContent={'Create account'} clickHandler={clickHandler} />
      </article>
    </section>
  );
};

export default Login;
