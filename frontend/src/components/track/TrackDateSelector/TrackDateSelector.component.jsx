import "./TrackDateSelector.styles.css";

const TrackDateSelector = ({ year, everyMonth, months, handleYear, handleMonth }) => {

    const parseMonth = (e) => {
        handleMonth(parseInt(e.target.value));
    };

    return (
        <div className={"track-date-selector"}>
            <input type="number"
                   defaultValue={year}
                   onChange={(e) => handleYear(e.target.value)}
                   min={new Date().getFullYear() - 100}
                   max={new Date().getFullYear() + 100}
            />

            <select defaultValue={months} onChange={(e) => parseMonth(e)}>
                {everyMonth.map((month, i) => {
                    return <option value={i}>{month}</option>
                })}
            </select>
        </div>
    );
};

export default TrackDateSelector;
