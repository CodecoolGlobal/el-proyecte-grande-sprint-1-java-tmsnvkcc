import './TrackNavigation.styles.css';
import {TrackComponentSelector, TrackDateSelector} from "../index.js";

const TrackNavigation = ({ selectedYear, selectedMonth, setSelectedYear, setSelectedMonth, handleClick }) => {

  return (
    <div className={'track-navigation'}>
      <TrackDateSelector
        selectedYear={selectedYear}
        selectedMonth={selectedMonth}
        setSelectedYear={setSelectedYear}
        setSelectedMonth={setSelectedMonth} />
      <div id='track-tile-selector'>
        <TrackComponentSelector buttonLabel='Spendings' clickHandler={handleClick} />
        <TrackComponentSelector buttonLabel='Income' clickHandler={handleClick} />
        <TrackComponentSelector buttonLabel='Savings' clickHandler={handleClick} />
        <TrackComponentSelector buttonLabel='Overview' clickHandler={handleClick} />
      </div>
    </div>
  );
};

export default TrackNavigation;
