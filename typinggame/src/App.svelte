<script>
    import GamePage from "./GamePage.svelte";
    import LoginPage from "./loginPage.svelte";
    import ModeSelector from "./mode-selector.svelte";
import WelcomeBox from "./welcome-box.svelte";

let activeView = 'home'; //This is the default view

/** @param {String} view */
function setActiveView(view){
  activeView = view;
}

</script>

{#if activeView == 'home'}
<WelcomeBox {activeView} {setActiveView}/>
{:else if activeView == 'login'}
<LoginPage {activeView} {setActiveView}/>
{:else if activeView == 'selector'}
<ModeSelector {activeView} {setActiveView} />
{:else if activeView == 'SPGame'}
<GamePage {activeView} {setActiveView} />
{/if}
