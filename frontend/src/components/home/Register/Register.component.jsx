import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useHandleFormSubmit } from 'hooks';
import {
  FormSwapButton,
  Title,
} from 'components/home';
import {
  FormError,
  InputField,
  SubmitButton,
} from 'components/form-related';
import { iconLibrary } from 'config';
import './Register.styles.css';

const Register = ({ clickHandler }) => {
  const { loading, errorMessage, handleOnSubmit } = useHandleFormSubmit();

  return (
    <section className={'register-form-container'}>
      <Title title={'Who are you?'} />
      <form id={'register-form'} onSubmit={(event) => handleOnSubmit({ apiUrl: '/api/user/register', method: 'POST', navigateUrl: '/' }, event)}>
        <InputField
          id={'register-email'}
          labelContent={'Email'}
          type={'email'}
          placeholder={'Enter your email address...'}
        />
        <InputField
          id={'register-password'}
          labelContent={'Password'}
          type={'password'}
          placeholder={'Enter your password...'}
        />
        <InputField
          id={'register-password-repeat'}
          labelContent={'Password Repeat'}
          type={'password'}
          placeholder={'Repeat your password...'}
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
        <FormSwapButton buttonName={'login'} buttonContent={'Sign in'} clickHandler={clickHandler} />
      </article>
    </section>
  );
};

export default Register;
