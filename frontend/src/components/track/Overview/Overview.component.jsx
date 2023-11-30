import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
    useEffect,
    useState
} from "react";
import { iconLibraryConfig } from "config";
import './Overview.styles.css';

const Overview = ({ transactions, isLoading }) => {

    const [accountDetails, setAccountDetails] = useState("");
    const [spending, setSpending] = useState(null);
    const [income, setIncome] = useState(null);
    const [plannedSpending, setPlannedSpending] = useState(null);
    const [plannedIncome, setPlannedIncome] = useState(null);
    const [currency, setCurrency] = useState("HUF");

    const getAmountSumOf = (list) => {
        let sum = 0;
        for (let i = 0; i < list.length; i++) {
            sum += list[i].amount;
        }
        return sum;
    }

    const calculateSpending = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount < 0));
    };

    const calculateIncome = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount > 0));
    };

    const calculatePlannedSpending = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount < 0 && tr.isPlanned));
    };

    const calculatePlannedIncome = (exTransactionList) => {
        return getAmountSumOf(exTransactionList.filter((tr) => tr.amount > 0 && tr.isPlanned));
    };
    //TODO Implement custom hook for helper functions

    useEffect(() => {
        console.log(isLoading);
        if(!isLoading){
          const data = transactions.externalTransactionDTOS;

          setSpending(calculateSpending(data));
          setIncome(calculateIncome(data));
          setPlannedSpending(calculatePlannedSpending(data));
          setPlannedIncome(calculatePlannedIncome(data));

          const userData = JSON.parse(localStorage.getItem("userData"));
          setAccountDetails(userData.account);
        }
    }, [transactions, isLoading]);

    return (
        <div className={"track-page-overview"}>

            { isLoading &&
            <div className={'track-page-overview-loading'}>
                <FontAwesomeIcon icon={iconLibraryConfig.faCircleNotch} spin className={'loading-icon'} />
            </div> }

            <div className={"overview-left"}>
                {/*<div className={'left-title'}>
                    <h1>Monthly balance</h1>
                    <hr />
                </div>*/}
                <div className={'left-content'}>
                    <div className={'information'}>
                        <h2>Total amount of spending this month</h2>
                        <h3 className={'spending-color'}>{spending} {currency}</h3>
                    </div>

                    <div className={'information'}>
                        <h2>Planned spending</h2>
                        <h3 className={'spending-color'}>{plannedSpending} {currency}</h3>
                    </div>

                    <div className={'information'}>
                        <h2>Total amount of income this month</h2>
                        <h3 className={'income-color'}>{income} {currency}</h3>
                    </div>

                    <div className={'information'}>
                        <h2>Planned income</h2>
                        <h3 className={'income-color'}>{plannedIncome} {currency}</h3>
                    </div>
                </div>
            </div>
            <div className={'overview-split'}>
                <hr className={'hr-stand'}/>
            </div>
            <div className={"overview-right"}>
                {/*<div className={'right-title'}>
                    <h1>Total</h1>
                    <hr />
                </div>*/}
                <div className={'right-content'}>
                    <div className={'information'}>
                        <h2>Actual Balance</h2>
                        <h3>{accountDetails.actualBalance} {currency}</h3>
                    </div>
                    <div className={'information'}>
                        <h2>Savings Balance</h2>
                        <h3>{accountDetails.savingsBalance} {currency}</h3>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Overview;
