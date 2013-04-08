Tic-Tac-Toe
===========
author: Jenny Zhen; jenz.rit@gmail.com  
date: April 8, 2013  
language: Java  

Table of Contents
=================

0. Overview
1. Instructions
1.1 Dependencies
2. Board & TicTacToe
3. Player & Player types
4. Problems
5. Metrics
6. Source Control
7. Research
8. Conclusion

0. Overview
===============

For this research project, a limited amount of time was given to conduct
research on basic Tic-Tac-Toe strategies and AI/algorithms for non-human
players. As a result, this project may not be complete due to time constraints,
with regards to the designing, testing, and documenting of said project.

The project itself has the following completed:
* The abstract Player class.
* The HumanPlayer that extends Player.
* The RandomAIPlayer that extends Player.
* The OkayAIPlayer that extends Player.
* The Board class that mimics an actual board with fixed, given dimensions.
* The Tic-Tac-Toe class that runs the game using the Board and Player(s).
* The RandomAIPlayerTest and OkayAIPlayerTest that tests chooseMove() from
  their respective Player class.

The project itself has the following in progress:
* The GoodAIPlayer that extends Player.
* The GoodAIPlayerTest that tests chooseMove() from GoodAIPlayer.

1. Instructions
===============

Options to run the game:
1. From the project structure, run using Tic-Tac-Toe as main().
2. Run the game using the given .jar file.  
   If using the console, run the .jar file using the following command:  
        java -jar TicTacToe.jar
3. Build the .jar using Maven.  
        mvn package    

Note: All numbering in the game starts at zero. 
Note: A valid move is defined as an empty tile on the board.
Note: For testing purposes, all random number generators created using 
Java's Random are given a seed to ensure repeatable tests.

1.1 Dependencies
----------------
* Java 1.6
* Maven 3.0

2. Board & Tic-Tac-Toe
======================

The board is implemented as a Character[][] with a default length and width 
of 3 by 3, respectively. The tiles start off populated with underscores to 
represent empty tiles. Those tiles are filled using the respective Player's 
when the Player makes a valid move on that tile.

The Tic-Tac-Toe game is implemented as a single controller that runs the 
game, requests moves from Player, and sends those moves to the Board. 
Additionally, the alternate player type and difficulty is determined by 
the user's input, but created and managed by Tic-Tac-Toe. The status of 
the game (win, lose, tie, nothing) is determined by Tic-Tac-Toe 
because only Tic-Tac-Toe has to know the rules of the game, with the 
exception of GoodAIPlayer. Tic-Tac-Toe will determine the game over and 
win messages.

3. Players & Player types
=========================

HumanPlayer
-----------
The HumanPlayer takes in user input for moves. Basic input validation is 
used to determine if input is of integer type, if input is within the 
boundaries of the board, and if the input is for a valid move.

Note: By default, the first player is always a human player. This can be 
easily modified to utilize two AI players to play the game in the 
constructor of Tic-Tac-Toe.

RandomAIPlayer
--------------
The RandomAIPlayer takes in a list of valid moves, and uses Java's Random 
to create a random number generator that selects an index for an element 
in the list.

OkayAIPlayer
------------
The OkayAIPlayer chooses a random first move using Java's Random on the 
list of valid moves. For the second and latter moves, OkayAIPlayer chooses 
moves adjacent to its own previous moves. Those previous moves are managed 
within the class as a list.

GoodAIPlayer
------------
The GoodAIPlayer always chooses the center of the board as a first move if 
it is a valid move, as a heuristic, because choosing the center should be 
the best move. Afterwards, it determines moves based on a Minimax 
algorithm.

Minimax theorem works by assigning a score to each action. To
evaluate the worth of an action, the game is simulated until a leaf node
is reached, which is then given a score of 1 if the AI won, 0 if the game
ended in a tie, and -1 if the AI lost. When the AI is evaluating the worth
of a move, it considers what the opponent would do and tries to maximize 
the score. To simulate what an opponent would do, the AI assumes the 
opponent will play perfectly, and so has the simulated opponent minimize 
the score (attempt to win). This means that the AI will always pick moves 
where it can win, if possible, or in the worst case tie.
