import { useEffect, useState } from 'react';
import {
  PageTitle,
} from 'components/form-related';

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

  // TODO: fetch accounts
  useEffect(() => {
    const loadData = () =>{
      const profileDataRaw = JSON.parse(localStorage.getItem('userData'));

      setLoading(false);
      setProfileData(profileDataRaw);
    };

    loadData();
  }, [isEditing, currentTile, isLoading]);

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
        <ProfileAccountDisplay account={profileData.account} />;

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
