import { useEffect, useState } from 'react';
import {
  PageTitle,
} from 'components/form-related';

import getProfileAccounts from './Profile.page.hooks';

import {
  ProfilePageDisplay,
  ProfilePageEdit,
  ProfileNavigationComponent,
  ProfileAccountDisplay,
  ProfileAccountEdit,
} from 'components/profile';

import './Profile.styles.css';

const Profile = () => {
  const [isEditing, setEditing] = useState(false);
  const [profileData, setProfileData] = useState({});
  const [isLoading, setLoading] = useState(true);
  const [currentTile, setCurrentTile] = useState('Profile');

  const { accountData, isAccountLoading, isAccountError, refetch } = getProfileAccounts();

  // TODO: fetch accounts
  useEffect(() => {
    refetch();
  }, []);

  const onEditHandler = () => {
    setEditing(!isEditing);
  };

  const handleClick = (tileName) => {
    setCurrentTile(tileName);
    setEditing(false);
  };


  const renderFormComponent = () => {
    switch (currentTile) {
    case 'Profile':
      return isEditing ?
        <ProfilePageEdit profileData={profileData} editHandler={onEditHandler} /> :
        <ProfilePageDisplay profileData={profileData} onEditHandler={onEditHandler} loading={isLoading} />;

    case 'Account':
      return isEditing ?
        <ProfileAccountEdit /> :
        <ProfileAccountDisplay account={accountData} />;

    default:
      return 'error';
    }

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
