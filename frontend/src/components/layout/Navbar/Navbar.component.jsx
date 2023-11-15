import './Navbar.styles.css';
import { NavbarButtonComponent } from '../index.js';

const Navbar = () => {
  return (
    <nav>
        <NavbarButtonComponent buttonLabel='Dashboard' destinationPath='/dashboard'/>
        <NavbarButtonComponent buttonLabel='Track' destinationPath='/track'/>
        <NavbarButtonComponent buttonLabel='Insights' destinationPath='/insights'/>
        <NavbarButtonComponent buttonLabel='Profile' destinationPath='/profile'/>
    </nav>
  );
};

export default Navbar;
