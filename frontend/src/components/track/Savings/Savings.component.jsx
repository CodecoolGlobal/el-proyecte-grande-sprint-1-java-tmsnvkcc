import React, {useEffect, useState} from 'react';
import './Savings.styles.css';

const SavingsComponent = ({ transactions, isLoading }) => {
    const [actualBalance, setActualBalance] = useState(0);
    const [savingsBalance, setSavingsBalance] = useState(0);
    const getAccountBalance = () => {
        const storedData = JSON.parse(localStorage.getItem("userData"));
        return storedData.account;
        //TODO Replace temporary implementation of account retrieval
    }

    useEffect(() => {
        const retrievedBalances = getAccountBalance();

        setActualBalance(retrievedBalances.actualBalance);
        setSavingsBalance(retrievedBalances.savingsBalance)
    }, []);

    return (
        <div className={"savings-page-overview"}>
            <div className={"savings-balance-container"}>
                <h2>ACTUAL BALANCE</h2>
                {actualBalance}
            </div>
            <div className={"savings-arrow-button-container"}>ARROWS</div>
            <div className={"savings-balance-container"}>
                <h2>SAVINGS BALANCE</h2>
                {savingsBalance}
            </div>
        </div>
    );
};

export default SavingsComponent;