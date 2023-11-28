import './Window.styles.css';

const Window = ({ title, text, button }) => {
  return (
    <div className={'window'}>
      <h2>{title}</h2>
      <hr />
      <p>{text}</p>
      {button}
    </div>
  );
};

export default Window;
