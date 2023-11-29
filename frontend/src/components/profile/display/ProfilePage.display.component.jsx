import {
  FormSwapButton,
} from 'components/home';

import './ProfilePage.display.styles.css';

const ProfilePageDisplay = ({ profileData, onEditHandler }) => {
  return <>
    <div className={'profile-page-overview'}>
      <div className={'overview-content'}>
        <h2>Username</h2>
        <label>{profileData.userName}</label>
      </div>

      <div className={'overview-content'}>
        <h2>Email address</h2>
        <label>{profileData.email}</label>
      </div>

      <article className={'bottom-button-container'}>
        <FormSwapButton buttonName={'editProfile'} buttonContent={'Edit'} clickHandler={onEditHandler} />
      </article>
    </div>
  </>;
};

export default ProfilePageDisplay;
