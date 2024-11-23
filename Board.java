//Jonah Rothman
//Board.java
//This is class stores the board object and rows and columns

import java.util.List;

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
            System.out.print("   "+ (c+1)+"   ");
        }
        System.out.println();

        for(int r = 0; r < rows ; r++){
            //Display top line
            printDivRowLine();
            printValRowLine(r);
        }
        printDivRowLine();
    }

    //Method to print the Board in the terminal with monster and player pos
    public void displayBoard(int[][] playerPositions, List<int[]> monstersPositions) {
        //Print col numbers
        System.out.print("  ");
        for(int c = 0; c <  cols ; c++){
            System.out.print("   "+ (c+1)+"   ");
        }
        System.out.println();

        for(int r = 0; r < rows ; r++){
            //Display top line
            printDivRowLine();

            System.out.print((r+1)+" ");
            for(int c = 0; c < cols; c++) {

                boolean heroAtSpace = false;
                boolean monsterAtSpace = false;

                int heroIdx;
                for (heroIdx = 0; heroIdx < 3; heroIdx++) {
                    int[] pos = playerPositions[heroIdx];
                    //If hero is at this square
                    if (pos[0] == r && pos[1] == c) {
                        heroAtSpace = true;
                        break;
                    }
                }

                for (int[] monsterPos : monstersPositions) {
                    if (monsterPos[0] == r && monsterPos[1] == c) {
                        monsterAtSpace = true;
                    }
                }

                //When there is only hero
                if(heroAtSpace && !monsterAtSpace){
                    System.out.print("| " + new BoardCell(new HeroSpace(heroIdx)) + " " + grid[r][c].toString() + " ");
                }
                //when there is only monster
                else if(monsterAtSpace && !heroAtSpace){
                    System.out.print("|  " + new BoardCell(new MonsterSpace()) + " " + grid[r][c].toString() + " ");
                }
                //when there is both hero and monster at space
                else if(monsterAtSpace && heroAtSpace){
                    System.out.print("|" + new BoardCell(new HeroSpace(heroIdx)) + " " + grid[r][c].toString() + " " + new BoardCell(new MonsterSpace()));
                }

                  
                    //normal printing for when no entitity at space
                    if (!(heroAtSpace || monsterAtSpace)) {
                        //Fix spacing for when H1 takes up two spaces
                        if (grid[r][c].toString().length() > 12) {
                            System.out.print("|  " + grid[r][c].toString() + "  ");
                        } else {
                            System.out.print("|  " + grid[r][c].toString() + "   ");
                        }
                    }
                }
                System.out.println("|");
            }
        printDivRowLine();
    }

    
    //Method to print the divider row line
    public void printDivRowLine(){
        System.out.print("  ");
        for(int c = 0; c < cols; c++){
            System.out.print("+------");
        }
        System.out.println("+");
    }

    //Method to print the value row line
    public void printValRowLine(int row){
        System.out.print((row+1)+" ");
        for(int c = 0; c < cols; c++){

            //Fix spacing for when H1 takes up two spaces
            if(grid[row][c].toString().length()>12){
                System.out.print("|  "+ grid[row][c].toString()+"  ");
            }else {
                System.out.print("|  "+ grid[row][c].toString()+"   ");
            }
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
