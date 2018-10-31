package blockbuster.model;

public interface IModel {
        public int getNumColumnsOfBoard();
        public int getNumRowsOfBoard();
        
        public String getPlayerName();
        public int getLevel();
        public int getScore();
        public int getLineLeft();
        public int getIncrementedScore();
        public int getIncrementBlock(int index);
        public int getBoardBlock(int i,int j);
        public int getVisitedBlockNumber();
        public int getIncrementlDelay();
        
        public void setPlayerName(String playerName);
        public void setLevelMode(boolean state);
        
        public void setVisitedBlocks(int i, int j, int blockType);
        public void setVisitedSquare(int i, int j);
        public void removeColor(int i, int j);
        public void removeVisitedBlocks();
        public void paintSquare(int i, int j);
        public void resetVisited();
        
        public void initGame();
        public void loadGame();
        
        public void addScore();
        public void addIncrementBlock();
        
        public void updateLine();
        
        public void nextLevel();
        
        public void pushIncrement();

        public boolean isIncrementLineFull();
        public boolean isLevelCompleted();
        public boolean islastRowEmpty();
        public boolean isLevelMode();
             
}
