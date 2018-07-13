/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.model;

import blockbuster.view.View;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Andrea
 */
public class IncrementLine {
    private static final int NUM_COLUMNS=15;
    
    private LinkedList<Integer> incLine;
    private int lineNumber;
    
    public IncrementLine(){
        this.incLine = new LinkedList<Integer>();
        this.lineNumber=0;
    }
    public void addBlock(int level){ 
        this.incLine.add(randomBlock(level));
    }
    public int getLineNumber(){
        return this.lineNumber;
    }
    public void setLineNumber(int n){
        this.lineNumber=n;
    }
    public void updateLine(){
            this.lineNumber++;
            this.incLine.clear(); 
    }
    public int getBlockAt(int index){
        if(index < 0 || index >= incLine.size())
            return 0;
        return incLine.get(index);
    }
    public boolean isIncrementLineFull(){
        if(this.incLine.size()<NUM_COLUMNS)
            return false;
        return true;
    }
    
        private static int randomBlock(int level) {
            int BLACK_BLOCK = 6; // Non rimovibile
            int REMOVER_BLOCK = 7; //Sceglie colore a caso(presente in tavola) e rimuove 
            int REPAINT_BLOCK = 8; //Colora i vicini 3x3
            int REMOVE_SQUARE = 9;
            boolean levelMode = true;
            if(level <0)
               levelMode = false;

            int randNormalBlock=new Random().nextInt(3)+1;
            if(level>6 || !levelMode)
                randNormalBlock=new Random().nextInt(5)+1;

            final int ran = new Random().nextInt(1000); // random da 0 a 100
            if (ran < 15 && (level >0||!levelMode)) { return REPAINT_BLOCK; } //6 %      >LV1
            else if (ran <25 && (level >0||!levelMode)) { return BLACK_BLOCK; } //6%      >2
            else if (ran < 40 && (level >0||!levelMode)) { return REMOVER_BLOCK; } //3%  >3
            else if (ran< 50 && (level >0||!levelMode) ){ return REMOVE_SQUARE;}
            else { return randNormalBlock ; } //85%
    }

    
    
}//end class
