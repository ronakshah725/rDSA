//package org.ronak.oop;
//
//import lombok.AllArgsConstructor;
//
//public class Connect4 {
//
//    @AllArgsConstructor
//    enum Player {
//        ONE(Token.YELLOW),
//        TWO(Token.RED);
//
//        final Token token;
//    }
//
//    @AllArgsConstructor
//    enum Token {
//        EMPTY(0),
//        YELLOW(1),
//        RED(2);
//
//        final int token;
//    }
//
//    @AllArgsConstructor
//    enum State {
//        RUNNING("Game is still running"),
//        WIN("Game is complete!!"),
//        DRAW("Game is draw"),
//        PLACEMENT_ERROR("Game is aborted");
//
//        final String message;
//    }
//
//    protected class Board {
//        private Player currentPlayer;
//        private Token[][] board;
//        private static final int K = 4;
//        private State gameState;
//        private int[] colFill;
//        private int rowBound, colBound;
//        private static String errorMessage;
//
//
//        public Board(int m, int n) {
//            this.board = new Token[m][n];
//            this.rowBound = m;
//            this.colBound = n;
//            this.currentPlayer = Player.ONE;
//            this.colFill = new int[n];
//            initBoard(m, n);
//            this.gameState = State.RUNNING;
//        }
//
//        void placeToken(Token token, int col) {
//            if (!isValidPlacement(token, col)) {
//                gameState = State.PLACEMENT_ERROR;
//                throw new IllegalStateException(gameState.message + " " + errorMessage);
//            }
//
//            int row = getRow(col);
//
//            board[row][col] = token;
//            colFill[col]++;
//            gameState = checkState(row, col);
//
//            if (gameState == State.RUNNING) {
//                currentPlayer = getNextPlayer();
//            }
//        }
//
//        /**
//         *
//         * m = 8, n = 6, k = 4
//         *      0 1 2 3 4 5
//         * 0    0 0 0 0 0 0
//         * 1    0 0 0 0 0 0
//         * 2    0 0 0 0 0 0
//         * 3    0 0 0 0 0 0
//         * 4    0 0 0 0 0 0
//         * 5    0 0 0 0 0 0
//         * 6    0 0 0 0 0 0
//         * 7    0 0 0 0 0 0
//         */
//
//        /**
//         *
//         * @param row
//         * @param col
//         * @return
//         *
//         */
//        private State checkState(int row, int col) {
//            // check Horizontal
//
//            boolean won = true;
//            Token token = board[row][col];
//
//            // check left
//            if(col >= K - 1){
//                for(int j = col - 1; j >= K - col; j-- ){
//                    if(board[row][j] != token) {
//                        won = false;
//                        break;
//                    }
//                }
//            } else {
//                for(int j = col + 1; j < col + K; j++ ){
//                    if(board[row][j] != token) {
//                        won = false;
//                        break;
//                    }
//                }
//            }
//        }
//
//        boolean isValidPlacement(Token token, int col) {
//            if (currentPlayer.token != token) {
//                errorMessage = "Wrong toke selected";
//                return false;
//            }
//            if (col >= colBound) {
//                errorMessage = "Invalid Column";
//                return false;
//            }
//            int row = getRow(col);
//            if (row >= rowBound) {
//                errorMessage = "Column is full";
//                return false;
//            }
//            return true;
//        }
//
//        private void initBoard(int m, int n) {
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    board[i][j] = Token.EMPTY;
//                }
//            }
//
//            for (int j = 0; j < n; j++) {
//                colFill[j] = 0;
//            }
//        }
//
//        private int getRow(int col) {
//            return colFill[col] + 1;
//        }
//
//        public Player getNextPlayer() {
//            return currentPlayer == Player.ONE ? Player.TWO : Player.ONE;
//        }
//
//    }
//
//
//}
//
///*
// *
// *
// *
// * */
