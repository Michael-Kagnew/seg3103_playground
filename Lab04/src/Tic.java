package src;
public class Tic {

    public  String[][] emptyBoard(int rows, int cols){
        String [][] board = new String[rows][cols];

        for(int i = 0; i < rows; i++){
            
            for(int x=0; x <cols; x++){
                if (x!=cols-1){
                    board[i][x] = "_|";

                } else {
                    board[i][x] = "_";
                }
            }
        }
        
        return board;

    }
    
}
