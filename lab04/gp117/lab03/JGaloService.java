import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public  class JGaloService  implements JGaloInterface {
    private int playerIndexCount;
    private Map<Integer, Character> playersGrid = new HashMap<>();
    private char[][] grid =  new char[3][3]; private char winner = ' ';
    private boolean isFullyOcupied = true;
    private int emptySpaces = grid.length * grid.length;
    private List<String> 
    lines = new ArrayList<>(),
    columns = new ArrayList<>(),
    diagonals = new ArrayList<>();

    //Caso o utilizador omita o jogador inicial, inicia-se com X
    public JGaloService(){
        for(int i=0; i<9; i++){
            playersGrid.put(i, 'n');
        }

        for(int i=0; i<3; i++){
           for(int j=0; j<3; j++){
            this.grid[i][j] = 'n';
           }
        }
        this.playerIndexCount = 1;
        updateGrid();


    }


    //Caso o utilizador defina o jogador inicial
    public JGaloService(char player){
        for(int i=0; i<9; i++){
            playersGrid.put(i, 'n');
        }

        for(int i=0; i<3; i++){
           for(int j=0; j<3; j++){
            this.grid[i][j] = 'n';
           }
        }

        if(player == 'o' || player == 'O'){
            this.playerIndexCount = 2;

        }
        else if (player == 'x' || player == 'X'){
            this.playerIndexCount = 1;
        }
        updateGrid();


    }

    public void updateGrid(){
        StringBuilder sb = new StringBuilder("");
        //Linhas : 
        for(int l = 0; l < grid.length ; l++){
            for(int c = 0; c < grid.length ; c++){
                sb.append(grid[l][c]);
            }
            lines.add(sb.toString());
            //System.out.println("\nLine : "+sb.toString());
            sb.setLength(0);
        }

         //Colunas : 
         for(int colIndex = 0; colIndex < grid.length; colIndex++){
            for(int line = 0; line < grid.length ; line++){
                sb.append(grid[line][colIndex]);
            }
            columns.add(sb.toString());
            //System.out.println("\nCol : "+sb.toString());
            sb.setLength(0);
        }
        sb.setLength(0);
        //Diagonals : 
        for(int l = 0; l < 3 ; l++){
            for(int c = 0; c < 3 ; c++){
                if( l == c){
                    //System.out.printf("%n [%,d , %,d]", l,c);
                     //System.out.print(grid[l][c]);
                    sb.append(grid[l][c]);
                }
            }
           
        }
        //System.out.println("\ndiagonal : "+sb.toString());
        diagonals.add(sb.toString());
        sb.setLength(0);
        //System.out.println("\n-----");

        for(int l = 0; l < grid.length  ; l++){
            for(int c = 0; c < grid.length ; c++){
                if( l ==(3-c-1)){
                    //System.out.printf("%n [%,d , %,d]", c,l);
                    //System.out.print(grid[l][c]);
                    sb.append(grid[c][l]);
                }
            }
            
        }
        diagonals.add(sb.toString());
        //System.out.println("\ndiagonal : "+sb.toString());
        sb.setLength(0);

    }

    public void incrementsPlayerIndexCount(){
        this.playerIndexCount++;
    }

   

    //Identify the players
    public char getActualPlayer(){
        //Impar --> 'x'
        char ActualPlayer;
        //System.out.printf("%n player Index : %,d", this.playerIndexCount);
        if( (this.playerIndexCount) % 2 != 0){
            ActualPlayer =  'x';

        }
        //Par --> 'o'
        else{
           ActualPlayer =  'o';
        }
        incrementsPlayerIndexCount();
        return ActualPlayer;
    }

    public char checkResult(){
        return this.winner;
    }

    public boolean setJogada(int lin, int col){

        lin = Math.round(lin)  -1 ; col = Math.round(col) -1;

        if(lin > grid.length || col > grid.length || lin < 0 || col <  0){
            System.out.printf("%n [%,d , %,d ]", lin, col);
            return false;
        }


        if(this.grid[lin][col]  == 'n'){
            this.emptySpaces --;
            getActualPlayer();
            //System.out.printf("%n Line %d Col %d", lin, col);
            this.grid[lin][col] = getActualPlayer();
            updateGrid();
            return true;
        }
        else{
            return false;
        }
        

    }

    //Check if someone winned already or no empty spaces
	public boolean isFinished(){

       
        //Verificar para ambos os jogadores
        //Player x : 
        for(String line : this.lines){
            if( line.contains("o") == false  && line.contains("n") ==  false){
                winner = 'x';
            }
        }

        for(String column : this.columns){
            if( column.contains("o") == false  && column.contains("n") ==  false){
                winner = 'x';
            }
        }

        for(String diagonal : this.diagonals){
            if( diagonal.contains("o") == false  && diagonal.contains("n") ==  false){
                winner = 'x';
            }
        }

        //Player o :
        for(String line : this.lines){
            if( line.contains("x") == false  && line.contains("n") ==  false){
                winner = 'o';
            }
        }

        for(String column : this.columns){
            if( column.contains("x") == false  && column.contains("n") ==  false){
                winner = 'o';
            }
        }

        for(String diagonal : this.diagonals){
            if( diagonal.contains("x") == false  && diagonal.contains("n") ==  false){
                winner = 'o';
            }
        }
        
        if(this.emptySpaces == 0 || winner == 'x' || winner == 'o'){
            return true;
        }

        
        return false;

    }   

    

}