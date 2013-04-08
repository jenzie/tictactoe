project: Tic-Tac-Toe
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
7. Conclusion

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

1.1 Dependencies
----------------
* Java 1.6
* Maven 3.0

2. Board & Tic-Tac-Toe
======================

