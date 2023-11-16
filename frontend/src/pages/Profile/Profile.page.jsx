import axios from "axios";
import { useEffect, useState } from "react";


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
    <>
      {profileData}
    </>
  )
};

export default Profile;
