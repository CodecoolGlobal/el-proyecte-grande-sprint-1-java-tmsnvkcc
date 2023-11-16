import './Window.styles.css';

const Window = ({ title, text }) => {
    return (
        <div className={'window'}>
            <h2>{title}</h2>
            <hr/>
            <p>{text}</p>
        </div>
    );
};

export default Window;
