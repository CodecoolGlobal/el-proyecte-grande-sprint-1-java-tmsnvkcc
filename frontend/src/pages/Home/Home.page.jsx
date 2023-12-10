import { useState } from 'react';
import {
  Login,
  Register,
  RequestPasswordReset,
} from 'components/home';
import { PasswordResetConfirmationModal } from 'components/modal';
import './Home.styles.css';

const Home = () => {
  const [activeForm, setActiveForm] = useState('login');
  const [isModalVisible, setIsModalVisible] = useState(false);

  const handleModalVisibilityOnClick = () => {
    setIsModalVisible(!isModalVisible);
  };

  const listenForEscapeKey = (event) => {
    if (event.key === 'Escape') {
      setIsModalVisible(false);
    }
  };

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
      return <RequestPasswordReset clickHandler={handleOnClick} handleModal={handleModalVisibilityOnClick} />;

    default:
      return <Login clickHandler={handleOnClick} />;
    }
  };

  return (
    <main className={!isModalVisible ? '' : 'blur'}>
      {renderFormComponent()}
      <PasswordResetConfirmationModal
        isModalVisible={isModalVisible}
        handleOnKeyClose={listenForEscapeKey}
        handleOnClick={handleModalVisibilityOnClick}
      />
    </main>
  );
};

export default Home;
