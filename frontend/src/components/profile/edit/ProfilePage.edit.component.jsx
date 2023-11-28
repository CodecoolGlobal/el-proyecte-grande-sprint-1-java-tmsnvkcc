import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import {
  FormError,
  InputField,
  PasswordInputField,
  SubmitButton,
} from 'components/form-related';

import { useHandleFormOnSubmit } from './ProfilePage.hooks';
import { iconLibraryConfig } from 'config';

const ProfilePageEdit = ({ profileData, editHandler }) => {
  const { loading, errorMessage, onSubmit } = useHandleFormOnSubmit(editHandler);

  return <>
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
          <SubmitButton />
          <button onClick={editHandler}>Back</button>
          {errorMessage && <FormError errorMessage={errorMessage} />}
        </article> :
        <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
      }
    </form>
  </>;
};

export default ProfilePageEdit;
