/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.model;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Andrea
 */
public class IncrementLine {
    private static final int NUM_COLUMNS = 15;
    
    private static final int BLACK_BLOCK = 6; // Non rimovibile
    private static final int REMOVER_BLOCK = 7; //Sceglie colore a caso(presente in tavola) e rimuove 
    private static final int REPAINT_BLOCK = 8; //Colora i vicini 3x3
    private static final int REMOVE_SQUARE = 9;// Rimuove 3x3
    
    private LinkedList<Integer> incLine;
    private int lineNumber;
    
    public IncrementLine(){
        this.incLine = new LinkedList<>();
        this.lineNumber=0;
    }
    public void addBlock(int level){ 
        this.incLine.add(randomBlock(level));
    }
    public int getLineNumber(){
        return this.lineNumber;
    }
    public int getBlockAt(int index){
        if(index < 0 || index >= incLine.size())
            return 0;
        return incLine.get(index);
    }
    
    public void setLineNumber(int n){
        this.lineNumber=n;
    }
    public void updateLine(){
            this.lineNumber++;
            this.incLine.clear(); 
    }
    
    public boolean isIncrementLineFull(){
        return this.incLine.size() >= NUM_COLUMNS;
    }
    
    private static int randomBlock(int level) {
        boolean levelMode = level != -1;
        int randNormalBlock=new Random().nextInt(3)+1;
        if(level>6 || !levelMode)
            randNormalBlock=new Random().nextInt(5)+1;

        final int ran = new Random().nextInt(1000);
        if (ran < 20 && (level >0||!levelMode)) { return BLACK_BLOCK; }         //2 % 
        else if (ran <35 && (level >0||!levelMode)) { return REPAINT_BLOCK; }   //1,5%
        else if (ran < 50 && (level >0||!levelMode)) { return REMOVE_SQUARE; }  //1,5%
        else if (ran< 55 && (level >0||!levelMode) ){ return REMOVER_BLOCK;}    //0,5%
        else { return randNormalBlock ; } 
    }

}//end class
