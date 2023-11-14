import { useNavigate } from 'react-router-dom';

const NavbarButton = ({ buttonLabel, destinationPath }) => {
  const nav = useNavigate();
  const handleButtonClick = () => nav(destinationPath);

  return (
    <button onClick={handleButtonClick}>{buttonLabel}</button>
  );
};

export default NavbarButton;
