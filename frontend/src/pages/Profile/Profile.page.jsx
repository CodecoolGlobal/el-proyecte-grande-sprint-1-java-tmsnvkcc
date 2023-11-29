import { useEffect, useState } from 'react';
import {
  PageTitle,
} from 'components/form-related';

import {
  ProfilePageDisplay,
  ProfilePageEdit,
  ProfileNavigationComponent,
} from 'components/profile';

import './Profile.styles.css';

const Profile = () => {
  const [editProfile, setEditProfile] = useState(false);
  const [profileData, setProfileData] = useState({});
  const [isLoading, setLoading] = useState(true);
  const [currentTile, setCurrentTile] = useState('Profile');

  useEffect(() => {
    const loadData = () =>{
      const profileDataRaw = JSON.parse(localStorage.getItem('userData'));

      setLoading(false);
      setProfileData(profileDataRaw);
    };

    loadData();
  }, [editProfile, currentTile, isLoading]);

  const onEditHandler = () => {
    setEditProfile(!editProfile);
  };

  const renderFormComponent = () => {
    switch (currentTile) {
    case 'Profile':
      return editProfile ?
        <ProfilePageEdit profileData={profileData} editHandler={onEditHandler} /> :
        <ProfilePageDisplay profileData={profileData} onEditHandler={onEditHandler} loading={isLoading} />;

    case 'Account':
      return 'account';

    default:
      return 'error';
    }

  };

  const handleClick = (tileName) => {
    setCurrentTile(tileName);
  };


  return (
    <div className={'profile-page'}>
      <PageTitle title={'Profile'} />
      <ProfileNavigationComponent clickHandler={handleClick} />
      {renderFormComponent()}
    </div>
  );
};

export default Profile;
