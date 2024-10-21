<script>
    import Menu from "./Menu.svelte";
    import SpGame from "./SPGame.svelte";
    export let activeView;
    export let setActiveView;


    
</script>

<style>
</style>

<Menu {activeView} {setActiveView} />

<SpGame />
