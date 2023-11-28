import './Window.styles.css';

const Window = ({ title, text, transactionButton }) => {
  return (
    <div className={'window'}>
      <h2>{title}</h2>
      <hr />
      <p>{text}</p>
      {transactionButton}
    </div>
  );
};

export default Window;
