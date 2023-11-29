import './SelectField.styles.css';

const SelectField = ({ id, options, labelContent }) => {
  return (
    <article className={'select-component-container'}>
      <label htmlFor={id}>{labelContent}</label>
      <select
        className={'default-select-field-style'}
        id={id}
        name={id}
      >
        <option disabled selected value>select a category</option>
        {options.map((element) => <option key={element.id} value={element.name}>{element.name}</option>)}
      </select>
    </article>
  );
};

export default SelectField;
