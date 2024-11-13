//Jonah Rothman
//Board.java
//This is class stores the board object and rows and columns

public class Board {

    int rows;
    int cols;
    BoardCell[][] grid; //Make the board a grid of Board Cell Objects

    //Default Constructor
    public Board()
    {
        rows = 3;
        cols = 3;
        grid = new BoardCell[rows][cols];

        //Initialze each board cell
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                grid[r][c] = new BoardCell();
            }
        }
    }

    //Overloaded Constructor
    public Board(int rowInput, int colInput)
    {
        rows = rowInput;
        cols = colInput;

        grid = new BoardCell[rows][cols];

        //Initialze each board cell
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                grid[r][c] = new BoardCell();
            }
        }
    }

    //Method to update the board
    public void updateBoard(int row, int col, BoardCell Piece){
        grid[row][col].setPiece(Piece);
    }

    //Method to return the board
    public BoardCell[][] getBoard(){
        return grid;
    }


    //Method to print the Board in the terminal
    public void displayBoard(){
        //Print col numbers
        System.out.print("  ");
        for(int c = 0; c <  cols ; c++){
            System.out.print("  "+ (c+1)+"  ");
        }
        System.out.println();

        for(int r = 0; r < rows ; r++){
            //Display top line
            printDivRowLine();
            printValRowLine(r);
        }
        printDivRowLine();
    }

    //Method to print the divider row line
    public void printDivRowLine(){
        System.out.print("  ");
        for(int c = 0; c < cols; c++){
            System.out.print("+----");
        }
        System.out.println("+");
    }

    //Method to print the value row line
    public void printValRowLine(int row){
        System.out.print((row+1)+" ");
        for(int c = 0; c < cols; c++){
            System.out.print("| "+ grid[row][c].toString()+"  ");
        }
        System.out.println("|");
    }

    //Method returns if the board is full
    public boolean isBoardFull(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                if(grid[r][c].toString().equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }
}
