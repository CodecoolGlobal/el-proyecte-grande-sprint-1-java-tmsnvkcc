import {
  FormSwapButton,
} from 'components/home';

const ProfilePageDisplay = ({ profileData, onEditHandler }) => {
  return <>
    <div>
      <label>{profileData.userId}</label>
    </div>

    <div>
      <label>{profileData.userName}</label>
    </div>

    <div>
      <label>{profileData.email}</label>
    </div>

    <article className={'bottom-button-container'}>
      <FormSwapButton buttonName={'editProfile'} buttonContent={'Edit'} clickHandler={onEditHandler} />
    </article>
  </>
};

export default ProfilePageDisplay;
