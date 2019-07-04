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
* added temporary game matrix
* start with adding class "stone "stone constructor" "array stone type"
* generate empty frame
* add relative coordinates for stones and set them up
* added static matrix and rotate functionality
* random initial rotation (starting pos.)
* added horizontal movement and drop function
* added random background and block colors
* added paralled processing
* added gameplay test area (console)
* ------------comments              <---- important
* last line detector
* added fastdrop
* random colors
* 2 dimensional
* regulating game refreshrate (increase)
* pause function
* borders
* ----------------code clean       <---- important
* logo
* menu
* ---------------structure progam   <--- important
* fix window and border size
* reduce flickering
* rework pause menu
* add game over
* add stone projection at the bottom (ghost)
* redesign menu
* redesign game over
* add credits
* add greyscale when paused
* design and design user interface


## Encountered problems
we encountered a few problems on our path to completeing our game. This is how we dealt with them:
* To coordinate every move of a stone, every individual case for every individual stone would have to be coded. This proves to be way too much.
  * **solution:** working object-oriented every stone position can be calculated. We only need to worry about further movements.
* While trying to add a predetermined list of blocks to drop, the game over screen is bypassed.
  * **solution:** instead of saving the next block to generate, save the type of the next block.
* both pause menu and game over screen should include a grayscale when active. The grayscale however didnt apply.
  * **solution:** write a new if-query that checks if pause or gameover = true
## Conclusion
