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
    private static final int NUM_COLUMNS=15;
    
    private LinkedList<Integer> incLine;
    private Block block;
    
    public IncrementLine(){
        incLine = new LinkedList<Integer>();
        this.block=new Block();
    }
//    if(incLine==null)
//     return 0
//    else return block
    public void addBlock(int level){ 
        if(incLine.size()<NUM_COLUMNS)
             this.incLine.add(block.randomBlock(level));
        //add line to board
        else{
            Model.getInstance().pushIncrement();
            this.incLine.clear(); 
        }
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
