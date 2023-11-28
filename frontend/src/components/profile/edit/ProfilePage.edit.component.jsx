import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import {
  FormError,
  InputField,
  PasswordInputField,
  SubmitButton,
} from 'components/form-related';

import { useHandleFormOnSubmit } from './ProfilePage.hooks';
import { iconLibraryConfig } from 'config';

const ProfilePageEdit = ({ profileData }) => {
  const { loading, errorMessage, onSubmit } = useHandleFormOnSubmit();

  return <>
    <form id='profileEdit' onSubmit={(event) => onSubmit(event)}>
      <InputField
        id={'userName'}
        labelContent={'Username'}
        type={'text'}
        placeholder={'Enter your username...'}
        defaultValue={profileData.userName}
      />

      <InputField
        id={'userEmail'}
        labelContent={'Email'}
        type={'email'}
        placeholder={'Enter your email address...'}
        defaultValue={profileData.email}
      />

      <PasswordInputField
        id={"password"}
        labelContent={'Password'}
        placeholder={'Enter your password...'}
      />

      {!loading ?
        <article>
          <SubmitButton />
          {errorMessage && <FormError errorMessage={errorMessage} />}
        </article> :
        <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
      }
    </form>
  </>;
};

export default ProfilePageEdit;
