# ProjectTetris
Tetris Project of WSS CT12 2019


## Goal of this project

We planned to implement a lot of features into our game. Here are the ones we worked out:
* visuals
*	To make the game more visually pleasing, backgrounds colored bricks were considered.
* Highscores
*	Highscores would be saved by the game and shown to the current player. New highscores would wear an editable signature from the player that beat the older highscores
* Bricks
*	The bricks would be randomly selected from a predetermined couple of shapes and colors. The player controls the bricks by rotating and moving them to progress in the level. To prematurely place a block, a faster fall speed would be present by activating it with a button.
* Progress in-game
*	To keep the player invested in the game, increasing difficulty in form of faster falling blocks would be present.
* Credits
	The names of everyone involved in the creation of the game would be shown when a button labeled "credits" would be selected

## Management

* ElyshaPhoenix – Responsible for organisation within the team and for backend
* programminghoch10  - Responsible for technic matters, coordination within the team and for backend
* jjw2202 –Responsible for communication between backend and frontend and for creating and designing of game screen; member of UI team
* Hartu666 –Responsible for creating of menu and designing game over cover in the game; member of UI team
* Juka27 –Responsible for creating the icon of the game and redesing of menu in the game; member of UI team
* Gr0g98 – Responsible for use-case diagrams and dokumentation of project 
* Ivan42069 – Responsible for class diagram and dokumentation of project


## Development
-Code
-Functions

## Encountered problems
we encountered a few problems on our path to completeing our game. This is how we dealt with them:
1. To coordinate every move of a stone, every individual case for every individual stone would have to be coded. This proves to be way too much.
* **solution:** working object-oriented every stone position can be calculated. We only need to worry about further movements.
2. both pause menu and game over screen should include a grayscale when active. The grayscale however didnt apply.
* **solution:** write a new if-query that checks if pause or gameover = true
## Conclusion
