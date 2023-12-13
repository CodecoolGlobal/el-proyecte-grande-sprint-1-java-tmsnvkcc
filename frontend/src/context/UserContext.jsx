import {
  createContext,
  useContext,
  useEffect,
  useState,
} from 'react';
import { axiosConfigWithAuth } from 'config';

const UserContext = createContext({});

const getToken = () => window.localStorage.getItem('token');

const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const getMe = async () => {
    try {
      const data = await axiosConfigWithAuth.request({
        method: 'GET',
        url: '/api/users/me',
      });

      setUser({ email: data.data.username });
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const token = getToken();

    if (!token) {
      setLoading(false);

      return;
    }

    getMe();
  }, []);

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {!loading && children}
    </UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);

export default UserProvider;
