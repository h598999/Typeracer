<script>
    import Menu from "./Menu.svelte";
    export let activeView;
    export let setActiveView;

</script>

<style>
 #Input{
     justify-content: center;
     background-color: #e6dbda;
     color: black;
     border-top-left-radius: 10px;
     border-bottom-left-radius: 10px;
     border-top-right-radius: 10px;
     border-bottom-right-radius: 10px;
     padding-bottom: 100px;
   }
  input{
      border-color: darkgoldenrod;
      background-color: #e6dbda;
      color: black;
      width: 300px;
      height: 40;
      padding: 10px;
      border-top-left-radius: 10px;
      border-bottom-left-radius: 10px;
      border-top-right-radius: 10px;
      border-bottom-right-radius: 10px;
    }

  button{
      background-color: darkgoldenrod;
      margin-top: 10px;
    }

  button:hover{
      background-color: grey;
      margin-top: 10px;
    }

</style>

<Menu {activeView} {setActiveView}/>

<div id="Input">
  <h3>Login: </h3>
   Brukernavn: <br>
   <input type="text" placeholder="Username:"> 
   <br>
   Passord:    <br>
   <input type="text" placeholder="Password:"> 
   <br>
   <button>Log in</button>
   <br>
   <button> Create user </button>
</div>
