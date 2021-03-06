Tic-Tac-Toe
===========
author: Jenny Zhen; jenz.rit@gmail.com  
date: April 8, 2013  
language: Java  

Table of Contents
=================

1. Overview  
2. Instructions  
2.1 Dependencies
3. Board & TicTacToe
4. Player & Player types
5. Problems
6. Metrics
7. Source Control
8. Research
9. Conclusion

1. Overview
===============

For this research project, a limited amount of time was given to conduct
research on basic Tic-Tac-Toe strategies and AI/algorithms for non-human
players. As a result, this project may not be complete due to time constraints,
with regards to the designing, testing, and documenting of said project.

The project itself has the following completed:
* A command-line interface.
* The abstract Player class.
* The HumanPlayer that extends Player.
* The RandomAIPlayer that extends Player.
* The OkayAIPlayer that extends Player.
* The Board class that mimics an actual board with fixed, given dimensions.
* The Tic-Tac-Toe class that runs the game using the Board and Player(s).
* The RandomAIPlayerTest and OkayAIPlayerTest that tests chooseMove() from
  their respective Player class.
* The GoodAIPlayer that extends Player.
* The GoodAIPlayerTest that tests chooseMove() from GoodAIPlayer.

The project itself has the following ideas for future revisions:
* BadAIPlayer that chooses the first move in a list of valid moves.
* Other AI algorithms/strategies relevant to Tic-Tac-Toe.
* Playing against two AIs.
* A GUI.

2. Instructions
===============

Options to run the game:  
* From the project structure, run using Tic-Tac-Toe as main().  
* Run the game using the given .jar file.  
    If using the console, run the .jar file using the following command:  
    
    java -jar jxz6853_tictactoe.jar  

* Build the .jar using Maven.  

    mvn package  

Note: All numbering in the game starts at zero.  
Note: A valid move is defined as an empty tile on the board.  
Note: For testing purposes, all random number generators created using 
Java's Random are given a seed to ensure repeatable tests.

2.1 Dependencies
----------------
* Java 1.6
* Maven 3.0

3. Board & Tic-Tac-Toe
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

4. Players & Player types
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

As a result, GoodAIPlayer can only win or tie. However, to make that 
possible, GoodAIPlayer has to know a lot more about the TicTacToe game, 
including the win, lose, and tie conditions. Thus, high coupling can be an 
issue with GoodAIPlayer, Board, and TicTacToe.

5. Problems
===========

1. GoodAIPlayer did not choose the optimal moves, so a more concise weight 
system had to be applied to the scoring process. If the AI would lose or 
win immediately, that move has a higher priority than a move that will 
win in two or more moves.
2. Coupling became an issue when writing the tests for OkayAIPlayer and 
GoodAIPlayer. The project was not designed to keep testing a priority. 
Rather, the functionality of the project with its AIs were the priority. 
Modifications had to be made to access modifiers for methods and members 
of classes in order to make them visible for unit tests.

6. Metrics
==========

The following is a very rough estimate of time spent.

* Design: 60 minutes
* Board & TicTacToe: 120 minutes
* Player, HumanPlayer, RandomAIPlayer, OkayAIPlayer: 200 minutes
* Tests for all Players, except GoodAIPlayer: 100 minutes
* GoodAIPlayer, GoodAIPlayerTest: 210 minutes
* All Documentation: 120 minutes
* Total: 810 minutes (13.5 hours)

The following is the source lines of code written.
* Product: 838
* Test: 168
* Documentation (ReadMe): 189
* Total: 1195

The following is the total source lines of code per hour.
* 88.52 sloc/hr

Note: A lot of the time was spent debugging GoodAIPlayer. The issue was 
simply calling the wrong function.

7. Source Control
=================

This project is managed using Git and located on Github at:  
https://github.com/jenzie/tictactoe

The commit log can also be accessed in the changelog.txt file in the 
root/target/ directory of the project.

8. Research
===========

Research was conducted to find the best moves for the first and second 
moves. Additionally, different types of AIs were researched. That led to 
the creation of the RandomAIPlayer, OkayAIPlayer, and GoodAIPlayer. A 
fifth player type (HumanPlayer included) could be implemented as a 
BadAIPlayer that chooses the first possible move. However, for the 
purposes of producing a perfect game AI, most of the time was spent on 
perfecting a GoodAIPlayer, as demonstrated in Section 5: Metrics.

Sources:  
* Tic-Tac-Toe AI  
http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
* Game Trees  
http://www.ocf.berkeley.edu/~yosenl/extras/alphabeta/alphabeta.html
* Tic-Tac-Toe Strategy  
http://ostermiller.org/tictactoeexpert.html
* Tic-Tac-Toe Strategy Guide  
http://www.chessandpoker.com/tic_tac_toe_strategy.html
* Minimax and Alpha-Beta Pruning  
http://students.cs.byu.edu/~cs670ta/Lectures/Minimax.html
* Mixed Strategies and Minimax  
http://students.cs.byu.edu/~cs670ta/Lectures/Minimax2.html

9. Conclusion
=============

This project is a playable implementation of Tic-Tac-Toe. Players range 
from human, bad, to good. Albeit, only one player type actually implements 
a good enough Tic-Tac-Toe strategy comparable to HumanPlayer, that player--
the GoodAIPlayer-- will always win or tie.

With time as one of the biggest constraints, the design neglected to factor 
in testabilty. Reusability was considered to make the project as easily 
modified into similar games, such as Connect 4, as easy as possible. H
owever, good software engineering practices should have considered all 
"-ilitiy" factors, no matter what the constraints were. Additionally, 
testing was done at the completion of every player, except for GoodAIPlayer 
where unit testing was started early for debugging purposes. Test-Driven 
Development could have been an option, but was not considered due to time 
constraints and the goal of creating a functional program as the top priority. 
Lastly, the documentation and quality assurance process was left until the 
near completion of the project. If time was not constraint, as it was 
repeatedly mentioned, documentation would've been done while classes 
and methods within classes were written.

As a whole, the project meets all requirements and more (because of the 
various AIs implemented, and the ability to play a game with two Human players).
