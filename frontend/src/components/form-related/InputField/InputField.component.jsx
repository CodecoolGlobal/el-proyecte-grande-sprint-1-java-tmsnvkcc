import './InputField.styles.css';

const InputField = ({ labelTag, labelContent, type, defaultValue, placeholder }) => {
  return (
    <article className={'input-component-container'}>
      <label htmlFor={labelTag}>{labelContent}</label>
      <input
        type={type}
        id={labelTag}
        name={labelTag}
        defaultValue={defaultValue ?? ''}
        placeholder={placeholder}
      />
    </article>
  );
};

export default InputField;
