import './SelectField.styles.css';

const SelectField = ({ defaultValue, id, options, labelContent }) => {
  return (
    <article className={'select-component-container'}>
      <label htmlFor={id}>{labelContent}</label>
      <select
        id={id}
        name={id}
        defaultValue={defaultValue}>
        {options.map((element) => <option key={element} value={element}>{element}</option>)}
      </select>
    </article>
  );
};

export default SelectField;
