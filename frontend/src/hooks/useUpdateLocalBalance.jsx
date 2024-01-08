import { useUser } from '@src/context/UserContext.jsx';

const useUpdateLocalBalance = () => {
  const { user, setUser } = useUser();

  const updateLocalBalance = (amount, direction) => {
    const newUser = { ...user };
    console.log(direction);

    if (direction) {
      newUser.actualBalance += Number(amount) * -1;
      newUser.savingsBalance += Number(amount);
      console.log(newUser);
    } else {
      newUser.actualBalance += Number(amount) * -1;
      newUser.savingsBalance += Number(amount);
      console.log(newUser);
    }

    setUser(newUser);
  };


  return {
    updateLocalBalance,
  };
};

export {
  useUpdateLocalBalance,
};
