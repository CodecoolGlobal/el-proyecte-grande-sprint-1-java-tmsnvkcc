import './ProfileAccount.display.styles.css';

const ProfileAccountDisplay = ({ account }) => {
  console.log(account)

  return (<>
    <div className={'profile-page-overview'}>
      <div className={'overview-content'}>
        <h2>Account name</h2>
        {account.name}
      </div>

      <div className={'overview-content'}>
        <h2>Description</h2>
        {account.description}
      </div>

      <div className={'line'}></div>

      <div className={'overview-content'}>
        <h2>Actual balance</h2>
        {account.actualBalance /* // TODO: change hard coded currency */} HUF
      </div>

      <div className={'overview-content'}>
        <h2>Savings balance</h2>
        {account.savingsBalance /* // TODO: change hard coded currency */} HUF
      </div>
    </div>
  </>)
};

export default ProfileAccountDisplay;
