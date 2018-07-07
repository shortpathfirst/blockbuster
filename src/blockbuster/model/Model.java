/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.model;

import java.util.*;


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
    private final static int GREEN_BLOCK = 2;
    private final static int RED_BLOCK = 3;
    
    private final static int YELLOW_BLOCK = 4;
    private final static int PURPLE_BLOCK = 5;
    //BLOCCHI SPECIALI
    private final static int BLACK_BLOCK = 6; // Non rimovibile
    private final static int REMOVER_BLOCK = 7; //Sceglie colore a caso(presente in tavola) e rimuove 
    private final static int REPAINT_BLOCK = 8; //Colora i vicini 3x3
                   
    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private int score;
    private String playerName;
    private int[][] boardArray;
    private boolean[][] visitedArray;
//    private Block[][] boardAr;
    private int level;
    private IncrementLine incLine;

    private Model() {
            this.boardArray = new int[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
            this.visitedArray = new boolean[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
            this.initGame();
    }
    //---------------------------------------------------------------
    // PRIVATEINSTANCE METHODS
    //---------------------------------------------------------------
    
    private void initBoardArray(int rows, int columns) {//Modificato da jtetris
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++){
				this.boardArray[i][j] = EMPTY_CELL;
                                this.visitedArray[i][j] = false; //andrebbe per ogni istanza di rimozione
                        }
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

//        public void setPlayerName(String playerName) {
//		this.playerName = playerName;
//	}
//        public String getPlayerName() {
//		return this.playerName;
//	}
//        public int getScore() {
//		return this.score;
//	}
//        public void incrementScore(int increment) {
//		this.score += increment;
//	}
        
        public void initGame() {
                this.score = 0;
                this.level=0;
                if (this.playerName == null)
                        this.playerName = "Unknown";
                this.initBoardArray(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLUMNS);
                incLine = new IncrementLine();
        }
    	public boolean isEmptyCell(int i, int j) {
		boolean isEmptyCell = false;
		if ((0 <= i) && (i < DEFAULT_NUM_ROWS) && (0 <= j) && (j < DEFAULT_NUM_COLUMNS) && (this.boardArray[i][j] == EMPTY_CELL))
			isEmptyCell = true;
		return isEmptyCell;
	}
        
        //se colonna si svuota chiamo metodo per avvicinare le colonne
        
        public void setVisitedBlocks(int i, int j, int blockType) {//findconnectedblocks()
            //setVisited() tutti i blocchi vicini uguali
            setVisited(i,j);
                    if(i<this.boardArray.length && blockType == this.getBoardBlock(i+1, j) && !this.visitedArray[i+1][j]){//top
                        setVisitedBlocks(i+1,j,blockType);
                    }
                    if(j< this.boardArray[0].length-1 && blockType == this.getBoardBlock(i, j+1)&& !this.visitedArray[i][j+1]){//right
                        setVisitedBlocks(i,j+1,blockType);
                    }
                    if(j>0 && blockType == this.getBoardBlock(i, j-1)&& !this.visitedArray[i][j-1]){//left
                        setVisitedBlocks(i,j-1,blockType);
                    }
                    if(i>0 && blockType == this.getBoardBlock(i-1, j) && !this.visitedArray[i-1][j]){ //bottom
                        setVisitedBlocks(i-1,j,blockType);
                    }

            
	} 
         public void removeVisitedBlocks(){
             for(int j=0; j<this.boardArray[0].length;j++){
                   List<Integer> boardColumn = new ArrayList<Integer>();
                    for(int i=0; i<this.boardArray.length;i++){
                        if(!this.visitedArray[i][j])
                            boardColumn.add(this.boardArray[i][j]);
                    }
                    for(int i=0; i<boardColumn.size();i++){
                          this.boardArray[i][j]=boardColumn.get(i);
                    }
                    for(int i=boardColumn.size(); i<this.boardArray.length-boardColumn.size();i++){
                          this.boardArray[i][j]=0;
                    }
             }
             this.visitedArray = new boolean[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
         }//end method
         
        public void removeColor(int blockType){
                //To do
        } 
        //used for remove, move to other class
        public void setVisited(int i, int j) { //to do
               this.visitedArray[i][j] = true;
        }
    //end for remove

         public int getLevel(){ 
            return this.level;
        }
         public void nextLevel(){
             this.level++;
         }
         public Block[] getNeighbor(int x, int y){
            Block[] neighbors = new Block[3];
                if(y==this.boardArray[0].length) neighbors[0] = null;
                else neighbors[0].blockType = this.boardArray[x][y+1];//[0]Top
                if(x==0) neighbors[1] = null;
                else neighbors[1].blockType = this.boardArray[x-1][y];//[1]Left
                if(x==this.boardArray.length) neighbors[2] = null;
                else neighbors[2].blockType = this.boardArray[x+1][y];//[2]Right
                if(y==0) neighbors[3] = null;
                else neighbors[3].blockType = this.boardArray[x][y-1];//[3]Bottom
            
            return neighbors;
         }
        
        
        
        public void incrementLine(){
            this.incLine.addBlock(this.level);
        }
        public void pushIncrement(){//check game over
           	for (int i = this.boardArray.length-2; i>=0; i--)
			for (int j = 0; j < this.boardArray[i].length; j++){
                            this.boardArray[i+1][j] = this.boardArray[i][j];
                        }
                for (int j = 0; j < this.boardArray[0].length; j++)  {
                    this.boardArray[0][j] = this.incLine.getBlockAt(j);
                }
        }
        public int getBlockAt(int index){
            return this.incLine.getBlockAt(index);
        }
        public int getBoardBlock(int i,int j){
            return this.boardArray[i][j];
        }
    
        
        
        
        
        
        
    
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
