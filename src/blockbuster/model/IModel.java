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

//	public String getPlayerName();
//        
//        public void setPlayerName(String playerName);
        
//        public int getScore();
        
//        public void incrementScore(int increment);
        public boolean isEmptyCell(int i, int j); //da rimuovere forse
        public void removeVisitedRows(int indexOfColumn);
        public void removeColor(int blockType);
        
        
        
        
        
        
        
        
        
        
        public void initGame();
        
//        initialize i index and j index
        
//        public void removeRow(int indexOfRow);
     
}
