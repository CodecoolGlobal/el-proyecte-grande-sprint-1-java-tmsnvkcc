import { useEffect, useState } from 'react';
import {
  PageTitle,
} from 'components/form-related';

import { 
  ProfilePageDisplay,
  ProfilePageEdit,
} from 'components/profile';

import './Profile.styles.css';

const Profile = () =>{
  const [editProfile, setEditProfile] = useState(false);
  const [profileData, setProfileData] = useState({});

  useEffect(() => {
    const profileDataRaw = JSON.parse(localStorage.getItem('userData'));
    setProfileData(profileDataRaw);
  }, []);

  const onHandleClickEdit = () =>{
    setEditProfile(true);
  }

  const renderFormComponent = () =>{
    return editProfile ? <ProfilePageEdit /> : <ProfilePageDisplay profileData={profileData} clickHandler={onHandleClickEdit} />; 
  }

  return (
    <div className={'profile-page'}>
      <PageTitle title={'Profile'} />
      {renderFormComponent()}
    </div>
  )
};

export default Profile;
