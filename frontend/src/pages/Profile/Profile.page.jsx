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

  useEffect(() => {
    const loadData = () =>{
      const profileDataRaw = JSON.parse(localStorage.getItem('userData'));

      setLoading(false);
      setProfileData(profileDataRaw);
    };

    loadData();
  }, [editProfile, isLoading]);

  const onEditHandler = () => {
    setEditProfile(!editProfile);
  };

  const renderFormComponent = () => {
    return editProfile ? <ProfilePageEdit profileData={profileData} editHandler={onEditHandler} /> : <ProfilePageDisplay profileData={profileData} onEditHandler={onEditHandler} loading={isLoading} />;
  };

  return (
    <div className={'profile-page'}>
      <PageTitle title={'Profile'} />
      <ProfileNavigationComponent />
      {renderFormComponent()}
    </div>
  );
};

export default Profile;
