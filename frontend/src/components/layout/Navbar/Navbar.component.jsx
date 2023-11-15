import './Navbar.styles.css';
import { NavbarButtonComponent } from '../index.js';

const Navbar = () => {
  return (
    <nav>
      <NavbarButtonComponent buttonLabel='Track' destinationPath='/track'/>
    </nav>
  );
};

export default Navbar;
