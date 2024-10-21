<script>
    import { onMount } from "svelte";
    import { getWords } from "./words_client";

    let dbWords = [];
    let i = 0;
    let wordCount = 0;
    let containerDiv;
    let words = "";
    

    async function fetchWords(){
        let retrieved = await getWords();
        dbWords = retrieved;
        words = readyWords(dbWords);
        spanerizeString(containerDiv, words);
      }

    /** @param{Array} dbWords 
    Takes an array of words, returns one long string 
    that is a combination of many words and spaces!
    */

    function readyWords(dbWords){
        var wordsStr = "";
        for (let i = 0; i<dbWords.length; i++){
            wordsStr += dbWords[i] + " ";
          }
          return wordsStr.trimEnd();
      }


    /**
    * @param {HTMLElement} WordContainer
    * @param {String} wordsStr
    * takes the wordsStr makes every char + ' ' into
    * a span element and appends it as a child to the 
    * given div
    */
    function spanerizeString(WordContainer, wordsStr){
      containerDiv.innerHtml = "";
      for (let i = 0; i<wordsStr.length; i++){
          const charSpan = document.createElement('span');
          charSpan.classList.add('char');
          charSpan.innerHTML = wordsStr[i];
          WordContainer.appendChild(charSpan);
        }
        containerDiv = WordContainer;
        WordContainer.children[0].classList.add("white");
      }

      function endGame(){
          const div = document.getElementById("WordContainer");
          div.innerHTML = "Test";
        }

    /**
    * @param {KeyboardEvent} event
    */
    function handleKeyEvent(event){
      if (i >= words.length){
          console.log("Done");
          endGame();
        } else {
        let currentChar = containerDiv.children[i];
        if (event.key === currentChar.innerHTML){
         currentChar.classList.remove("space");
         currentChar.classList.remove("red");
         currentChar.classList.remove("white");
         currentChar.classList.add("green");
         console.log("Korrekt");
          if (event.key === ' '){
            wordCount++;
            }
            i++;
            if (i < words.length && containerDiv.children[i].innerHTML === ' '){
              containerDiv.children[i].classList.add("space");
            } else if (i < words.length){
              containerDiv.children[i].classList.add("white");
              }
          } else {
           currentChar.classList.remove("red");
           currentChar.classList.add("red");
           currentChar.classList.remove("white");
          }

          }
      }


    // You want to set the html of the div to be 
    // fetchWords -> array of words
    // a bunch of chars and spaces that is in a span 

  onMount(() => {
      document.addEventListener('keydown', handleKeyEvent);
      fetchWords();
    });
</script>

<style>
 #WordContainer{
    font-size: 20px;
    background-color: black;
    color: grey;
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;
    padding-top: 20px;
    padding-bottom: 40px;
   }

  :global(.char) {
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
      width:  0.51em; /* Adjust the width as needed */
      height: 0.9em; /* Set height to match the text height */
      border: 1px solid white; /* White outline */
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

