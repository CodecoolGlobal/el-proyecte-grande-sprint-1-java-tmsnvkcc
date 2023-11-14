import {
  Login,
  Register,
} from 'components/home';
import { useState } from 'react';

const Home = () => {
  const [isLoginFormShown, setIsLoginFormShown] = useState(true);

  const handleOnClick = () => {
    setIsLoginFormShown(!isLoginFormShown);
  };

  return (
    <>
      {isLoginFormShown ? <Login /> : <Register />}
      <button type={'button'} onClick={handleOnClick}>{isLoginFormShown ? 'Register' : 'Login'}</button>
    </>
  );
};

export default Home;
