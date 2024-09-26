package lab04.Ex1;

public class JGaloGame implements JGaloInterface{
    private char player1;
    private char player2;
    private char[][] grid;
    private char actual_player;
    private char result;
    private int n_plays;


    /**
     * Constructor for JGaloGame. 
     * Initializes the players.
     * Sets the actual player to the player passed as argument, if it's valid. If not, sets the actual player to player1.
     * Initializes the grid, the result and the number of plays.
     * @param player - player that starts the game
     */
    public JGaloGame(char player){
        
        this.player1 = 'X';
        this.player2 = 'O';

        if (player == 'X' || player == 'O'){
            this.actual_player = player;
        }
        else if (player == ' '){
            this.actual_player = this.player1;
        }
        else{
            error("Invalid player");
        }

        this.grid = new char[3][3];
        this.result = ' ';
        this.n_plays = 0;
    }

    /**
     * @return the actual player
     */
    public char getActualPlayer(){
        return this.actual_player;
    }

    /**
     * @return player 1
     */
    public char getPlayer1(){
        return this.player1;
    }

    /**
     * @return player 2
     */
    public char getPlayer2(){
        return this.player2;
    }

    /**
     * Increments the number of plays, every time a play is made and print the play number in the terminal.
     */
    public void incrementPlays(){
        System.out.println("NPlays: " + this.n_plays);
        this.n_plays++;
    }

    /**
     * @return the number of plays
     */
    public int getNPlays(){
        return this.n_plays;
    }

    /**
     * Set the result of the game if it's finished
     */
    public void setResult(char result){
        this.result = result;
    }

    /**
     * Switch the player turn
     */
    public void setActualPlayer(){
        this.actual_player = (this.getActualPlayer() == this.getPlayer1()) ? this.getPlayer2() : this.getPlayer1();
        System.out.println("Actual player" + this.getActualPlayer());
    }

    /**
     * Verify if the play was valid. If it was, then the play is registered in the grid, the number of plays is incremented and the player turn is switched.
     * @param line - line of the play in the grid
     * @param col - column of the play in the grid
     * @return true if the play was valid, false otherwise
     */
    public boolean setJogada(int line, int col){
        line = line - 1;
        col = col - 1;
        if (validatePosition(line, col)){
            this.grid[line][col] = this.actual_player;
            this.incrementPlays();
            this.setActualPlayer();
            return true;
        }
        return false;
    }


    /**
     * Check if the coordinates of the play are valid in the grid
     * @param line - line of the play in the grid
     * @param col - column of the play in the grid
     * @return true if the position was valid, false otherwise
     */
    public boolean validatePosition(int line, int col){
        if (line < 0 || line > 2 || col < 0 || col > 2){
            return false;
        }
        if (this.grid[line][col] == '0' || this.grid[line][col] == 'X'){
            return false;
        }
        return true;
    }


    /**
     * Check if the game is finished, only if the number of plays is greater than 5 because the game should only finish after the 5th play
     * @return true if the game is finished, false otherwise
     */
    public boolean isFinished(){
        if (this.n_plays < 5){
            return false;
        }
        if (verifyLines() || verifyCols() || verifyDiagonals() || isFull()){
            return true;
        }
        return false;
    }

    /**
     * Verify if there is a winner in the lines
     * @return true if there is a winner, false otherwise
     */
    public boolean verifyLines(){
        for (int i = 0; i < 3; i++){

            if (this.grid[i][0]=='\u0000') continue;

            if (this.grid[i][0] == this.grid[i][1] && this.grid[i][1] == this.grid[i][2]){
                this.setResult(this.grid[i][0]);
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is a winner in the columns
     * @return true if there is a winner, false otherwise
     */
    public boolean verifyCols(){
        for (int i = 0; i < 3; i++){

            if (this.grid[0][i]=='\u0000') continue;
            
            if (this.grid[0][i] == this.grid[1][i] && this.grid[1][i] == this.grid[2][i]){
                this.setResult(this.grid[0][i]);
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is a winner in the diagonals
     * @return true if there is a winner in the diagonals, false otherwise
     */
    public boolean verifyDiagonals(){

        if (this.grid[1][1] == '\u0000') return false;

        if (this.grid[0][0] == this.grid[1][1] && this.grid[1][1] == this.grid[2][2]){
            this.setResult(this.grid[0][0]);
            return true;
        }
        if (this.grid[0][2] == this.grid[1][1] && this.grid[1][1] == this.grid[2][0]){
            this.setResult(this.grid[0][2]);
            return true;
        }
        return false;
    }
    
    /**
     * Verify if the grid is full, so if there is no winner, it's a draw
     * @return true if the grid is full, false otherwise
     */
    public boolean isFull(){
        if (this.getNPlays() == 9){
            return true;
        }
        return false;
    }

    /**
     * Get the result of the game
     * @return the result of the game
     */
    public char checkResult(){
        return this.result;
    }

    /**
     * Check if there was an error in the program, and if so, print the error message and exit the program
     * @param str - error message
     */
    public static void error(String str) {
	    System.err.println("ERROR " + str);
	    System.exit(1);
	}
}
