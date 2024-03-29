package src;

public class Tic {

    public enum Player { X, O }

    public String[][] board;
    private Player nextTurn;

    public Tic(int rows, int cols) {
        board = emptyBoard(rows, cols);
        nextTurn = Player.X;
    }

    /**
     * 
     * @param rows
     * @param cols
     * @return an empty bored represented by a rows x columns, 2d matrix
     */
    public  String[][] emptyBoard(int rows, int cols){
        String [][] boardTemp;

        if (rows ==3 && cols == 3){
            boardTemp = new String[3][3];
            for(int i = 0; i < 3; i++){
                boardTemp[i] =new String[]{"_|","_|", "_"};
            }

            return boardTemp;
        }


        boardTemp = new String[rows][cols];
        for(int i = 0; i < rows; i++){
            
            for(int x=0; x <cols; x++){
                if (x!=cols-1){
                    boardTemp[i][x] = "_|";

                } else {
                    boardTemp[i][x] = "_";
                }
            }
        }
        return boardTemp;
    }

    /**
     * 
     * @param positionX
     * @param positionY
     * @param player
     * @return a new board with the new tic if the move is valid, the old board if the action is invalid
     */
    public String[][] tickBoard(int positionX, int positionY, Player player) {
        // check to see that the marking will be made in a valid range
        if (((positionX < 0) || (positionX > board.length)) || ((positionY < 0) || (positionY > board[0].length))) {
            return board;
        }
        // check to see that the plater making the turn is the next player
        if (player != nextTurn) {
            return board;
        }
        // apply the correct tick based on the player type
        String append = "";
        if (board[positionY][positionX].equals("_|")) {
            append = "|";
        }
        if (player == Player.X) {
            append = "X" + append;
            nextTurn = Player.O;
        } 
        else if (player == Player.O) {
            append = "O" + append;
            nextTurn = Player.X;
        }
        board[positionY][positionX] = append;
        return board;
    }
    
}
