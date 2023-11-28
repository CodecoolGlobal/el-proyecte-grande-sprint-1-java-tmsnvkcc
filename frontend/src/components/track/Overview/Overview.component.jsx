import './Overview.styles.css';
import {useEffect, useState} from "react";

const Overview = () => {
    const [accountDetails, setAccountDetails] = useState("");

    const [spending, setSpending] = useState(null);
    const [income, setIncome] = useState(null);
    const [plannedSpending, setPlannedSpending] = useState(null);
    const [plannedIncome, setPlannedIncome] = useState(null);

    const getAmountSumOf = (list) => {
        let sum = 0;
        for (let i = 0; i < list.length; i++) {
            sum += list[i].amount;
        }
        return sum;
    }

    const calculateSpending = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount < 0));
    }

    const calculateIncome = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount > 0));
    }

    const calculatePlannedSpending = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount < 0 && tr.planned));
    }

    const calculatePlannedIncome = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount > 0 && tr.planned));
    }

    useEffect(() => {
        const data = JSON.parse(localStorage.getItem("userData"));

        setSpending(calculateSpending(data.account.externalTransactions));
        setIncome(calculateIncome(data.account.externalTransactions));
        setPlannedSpending(calculatePlannedSpending(data.account.externalTransactions));
        setPlannedIncome(calculatePlannedIncome(data.account.externalTransactions));

        setAccountDetails(data.account);
        console.log(data.account);

    }, []);

    return (
        <div>
            <h2>Overview component</h2>
            <p>Account name: <strong>{accountDetails.name}</strong></p>
            <p>Account description: <strong>{accountDetails.description}</strong></p>
            <br/>
            <p>SPENDING this month: <strong>{spending} Ft</strong></p>
            <p>INCOME this month: <strong>{income} Ft</strong></p>
            <br/>
            <p>PLANNED spending this month: <strong>{plannedSpending} Ft</strong></p>
            <p>PLANNED income this month: <strong>{plannedIncome} Ft</strong></p>
            <br/>
            <p>Actual BALANCE: <strong>{accountDetails.actualBalance}</strong></p>
            <p>Savings BALANCE: <strong>{accountDetails.savingsBalance}</strong></p>

        </div>
    );
};

export default Overview;
