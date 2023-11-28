import React from 'react';
import './Savings.styles.css';

const SavingsComponent = () => {
    return (
        <div className={"savings-page-overview"}>
            <div className={"savings-actual-balance"}>Actual</div>
            <div className={"savings-arrow-button-container"}>ARROWS</div>
            <div className={"savings-saved-balance"}>Saved</div>
        </div>
    );
};

export default SavingsComponent;