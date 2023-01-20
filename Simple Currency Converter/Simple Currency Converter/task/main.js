const input = require('sync-input');

console.log("Welcome to Currency Converter!");

const oneUsdEqualsMessage = "1 USD equals ";
const currenciesMap = new Map([["USD", 1], ["JPY", 113.5], ["EUR", 0.89], ["RUB", 74.36], ["GBP", 0.75]]);

console.log(`${oneUsdEqualsMessage}${currenciesMap.get("USD")} USD`);
console.log(`${oneUsdEqualsMessage}${currenciesMap.get("JPY")} JPY`);
console.log(`${oneUsdEqualsMessage}${currenciesMap.get("EUR")} EUR`);
console.log(`${oneUsdEqualsMessage}${currenciesMap.get("RUB")} RUB`);
console.log(`${oneUsdEqualsMessage}${currenciesMap.get("GBP")} GBP`);

const readInput = () => Number.parseInt(input("What do you want to do?\n1-Convert currencies 2-Exit program\n"));
let selectedOption = 0;

do {
    const userInput = readInput();
    if ([1, 2].includes(userInput)) {
        selectedOption = userInput;
    } else console.log("Unknown input");
} while (selectedOption === 0);

if (selectedOption === 2) {
    console.log("Have a nice day!");
    return;
}

let currencyFrom;
let currencyTo;
let amount;
const getCurrency = (str) => {
    console.log("What do you want to convert?");
    return input(str + ": ").toUpperCase();
}

do {
    currencyFrom = getCurrency("From");
} while (isInvalidCurrency(currencyFrom))

do {
    currencyTo = getCurrency("To");
} while (isInvalidCurrency(currencyTo))

do {
    amount = Number(input("Amount:"));
} while (isInvalidAmount(amount))

console.log(`Result: ${amount} ${currencyFrom} equals ${calculateExchangeRate(currencyFrom, currencyTo, amount).toFixed(4)} ${currencyTo}`)

function calculateExchangeRate(from, to, amount) {
    const exchangeRate = getExchangeRate(from);
    const amountInUsd = amount / exchangeRate;
    return amountInUsd * getExchangeRate(to)
}

function getExchangeRate(currency) {
    switch (currency) {
        case "EUR":
            return currenciesMap.get("EUR")
        case "USD":
            return currenciesMap.get("USD")
        case "JPY":
            return currenciesMap.get("JPY")
        case "GBP":
            return currenciesMap.get("GBP")
        case "RUB":
            return currenciesMap.get("RUB")
        default:
            throw new Error("Unknown currency")
    }
}

function isInvalidCurrency(currency) {
    const isUnknownCurrency = !currenciesMap.has(currency);
    if (isUnknownCurrency) {
        console.log("Unknown currency")
    }
    return isUnknownCurrency;
}

function isInvalidAmount(amount) {
    if (!(!isNaN(amount) && typeof amount === "number")) {
        console.log("The amount has to be a number")
        return true
    }
    if (amount < 1) {
        console.log("The amount cannot be less than 1")
        return true
    }
}