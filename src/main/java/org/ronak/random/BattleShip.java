package org.ronak.random;

/**
 * * Design the game Battleship
 *  * The game is played on two fields, each 10 X 10 squares.
 *
 *  The columns are labeled A-J, and the rows are labeled 1-10.
 *  Each player's fleet of ships consists of
 *      one aircraft carrier, battleship, destroyer, submarine, cruiser.
 *
 *  The size and shape of each ship is as follows:
 *  * Ships
 *  * Destroyer (2 squares):
 *  * Submarine (3 squares):
 *  * Cruiser (3 squares):
 *  * Battleship (4 squares):
 *  * Aircraft Carrier (5 squares):
 *
 *  * Before the game starts, each player secretly places their ships anywhere on their own playing field.
 *  Ships cannot overlap one another, but may be placed either vertically or horizontally.
 *  * The first turn is determined by some random means (throwing a die).
 *  Players take turns to try to guess the location of the other's ships by naming a square (e.g. F7).
 *  The opponent declares the square to be a hit or a miss, depending on whether there is a ship occupying that square.
 *  When all the squares occupied by a particular ship have been guessed, the player must announce that that particular ship is sunk.
 *  A player keeps track of the hits and misses on a copy of the opponent's field.
 *  * The first player to sink all the other's ships is the winner.
 *  * Additional questions
 *  * Design this as a real-time mobile app game
 *  * How would your design scale to support hundreds/thousands/millions of concurrent games
 *  * Design for 1v1 and 1vComputer
 */

public class BattleShip {
}


/***
 *
 *
 *
 *
 * Core entities:
 * Game: Instance of a game
 * Player
 * Board
 * Ship:
 * Coordinate:
 */
