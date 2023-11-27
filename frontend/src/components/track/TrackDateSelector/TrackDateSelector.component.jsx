import {useState} from "react";

const TrackDateSelector = ({ year, everyMonth, months, handleYear, handleMonth }) => {

    const parseMonth = (e) => {
        handleMonth(parseInt(e.target.value));
    };

    return (
        <div>
            <input type="number"
                   defaultValue={year}
                   onChange={(e) => handleYear(e.target.value)} />

            <select defaultValue={months} onChange={(e) => parseMonth(e)}>
                {everyMonth.map((month, i) => {
                    return <option value={i}>{month}</option>
                })}
            </select>
        </div>
    );
};

export default TrackDateSelector;
