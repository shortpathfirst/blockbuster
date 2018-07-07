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

//	public String getPlayerName();
//        
//        public void setPlayerName(String playerName);
        
//        public int getScore();
        
//        public void incrementScore(int increment);
        public boolean isEmptyCell(int i, int j); //da rimuovere forse
        public void setVisitedBlocks(int i, int j, int blockType);
        public void removeColor(int blockType);
        public int getBlockAt(int index);
        
        public void pushIncrement();
         public int getBoardBlock(int i,int j);
        public void TrimRows();
        
        
        
        
        public void initGame();
        
//        initialize i index and j index
        
//        public void removeRow(int indexOfRow);

    public void incrementLine();

     public void removeVisitedBlocks();
}
