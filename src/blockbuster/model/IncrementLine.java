/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.model;

import blockbuster.view.View;
import java.util.LinkedList;

/**
 *
 * @author Andrea
 */
public class IncrementLine {
    private static final int NUM_COLUMNS=15;
    
    private LinkedList<Integer> incLine;
    private Block block;
    public int lineNumber;
    
    public IncrementLine(){
        incLine = new LinkedList<Integer>();
        lineNumber=0;
        this.block=new Block();
    }
    public void addBlock(int level){ 
        if(incLine.size()<NUM_COLUMNS)
             this.incLine.add(block.randomBlock(level));
        else{
            Model.getInstance().pushIncrement();
            lineNumber++;
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
