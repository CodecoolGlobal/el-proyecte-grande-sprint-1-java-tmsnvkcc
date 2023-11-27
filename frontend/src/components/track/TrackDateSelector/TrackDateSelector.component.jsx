import "./TrackDateSelector.styles.css";

const TrackDateSelector = ({ year, everyMonth, months, setSelectedYear, setSelectedMonth }) => {

    const parseAndSetMonth = (e) => {
        setSelectedMonth(parseInt(e.target.value));
    };

    return (
        <div className={"track-date-selector"}>
            <input type="number"
                   defaultValue={year}
                   onChange={(e) => setSelectedYear(e.target.value)}
                   min={new Date().getFullYear() - 100} // basic limit for now
                   max={new Date().getFullYear() + 100} // basic limit for now
            />

            <select defaultValue={months} onChange={(e) => parseAndSetMonth(e)}>
                {everyMonth.map((month, i) => {
                    return <option value={i}>{month}</option>
                })}
            </select>
        </div>
    );
};

export default TrackDateSelector;
