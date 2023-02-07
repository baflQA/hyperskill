const input = require('sync-input');
const wordsList = ["python", "java", "swift", "javascript"];
const singleLowerCaseLetterRegex = /^[a-z]$/g;
let attempts;
let wonGamesCount = 0;
let lostGamesCount = 0;


console.log("H A N G M A N\n");

while (true) {
    let menuOption = input(`Type "play" to play the game, "results" to show the scoreboard, and "exit" to quit:`);
    if (menuOption === 'exit') {
        break;
    }
    if (menuOption === 'results') {
        console.log(`You won: ${wonGamesCount} times.`);
        console.log(`You lost: ${lostGamesCount} times.`);
    } else if (menuOption === 'play'){
        playTheGame();
    }
}

function playTheGame() {
    attempts = 8;
    const wordToGuess = String(wordsList[Math.round((wordsList.length - 1) * Math.random())]);
    let initialHint = '-'.repeat(wordToGuess.length);
    let userInputs = new Set();
    console.log(initialHint);
    let resultArray = [...initialHint];
    do {
        let userInput = input(`Input a letter:`);
        if (validateLetterAppears(userInput, wordToGuess, resultArray, userInputs)) {
            for (let i = 0; i < initialHint.length; i++) {
                if (wordToGuess[i] === userInput) {
                    resultArray[i] = userInput;
                }
            }
        }
        let result = resultArray.join('');
        if (result === wordToGuess) {
            console.log(`You guessed the word ${wordToGuess}!`);
            break;
        }
        console.log(result);
    } while (attempts > 0)
    printFinalResult();

    function printFinalResult() {
        if (attempts > 0) {
            console.log("You survived!");
            wonGamesCount++;
        } else {
            console.log("You lost!")
            lostGamesCount++;
        }
    }
}

function validateLetterAppears(userInput, wordToGuess, uncoveredLetters, userInputs) {
    if (userInput.length !== 1) {
        console.log("Please, input a single letter");
        return false;
    }
    if (userInput.match(singleLowerCaseLetterRegex) === null) {
        console.log("Please, enter a lowercase letter from the English alphabet")
        return false;
    }
    if (userInputs.has(userInput)) {
        console.log("You've already guessed this letter.");
        return false;
    }
    userInputs.add(userInput);
    if (uncoveredLetters.includes(userInput)) {
        console.log("No improvements.")
        attempts--
        return false;
    } else if (wordToGuess.includes(userInput)) {
        return true;
    } else {
        console.log("That letter doesn't appear in the word.");
        attempts--
        return false;
    }
}
