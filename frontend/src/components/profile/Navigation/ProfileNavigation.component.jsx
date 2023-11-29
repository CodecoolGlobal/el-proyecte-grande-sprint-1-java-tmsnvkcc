import './ProfileNavigation.styles.css';


const ProfileNavigationComponent = () => {

  return (
    <>
      <div className={'profile-navigation'}>
        <button
          className={'profile-page-selector'}
          type='button'
        >Profile</button>

        <button
          className={'profile-page-selector'}
          type='button'
        >Account</button>
      </div>
    </>
  );
};

export default ProfileNavigationComponent;
