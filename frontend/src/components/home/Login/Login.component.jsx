import { InputField } from 'components/form-related';
import './Login.styles.css';

const Login = () => {
  return (
    <section className={'login-form-container'}>
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
      </form>
    </section>
  );
};

export default Login;
