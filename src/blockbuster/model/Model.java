package blockbuster.model;

import blockbuster.utils.Config;
import blockbuster.utils.SoundPlayer;
import java.util.*;


public class Model implements IModel {
    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
    private final static int DEFAULT_NUM_ROWS = 20;
    private final static int DEFAULT_NUM_COLUMNS = 15;

    //---------------------------------------------------------------
    // INSTANCE ATTRIBUTES
    //---------------------------------------------------------------
    private int score;
    private int incrementedScore;
    private String playerName;
    private int[][] boardArray;
    private boolean[][] visitedArray;
    private int level;
    private IncrementLine incLine;
    private int numVisitedBlocks;
    
    private Model() {
            this.boardArray = new int[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
            this.visitedArray = new boolean[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
            this.initGame();
    }

    private void initBoardArray(int rows, int columns) {
            this.boardArray = new int[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < columns; j++){
                            this.boardArray[i][j] = 0; //EMPTY_CELL
                            this.visitedArray[i][j] = false; 
                        }
        
	}
    private int getLinesOfLevel(){
            if(this.level==0)
                return 20;
            else if(this.level==1)
                return 25;
            else if(this.level==2)
                return 30;
            else if(this.level==3)
                return 40;
            else if(this.level==4)
                return 45;
            else if(this.level==5)
                return 50;
            else if(this.level==6)
                return 60;
            else if(this.level==7)
                return 65;
            else if(this.level==8)
                return 70;
            else if(this.level==9)
                return 75;
            else return 80;
        }
        private int getGameOverIndex(){
            return this.boardArray.length-1;
        }
        public int getNumColumnsOfBoard() {
            return this.boardArray[0].length;
        }
        public int getNumRowsOfBoard() {
            return this.boardArray.length;
        }
        public String getPlayerName() {
            return this.playerName;
	}
        public int getScore() {
            return this.score;
	}
        public int getVisitedBlockNumber(){
            return this.numVisitedBlocks;
        }
        public int getIncrementedScore(){ 
            return this.incrementedScore =(int)(numVisitedBlocks*Math.log(numVisitedBlocks)/Math.log(2));
        }
        public int getLevel(){ 
            return this.level;
        }
        public int getLineLeft(){
            int lines;
            if(this.isLevelMode())
                lines = getLinesOfLevel()-this.incLine.getLineNumber();
            else lines=this.incLine.getLineNumber()+1;
            return lines;
        }
        public int getIncrementBlock(int index){
            return this.incLine.getBlockAt(index);
        }
        public int getBoardBlock(int i,int j){
           if(i<this.boardArray.length && j<this.boardArray[0].length)
                return this.boardArray[i][j];
           else return 0;
        }
        public int getIncrementlDelay(){
             if(this.level==0 || this.level==1 || this.level==2)
                 return 60;
             else if(this.level==3)
                return 60;
            if(this.level==4)
                return 60;
            if(this.level==5)
                return 50;
            if(this.level==6)
                return 45;
            if(this.level==7)
                return 40;
            if(this.level==8)
                return 35;
            if(this.level==9)
                return 30;
            if(this.level==10) 
                return 20;
            else 
                return 40;
        }
        public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
        public void setLevelMode(boolean state){
            if(state == false)
                this.level=-1;
            else
                this.level=0;
        }
        public void addScore() {
            this.score += getIncrementedScore(); 
	}
        
        public void initGame() {
            this.score = 0;
            if(this.isLevelMode())
                this.level=0;
            else
                this.level=-1;
            
            if (this.playerName == null)
                    this.playerName = "Unknown";
            this.initBoardArray(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLUMNS);
            this.numVisitedBlocks = 0;
            this.incLine = new IncrementLine();
            this.incLine.setLineNumber(0);
            new SoundPlayer("bcMusic");
        }
        public void loadGame(){
            this.score =  Config.getInstance().getScore();
            this.playerName = Config.getInstance().getPlayerName(); 
            this.level = Config.getInstance().getLevel();
            this.incLine.setLineNumber(this.getLinesOfLevel()-Config.getInstance().getLineLeft());
            
            for (int i = 0; i < this.boardArray.length; i++)
                for (int j = 0; j < this.boardArray[0].length; j++)
                    this.boardArray[i][j] = Config.getInstance().getBoardBlock(i, j);
        }
        
        public void setVisitedBlocks(int i, int j, int blockType) {
            numVisitedBlocks++;
                    this.visitedArray[i][j] = true;
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
                    for(int i=0; i<boardArray.length;i++){
                        if(i<boardColumn.size())
                          this.boardArray[i][j]=boardColumn.get(i);
                        else
                            this.boardArray[i][j]=0;
                    }
             }
            this.trimColumns();
         }
       public void resetVisited(){
            this.visitedArray = new boolean[DEFAULT_NUM_ROWS][DEFAULT_NUM_COLUMNS];                                         
            this.numVisitedBlocks = 0;
        }
        
        private void trimColumns(){
                for (int j =1; j < this.boardArray[0].length/2; j++){
                    if(this.boardArray[0][j]==0){
                        removeRightColumn(j);
                    }
                }
                for (int j = this.boardArray[0].length-2; j >= this.boardArray[0].length/2; j--){
                    if(this.boardArray[0][j]==0){
                        removeLeftColumn(j);
                    }
                }
                
        }
        private void removeRightColumn(int indexOfColumn){
                for (int j = indexOfColumn; j >= 0; j--){
                    for(int i = 0; i<this.boardArray.length; i++){
                        if(j!=0)
                            this.boardArray[i][j] = this.boardArray[i][(j-1)];
                        else    this.boardArray[i][j] = 0;
                    }
                }
        }
        private void removeLeftColumn(int indexOfColumn){
                for (int j = indexOfColumn; j < this.boardArray[0].length; j++)
                    for(int i = 0; i<this.boardArray.length; i++){
                        if (j < this.boardArray[0].length - 1)
                                this.boardArray[i][j] = this.boardArray[i][(j+1)];
                        else this.boardArray[i][j] = 0;
                    }

        }
        public void removeColor(int i, int j){
            int ranColor=new Random().nextInt(3)+1;
                if(this.level>=6 || !this.isLevelMode())
                    ranColor=new Random().nextInt(5)+1;;
                this.visitedArray[i][j] = true;
                for (int column = 0; column < this.boardArray[0].length; column++)
                     for (int row = 0; row < this.boardArray.length; row++)
                            if(this.boardArray[row][column]==ranColor){
                                this.visitedArray[row][column] = true;
                                numVisitedBlocks++;
                            }
        } 
        public void setVisitedSquare(int i, int j){
            this.visitedArray[i][j] = true;
            if(i>0 && j>0 && j<this.boardArray[0].length-1){
                this.visitedArray[i+1][j-1] = true; //left up corner
                this.visitedArray[i+1][j] = true; //top
                this.visitedArray[i+1][j+1] = true; //right up corner
                
                this.visitedArray[i][j-1] = true; // left
                this.visitedArray[i][j+1] = true; // right
                
                this.visitedArray[i-1][j-1] = true; //left bottom corner
                this.visitedArray[i-1][j] = true; //bottom
                this.visitedArray[i-1][j+1] = true; //right bottom corner
                numVisitedBlocks = 8; 
            }else if(j==0 && i>0){
                this.visitedArray[i+1][j] = true; //top
                this.visitedArray[i+1][j+1] = true; //right up corner
                this.visitedArray[i][j+1] = true; // right
                this.visitedArray[i-1][j] = true; //bottom
                this.visitedArray[i-1][j+1] = true; //right bottom corner
                numVisitedBlocks = 5; 
            }else if(j==this.boardArray[0].length-1 && i>0){
                this.visitedArray[i+1][j-1] = true; //left up corner
                this.visitedArray[i+1][j] = true; //top
                this.visitedArray[i][j-1] = true; // left
                this.visitedArray[i-1][j-1] = true; //left bottom corner
                this.visitedArray[i-1][j] = true; //bottom
                numVisitedBlocks = 5; 
            }else if(j==0 && i ==0){
                this.visitedArray[i+1][j] = true; //top
                this.visitedArray[i][j+1] = true; // right
                this.visitedArray[i+1][j+1] = true; //right up corner
                numVisitedBlocks = 3; 
            }else if(j==this.boardArray[0].length-1 && i==0){
                this.visitedArray[i+1][j] = true; //top
                this.visitedArray[i][j-1] = true; // left
                this.visitedArray[i+1][j-1] = true; //left up corner
                numVisitedBlocks = 3; 
            }else if(i==0 && j>0){
                this.visitedArray[i+1][j-1] = true; //left up corner
                this.visitedArray[i+1][j] = true; //top
                this.visitedArray[i][j-1] = true; // left
                this.visitedArray[i][j+1] = true; // right
                this.visitedArray[i+1][j+1] = true; //right up corner
                numVisitedBlocks = 5; 
            }
        }
        public void paintSquare(int i, int j){
            int ranColor=new Random().nextInt(3)+1;
                if(this.level>=6 || !this.isLevelMode())
                    ranColor=new Random().nextInt(5)+1;;
             setVisitedSquare(i,j);
             for (int row = 0;row<this.boardArray.length; row++)
			for (int column = 0; column < this.boardArray[0].length; column++){
				if(this.visitedArray[row][column]==true && this.boardArray[row][column]!= 0)
                                    this.boardArray[row][column]=ranColor;
                        }
             numVisitedBlocks = 0;
        }

        public void nextLevel(){
             this.incLine.setLineNumber(0);
             this.initBoardArray(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLUMNS);
             if(this.level<10)
             this.level++;
         }
        
        public void updateLine(){
            this.incLine.updateLine();
        }
        public void addIncrementBlock(){
            this.incLine.addBlock(this.level);
        }
        public void pushIncrement(){
           	for (int i = this.boardArray.length-2; i>=0; i--)
			for (int j = 0; j < this.boardArray[i].length; j++){
                            this.boardArray[i+1][j] = this.boardArray[i][j];
                        }
                for (int j = 0; j < this.boardArray[0].length; j++)  {
                    this.boardArray[0][j] = this.incLine.getBlockAt(j);
                }
        }

        
        public boolean isLevelMode(){
            return this.level != -1;
        }
        public boolean isIncrementLineFull(){
            return this.incLine.isIncrementLineFull();
        }
        public boolean isLevelCompleted(){
            return getLineLeft() ==0; 
        }
        public boolean islastRowEmpty(){
            for(int j=0; j<this.boardArray[0].length;j++){
                if(this.boardArray[getGameOverIndex()][j]!= 0)
                    return false;
            }
            return true;
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
