# TicTacToe

## Modes of Game 

1. **LOCAL**
    * Both players have to play on the same machine on the same console.

2. **ARENA**
    * Server is started on a machine.
    * As soon as 2 clients are connected to the server, the server spawns a new thread and the game initiates in that thread and the server starts listening for other connections.
    
    
## Steps to start the game

* Run the AppManager.
    * if **LOCAL**
        * both players should continue to play on the same console
    * if **ARENA**
        * Run 2 instances of Player which will connect to the Arena Server and then the players continue to play on the respective console of the respective machine.
        
## Addons

* If the game is running in **ARENA** mode then a separate GameStats thread listens for connections which outputs the List of following
    * Players name
    * Current state of the Game
    * Winner of the Game
    ```javascript
    [
      {
        "gameState": "RUNNING",
        "player1": "Sumeet",
        "player2": "Firday",
        "winner": "Sumeet"
      },
      {
        "gameState": "RUNNING",
        "player1": "Player_1",
        "player2": "Player_2",
        "winner": null
      }
    ]
```
