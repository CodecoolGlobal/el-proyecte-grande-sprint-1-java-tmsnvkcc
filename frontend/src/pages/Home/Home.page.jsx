import {
  Login,
  PasswordReset,
  Register,
} from 'components/home';
import { useState } from 'react';
import './Home.styles.css';

const Home = () => {
  const [activeForm, setActiveForm] = useState('login');

  const handleOnClick = (component) => {
    setActiveForm(component);
  };

  const renderFormComponent = () => {
    switch (activeForm) {
    case 'login':
      return <Login clickHandler={handleOnClick} />;

    case 'register':
      return <Register clickHandler={handleOnClick} />;

    case 'reset':
      return <PasswordReset clickHandler={handleOnClick} />;

    default:
      return <Login clickHandler={handleOnClick} />;
    }
  };

  return (
    <>
      {renderFormComponent()}
    </>
  );
};

export default Home;
