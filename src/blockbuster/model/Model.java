/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.model;

/**
 *
 * @author Andrea
 */
public class Model implements IModel {
    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
    private final static int DEFAULT_NUM_ROWS = 20;
    private final static int DEFAULT_NUM_COLUMNS = 15;
    
    //DEFINE GAME PIECES INDEX
    private final static int EMPTY_CELL = 0;
    private final static int BLUE_BLOCK = 1;
    private final static int GREEN_BLOCK = 1;
    private final static int RED_BLOCK = 1;
    
    private final static int YELLOW_BLOCK = 2;
    private final static int PURPLE_BLOCK = 3;
    //BLOCCHI SPECIALI
    private final static int BLACK_BLOCK = 4; // Non rimovibile
    private final static int REMOVER_BLOCK = 5; //Sceglie colore a caso(presente in tavola) e rimuove 
    private final static int REPAINT_BLOCK = 6; //Colora i vicini 3x3
                   
    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private int score;
    private String playerName;
    private int[][] boardArray;
    //Index of the line of increment (inside the line)
    //Structure of the increment line

    private Model() {
            this.boardArray = new int[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
            this.initGame();
    }
    //---------------------------------------------------------------
    // PRIVATEINSTANCE METHODS
    //---------------------------------------------------------------
    
    private void initBoardArray(int rows, int columns) {
		for (int i = 0; i < this.boardArray.length; i++)
			for (int j = 0; j < this.boardArray[i].length; j++)
				this.boardArray[i][j] = EMPTY_CELL;
	}
    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------
        public int getNumColumnsOfBoard() {
                return this.boardArray[0].length;
        }

        public int getNumRowsOfBoard() {
                return this.boardArray.length;
        }



        public void initGame() {
                this.score = 0;
                if (this.playerName == null)
                        this.playerName = "Unknown";
                this.initBoardArray(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLUMNS);
                //Initialize first blocks
        }
    
    	public boolean isEmptyCell(int i, int j) {
		boolean isEmptyCell = false;
		if ((0 <= i) && (i < DEFAULT_NUM_ROWS) && (0 <= j) && (j < DEFAULT_NUM_COLUMNS) && (this.boardArray[i][j] == EMPTY_CELL))
			isEmptyCell = true;
		return isEmptyCell;
	}
        public void removeVisitedRows(int indexOfColumn) {
		for (int j = 0; j < this.boardArray[indexOfColumn].length; j++){ // PER OGNI RIGA DA QUELLA DA ELIMINARE
                    int row = 0;
                    while(!isEmptyCell(indexOfColumn,j+row) && isCellVisited(indexOfColumn,j+row)){
                       this.boardArray[indexOfColumn][j]=this.boardArray[indexOfColumn][j+row+1];
                       row++;
                    }      
                }
	} //se colonna si svuota chiamo metodo per avvicinare le colonne
        
        public void removeColor(int blockType){
            for (int i  = 0; i < this.boardArray.length; i++){
                 for (int j = 0; j < this.boardArray[i].length; j++){
                     if(this.boardArray[i][j]==blockType && blockType <=3)
                         setVisited(i,j,true);
                 }
                 removeVisitedRows(i);
            }
        } 
        
        public boolean setVisited(int i, int j,boolean value) { //to do
            return value; //visited= value; (set, get)
        }
        public boolean isCellVisited(int i, int j) { // to do
		boolean isCellVisited = false;
		
		return isCellVisited;
	}
    //TO DO INTERFACE METHODS
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        //---------------------------------------------------------------
	// STATIC FIELDS
	//---------------------------------------------------------------
	private static Model instance = null;
        
        //---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static IModel getInstance() {
		if (instance == null)
			instance = new Model();
		return instance;
	}
}
