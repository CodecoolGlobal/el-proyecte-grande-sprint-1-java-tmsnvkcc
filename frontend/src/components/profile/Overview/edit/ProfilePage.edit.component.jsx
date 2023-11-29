import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import {
  FormError,
  InputField,
  PasswordInputField,
  SubmitButton,
} from 'components/form-related';

import { useHandleFormOnSubmit } from './ProfilePage.hooks';
import { iconLibraryConfig } from 'config';

import './ProfilePage.edit.styles.css';


const ProfilePageEdit = ({ profileData, editHandler }) => {
  const { loading, errorMessage, onSubmit } = useHandleFormOnSubmit(editHandler);

  return <>
    <div className={'profile-page-edit'}>
      <form id='profileEdit' onSubmit={(event) => onSubmit(event)}>
        <InputField
          id={'username'}
          labelContent={'Username'}
          type={'text'}
          placeholder={'Enter your username...'}
          defaultValue={profileData.userName}
        />

        <InputField
          id={'email'}
          labelContent={'Email'}
          type={'email'}
          placeholder={'Enter your email address...'}
          defaultValue={profileData.email}
        />

        <PasswordInputField
          id={'password'}
          labelContent={'Password'}
          placeholder={'Enter your password...'}
        />

        {!loading ?
          <article className={'button-component-container'}>
            {errorMessage && <FormError errorMessage={errorMessage} />}
            <SubmitButton />
            <button onClick={editHandler}>Back</button>
          </article> :
          <article className={'button-component-container'}>
            <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
          </article>
        }
      </form>
    </div>
  </>;
};

export default ProfilePageEdit;
