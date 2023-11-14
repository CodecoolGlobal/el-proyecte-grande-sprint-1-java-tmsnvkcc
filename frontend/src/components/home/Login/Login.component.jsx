import {
  FormError,
  InputField,
  SubmitButton,
} from 'components/form-related';
import { useHandleFormSubmit } from 'hooks';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import './Login.styles.css';
import { iconLibrary } from 'config';

const Login = () => {
  const { loading, error, handleOnSubmit } = useHandleFormSubmit();

  return (
    <section className={'login-form-container'} onSubmit={(event) => handleOnSubmit('/api/user/login', event)}>
      <h1>Who are you?</h1>
      <form id={'login-form'}>
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
            {error && <FormError errorMessage={error} />}
          </article> :
          <FontAwesomeIcon icon={iconLibrary.faCircleNotch} spin className={'loading-icon'} />}
      </form>
    </section>
  );
};

export default Login;
