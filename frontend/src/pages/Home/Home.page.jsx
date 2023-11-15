import {
  Login,
  Register,
} from 'components/home';
import { useState } from 'react';
import './Home.styles.css';

const Home = () => {
  const [activeForm, setActiveForm] = useState('login');

  const handleOnClick = (component) => {
    setActiveForm(component);
  };

  if (activeForm === 'login') {
    return (
      <Login clickHandler={handleOnClick} />
    );
  }

  if (activeForm === 'register') {
    return (
      <Register clickHandler={handleOnClick} />
    );
  }

  if (activeForm === 'reset') {
    return (
      <Register clickHandler={handleOnClick} />
    );
  }
};

export default Home;
