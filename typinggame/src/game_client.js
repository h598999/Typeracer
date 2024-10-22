const baseUrl = 'http://localhost:8080/api/game/';

export async function StartGame(){
  const response = await fetch(baseUrl + 'start', {method: 'POST'});
  const data = await response.json();
  let gameId = data.id;

  window.location.href = `/game/${gameId}`;
}
