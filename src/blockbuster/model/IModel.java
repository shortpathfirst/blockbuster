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
public interface IModel {
        public int getNumColumnsOfBoard();
        public int getNumRowsOfBoard();
        public int getLevel();
        public int[][] getboardArray();
        public String getPlayerName();
        
        public void setPlayerName(String playerName);
        
        public int getScore();
        
        public void addScore();
        public boolean isEmptyCell(int i, int j); //da rimuovere forse
        public void setVisitedBlocks(int i, int j, int blockType);
        public void removeColor(int i, int j);
        public int getIncrementBlock(int index);
        
        public void pushIncrement();
        public int getBoardBlock(int i,int j);
        public int getVisitedNum();
        public void resetVisited();
        
        public void initGame();
        public void incrementLine();
        public void updateLine();
        public boolean isIncrementLineFull();
        public boolean isLevelCompleted();
        public int getLineLeft();
        public void removeVisitedBlocks();
        public void nextLevel();
        public int getIncrementlDelay();
        public int getIncrementedScore();
        public boolean islastRowEmpty();
        public boolean isLevelMode();
        public void setLevelMode(boolean state);
        public void removeSquare(int i, int j);
        public void paintSquare(int i, int j);
}
