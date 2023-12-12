import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUser } from 'context/UserContext.jsx';
import { Layout } from '../index.js';

const Protected = ({ children }) => {
  const { user } = useUser();
  const navigate = useNavigate();

  useEffect(() => {
    if (!user) {
      navigate('/');
    }
  }, []);

  return (
    <>
      {/*{user && children}*/}
      <Layout />
    </>
  );
};

export default Protected;
