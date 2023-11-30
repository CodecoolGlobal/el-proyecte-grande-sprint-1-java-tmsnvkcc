import {
  FormSwapButton,
} from 'components/home';

import './ProfilePage.display.styles.css';
import { useEffect, useState } from 'react';

const ProfilePageDisplay = ({ onEditHandler, loading }) => {
  const [profileData, setProfileData] = useState({});

  useEffect(() => {
    if (loading) {
      setProfileData(JSON.parse(localStorage.getItem('userData')));
    }
  }, [loading]);

  //console.log(profileData);
  return <>
    {!loading &&
      <div className={'profile-page-overview'}>
        <div className={'overview-content'}>
          <h2>Username</h2>
          <label>{profileData.userName}</label>
        </div>

        <div className={'overview-content'}>
          <h2>Email address</h2>
          <label>{profileData.email}</label>
        </div>

        <div className={'line'}></div>

        <div className={'overview-content'}>
          <h2>Account name</h2>
          <label>{profileData.account.name}</label>
        </div>

        <div className={'line'}></div>

        <div className={'overview-content'}>
          <h2>Registration date</h2>
          <label>{profileData.dateOfReg.split('T')[0]}</label>
        </div>

        <article className={'bottom-button-container'}>
          <FormSwapButton buttonName={'editProfile'} buttonContent={'Edit'} clickHandler={onEditHandler} />
        </article>
      </div>
    }
  </>;
};

export default ProfilePageDisplay;
