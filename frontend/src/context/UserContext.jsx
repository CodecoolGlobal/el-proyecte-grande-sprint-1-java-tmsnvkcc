import {
  createContext,
  useContext,
  useEffect,
  useState,
} from 'react';

const UserContext = createContext({});

const getToken = () => window.localStorage.getItem('token');
const setToken = (token) => window.localStorage.setItem('token', token);

const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  const getMe = async (token) => {
    try {
      const response = await fetch('/api/users/me', {
        method: 'GET',
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      const data = await response.json();

      setUser({ userId: data.userId, email: data.email });
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

    getMe(token);
  }, []);

  return (
    <UserContext.Provider value={{ user, setUser }}>
      {!loading && children}
    </UserContext.Provider>
  );
};

export const useUser = () => useContext(UserContext);

export default UserProvider;
