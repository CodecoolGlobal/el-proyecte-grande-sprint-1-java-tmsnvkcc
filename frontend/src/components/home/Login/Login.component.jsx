import './Login.styles.css';
import { InputField } from 'components/form-related';

const Login = () => {
  return (
    <section>
      <form id={'login-form'}>
        <InputField
          labelTag={'email'}
          labelContent={'Email'}
          type={'email'}
          placeholder={'Enter email address...'}
        />
        <InputField
          labelTag={'password'}
          labelContent={'Password'}
          type={'password'}
          placeholder={'Enter password...'}
        />
        <InputField
          labelTag={'password-repeat'}
          labelContent={'Password Repeat'}
          type={'password'}
          placeholder={'Repeat password...'}
        />
      </form>
    </section>
  );
};

export default Login;
