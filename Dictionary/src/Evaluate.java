/*
Hirbod Hosseini
Monday, October 10th
CS 2210
Professor Solis-Oba
Description: Evaluate object implementation
 */
public class Evaluate {
    // declaring instance variables
    private final int SIZE;
    private final int ADJECTIVAL;
    private char[][] gameBoard;
    // constructor
    public Evaluate(int size, int tilesToWin, int maxLevels){
        // initializing instance variables
        this.SIZE = size;
        this.ADJECTIVAL = tilesToWin;
        // initializing game board array
        gameBoard = new char[SIZE][SIZE];
        for (int i=0; i<size;i++){
            for (int j = 0; j<size;j++)
                gameBoard[i][j] = 'e';
        }
    }

    public Dictionary createDictionary(){
        // returning dictionary object with a prime value for size
        return new Dictionary(8069);
    }

    public Record repeatedState(Dictionary dict){
        // storing game state
        String i = getGameState();
        // get() will return appropriate values
        return dict.get(i);
    }

    // adds game state to dictionary
    public void insertState(Dictionary dict, int score, int level){
        // storing game state
        String state = getGameState();
        // creating new Record obj
        Record rec = new Record(state, score, level);
        // trying to add record to dictionary and handling duplicate key exception (game state is already stored in dict)
        try {
            dict.put(rec);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void storePlay(int row, int col, char symbol){
        gameBoard[row][col] = symbol;
    }


    public boolean squareIsEmpty(int row, int col){
        return (gameBoard[row][col] == 'e');
    }


    public boolean tileOfComputer(int row, int col){
        return (gameBoard[row][col] == 'c');
    }


    public boolean tileOfHuman(int row, int col){
        return (gameBoard[row][col] == 'h');
    }


    public boolean wins (char symbol) {
        // checking for K adjacent occurrences of symbol in all directions
        return readRowsAndColumns(symbol) || readDiagonals(symbol);
    }


    public boolean isDraw(){
        // checking if either side has NOT won and if there are NO empty spots on the game board
        return !hasSymbol('e') && !wins('h') && !wins('c');
    }

    // evaluates the board
    public int evalBoard(){
        // returning appropriate values based on game state
        if (wins('c') && !(wins('h')))
            return 3;
        if (wins('h') && !(wins('c')))
            return 0;
        if (isDraw())
            return 2;
        return 1;
    }

    // checks if game state contains symbol
    private boolean hasSymbol(char symbol){
        // storing symbol as String for comparison
        String res = "";
        res += symbol;
        // contains() will return appropriate values
        return getGameState().contains(res);
    }

    // returns game state
    private String getGameState(){
        // initializing return value
        String res = "";
        // iterating over game board
        for(int i = 0; i< SIZE; i++){
            for (int j = 0; j< SIZE; j++){
                // reading symbols
                res += gameBoard[i][j];
            }
        }
        return res;
    }

    // iterates over rows and columns looking for adj values
    private boolean readRowsAndColumns(char symbol){
        // declaring and initializing array
        char[] current = new char[SIZE];
        // iterating over each row
        for(int i = 0; i< SIZE; i++){
            // reading row
            for(int j = 0; j< SIZE; j++)
                current[j] = gameBoard[i][j];
            // checking for adj values
            if(checkForAdj(current, symbol))
                return true;
        }
        // exact same idea but with columns
        for(int i = 0; i< SIZE; i++){
            for(int j = 0; j< SIZE; j++)
                // reading column
                current[j] = gameBoard[j][i];
            // checking for adj values
            if(checkForAdj(current, symbol))
                return true;
        }
        // returning true if there are k adj values in any row OR column of the game board
        return false;
    }

    // iterates over the diagonals looking for adj values
    private boolean readDiagonals(char symbol){
        // declaring array pointer
        char[] current;
        // checking diagonals from left to right (/ direction)
        for(int i = 0; i<SIZE*2-1; i++) {
            // reinitializing array at each diagonal to reset values
            // this is because not all diagonals will have the same number of elements
            // not resetting values at each iteration would result in storing values from past diagonals
            // which could result in inappropriate returns
            current = new char[SIZE];
            // fixing row variable
            int z =0;
            if (i>=SIZE)
                z = i-SIZE+1;
            // iterating from bottom left
            for (int j = z; j <= i-z; j++) {
                // fixing column variable to bottom left
                int y = i-j;
                current[j] = gameBoard[j][y];
            }
            // checking for adj values
            if (checkForAdj(current, symbol))
                return true;
        }
        // checking diagonals from right to left (\ direction)
        for (int i = 0; i < SIZE*2-1; ++i) {
            // reinitializing array at each diagonal
            current = new char[SIZE];
            // storing adjustment value
            int z=0;
            // fixing row variable
            if (i>=SIZE)
                z = i-SIZE+1;
            // iterating from top right
            for (int j = z; j <= i - z; j++) {
                // fixing colum variable to most right index
                int y=(SIZE-1)-(i-j);
                current[j]=gameBoard[j][y];
            }
            // checking for adj values
            if (checkForAdj(current, symbol))
                return true;
        }
        return false;
    }

    // iterates over array and returns true if K adjacent values are found, false otherwise
    private boolean checkForAdj(char[] list, char symbol){
        // adj values counter
        int counter = 0;
        // iterating over array
        for(char c : list){
            // checking for matching symbol
            if (c == symbol)
                counter++;
            // K adj values are found
            if (counter == ADJECTIVAL)
                return true;
            // setting counter back 0 when a adjacent value is not symbol and K values have not been found
            if (c != symbol)
                counter = 0;
        }
        // returning false when whole array was iterated and k adj values were not found
        return false;
    }
}