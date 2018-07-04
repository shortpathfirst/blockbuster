/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.model;

import java.util.LinkedList;

/**
 *
 * @author Andrea
 */
public class IncrementLine {
    private static int NUM_COLUMNS; //final
    
    private int[] incrementLine;
    private int level;
    private LinkedList<Integer> incLine;
    private Block block;
    public IncrementLine(){
        NUM_COLUMNS = 15;//Model.getInstance().getNumColumnsOfBoard();
        incLine = new LinkedList<Integer>();
        this.block=new Block();
        this.level=0;//Model.getInstance().getLevel();
    }
//    if(incLine==null)
//     return 0
//    else return block
    public void addBlock(){ 

        if(incLine.size()<NUM_COLUMNS)
             this.incLine.add(block.randomBlock(level));
        //add line to board
        else this.incLine.clear(); 
        

    }   
       public int[] getIncrementLineArray(){
        int[] incrementArray =new int[NUM_COLUMNS];
        for(int i=0; i< incrementArray.length; i++){
            incrementArray[i]=this.incLine.removeFirst();
        }
        return incrementArray;
       }
    public int getBlockAt(int index){
        if(index < 0 || index >= incLine.size())
            return 0;
        return incLine.get(index);
    }
    public boolean isIncrementLineFull(){
        if(incLine.size()<=NUM_COLUMNS)
            return false;
        return true;
    }
    public void clear(){
        this.incLine.clear();
    }
    
    
    
    
    
}//end class
