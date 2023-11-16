import {
    PageTitle,
} from 'components/form-related';
import {
    Window,
} from 'components/dashboard';
import './Dashboard.styles.css';

const Dashboard = () => {

  return (
    <div className={'dashboard-page'}>
      <PageTitle title={"Dashboard"} />
      <div className={'dashboard-window-container'}>
          <Window title={'Aktuális hónap'} text={'Kiadás az előző hónaphoz képest'}/>
          <Window title={'12 Havi visszatekintés'} text={'Kiadások'} />
          <Window title={'Test title'} text={'Random test text dev'} />
      </div>
    </div>
  );
};

export default Dashboard;
