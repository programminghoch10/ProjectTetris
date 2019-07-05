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


## Development progress

* keeping a code polished is very important. It helps making it clear for every party involved. Because of that reason the code has been cleaned, restructured as well as getting updates commented every few steps of development.
* added temporary game matrix
* The first added classes were: "stone" "stone constructor" and "array stone type"
* Generate an empty frame
* Added relative coordinates for stones and set them up
* Added static matrix and rotation functionality
* Implemented a random initial rotation at the starting position when a new block is generated
* Added horizontal movement and the rate at which blocks drop down
* Added randomized background colors
* Added paralled processing
* Added a gameplay test area in the console
* To detect when blocks hit the last line, a last line detector was implemented
* Added the feature to fastdrop blocks
* Added randomized block colors
* Made the jpanelarray static and 2 dimensional to fit the gamematrix
* Increased the game refreshrate for a smoother experience
* Every full line of blocks nowgets removed
* Added controls to move blocks and pause the game
* The functionality of the pause menu is expanded
* Added borders to the game window
* Created a logo for the game
* Main menu is now put together
* Fixing up window and border size of the game
* Reduced flickering
* Rework of the pause menu
* Added a game over screen
* Added a stone placement prediction at the bottom of the window (ghost)
* Main menu gets redesigned
* Game over screen gets redesigned
* Added credits
* Added the feature of greyscaling the screen when paused
* The look of the user interface gets added


## Encountered problems

we encountered a few problems on our path to completeing our game. This is how we dealt with them:
* To coordinate every move of a stone, every individual case for every individual stone would have to be coded. This proves to be way too much.
  * **solution:** working object-oriented every stone position can be calculated. We only need to worry about further movements.
* While trying to add a predetermined list of blocks to drop, the game over screen is bypassed.
  * **solution:** instead of saving the next block to generate, save the type of the next block.
* both pause menu and game over screen should include a grayscale when active. The grayscale however didnt apply.
  * **solution:** write a new if-query that checks if pause or gameover = true
  
## Conclusion
