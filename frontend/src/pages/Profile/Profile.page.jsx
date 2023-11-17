import axios from "axios";
import { useEffect, useState } from "react";
import {
  PageTitle,
} from 'components/form-related';
import './Profile.styles.css';

const Profile = () =>{
  const [loading, setLoading] = useState(false);
  const [profileData, setProfileData] = useState(null);

  if (loading) {
    return 'load data';
  }

  useEffect(() => {
    const fetchData = async() =>{
      await axios.get('/api/user/')
        .then((response) => {
          setProfileData(response.data);
          setLoading(false);
        })
        .catch((error) => {
          console.log(error);
        });
    }

    fetchData();
  }, []);

  return (
    <div className={'profile-page'}>
      <PageTitle title={"Profile"} />
      {profileData}
    </div>
  )
};

export default Profile;
