const TrackComponentSelector = ({ buttonLabel, clickHandler }) => {
  return (
    <button
      onClick={() => clickHandler(buttonLabel)}
      type='button'
    >{buttonLabel}</button>
  );
};

export default TrackComponentSelector;
