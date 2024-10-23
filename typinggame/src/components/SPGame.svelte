<script>
import { onMount } from "svelte";
import { getWords } from "./words_client";

let dbWords = [];
let i = 0;
let wordCount = 0;
let containerDiv;
let words = "";
let startTime;
let gameFinished = false;
let endMessage = "";
let gameId; // Store the game ID

async function fetchWords() {
    let retrieved = await getWords();
    dbWords = retrieved;
    words = readyWords(dbWords);
    spanerizeString(containerDiv, words);
    startTime = Date.now(); // Record the start time

    // Send a request to start a new game on the backend
    const response = await fetch("http://localhost:8080/api/game/start", {
method: "POST",
headers: {
"Content-Type": "application/json",
},
});

if (response.ok) {
    const gameData = await response.json();
    gameId = gameData.id; // Store the game ID
} else {
    console.error("Failed to start a new game.");
}
}

function readyWords(dbWords) {
    let wordsStr = "";
    for (let i = 0; i < dbWords.length; i++) {
        wordsStr += dbWords[i] + " "; 
    }
    return wordsStr.trimEnd(); 
}

function spanerizeString(WordContainer, wordsStr) {
    containerDiv.innerHTML = ""; // Clear the container
    for (let i = 0; i < wordsStr.length; i++) {
        const charSpan = document.createElement('span');
        charSpan.classList.add('char');
        charSpan.innerHTML = wordsStr[i];

        // If the character is a space, add a class for styling (optional)
        if (wordsStr[i] === ' ') {
            charSpan.innerHTML = '&nbsp;';
        }

        WordContainer.appendChild(charSpan);
    }
    containerDiv = WordContainer;
    WordContainer.children[0].classList.add("white");
}

function endGame() {
    const endTime = Date.now(); // Get the end time
    const timeTaken = (endTime - startTime) / 1000; // Calculate time taken in seconds
    const minutesTaken = timeTaken / 60;
    const wpm = (wordCount / minutesTaken).toFixed(2); // Calculate WPM

    // Create the game object to send to the backend
    const gameData = {
        endTime: new Date(endTime), // Convert end time to a proper date
         wpm: parseInt(wpm), // WPM should be an integer
         voteOptions: [
            
         ]
    };

    // Send the gameData to the backend
    fetch(`http://localhost:8080/api/game/end/${gameId}`, {
method: 'POST',
headers: {
'Content-Type': 'application/json',
},
body: JSON.stringify(gameData), // Convert the game object to JSON
})
.then(response => {
        if (!response.ok) {
        throw new Error('Network response was not ok');
        }
        return response.json();
        })
.then(data => {
        console.log('Game ended successfully:', data);
        // Handle success, like showing a message to the user
        endMessage = `Congrats! You finished in ${timeTaken.toFixed(2)} seconds and wrote ${wpm} WPM.`;
        gameFinished = true; // Set the game as finished
        })
.catch(error => {
        console.error('There was a problem with the fetch operation:', error);
        });
}



function handleKeyEvent(event) {
    if (gameFinished) return; // Ignore events if the game is finished

    let currentChar = containerDiv.children[i];

    // Handle space input separately
    if (event.key === ' ' && currentChar.innerHTML === '&nbsp;') {
        // Correctly typed a space
        currentChar.classList.remove("red", "white", "space");
        currentChar.classList.add("green");
        wordCount++; // Increment word count
        i++; // Move to the next character
    } else if (event.key === currentChar.innerHTML) {
        // Handle correct character input
        currentChar.classList.remove("red", "white");
        currentChar.classList.add("green");

        i++; // Move to the next character

        // Check if we've reached the end
        if (i >= words.length) {
            endGame(); // End the game if all characters have been typed
            return; // Exit to prevent further processing
        }
    } else {
        // Handle incorrect input
        currentChar.classList.add("red");
        currentChar.classList.remove("white");
    }

    // Highlight the next character if it exists
    if (i < words.length) {
        if (containerDiv.children[i].innerHTML === '&nbsp;'){
            containerDiv.children[i].classList.add("space");
        }else {
            containerDiv.children[i].classList.add("white");
        }
    }
}



function playAgain() {
    // Reset all the necessary variables to start a new game
    i = 0;
    wordCount = 0;
    gameFinished = false;
    endMessage = "";
    fetchWords(); // Fetch new words and start a new game
}

onMount(() => {
        document.addEventListener('keydown', handleKeyEvent);
        fetchWords(); // Fetch words when the component mounts
        });
</script>

<style>
#WordContainer {
    font-size: 20px;
    background-color: black;
color: grey;
       border-radius: 10px;
       padding-top: 20px;
       padding-bottom: 40px;
}

:global(.char) {
display: inline-block;
transition: color 0.2s ease;
color: grey; 
}

:global(.char.green) {
color: green;
}

:global(.char.white) {
color: white;
}

:global(.char.space) {
display: inline-block;
width: 0.51em; /* Adjust the width as needed */
height: 0.9em; /* Set height to match the text height */
border: 1px solid white; /* Make the space visible */
        background-color: transparent; /* Transparent background */
}

:global(.char.red) {
color: red;
animation: -global-shake 1s; /* Add the shake animation */
}

@keyframes -global-shake {
    0% { transform: translateX(0); }
    25% { transform: translateX(-2px); }
    50% { transform: translateX(2px); }
    75% { transform: translateX(-2px); }
    100% { transform: translateX(0); }
}
</style>

<div id="WordContainer" bind:this={containerDiv}>
{#if words.length > 0}
{:else}
Loading Words .....
{/if} 
</div>

{#if gameFinished}
<div>
<h2>{endMessage}</h2> <!-- Display the end message -->
<button on:click={playAgain}>Play Again</button> <!-- Button to play again -->
</div>
{/if}

