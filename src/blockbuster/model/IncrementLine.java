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
    public int lineNumber;
    
    public IncrementLine(){
        this.incLine = new LinkedList<Integer>();
        this.lineNumber=0;
    }
    public void addBlock(int level){ 
        this.incLine.add(randomBlock(level));
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
            
            boolean levelMode = true;
            
            if(level <0)
               levelMode = false;
            Random myRandom = new Random();
            final int ran = myRandom.nextInt(100); // random da 0 a 100
            int randNormalBlock=new Random().nextInt(3)+1;
            if(level>6 || !levelMode)
                randNormalBlock=new Random().nextInt(5)+1;


            if (ran < 6 && (level >1||!levelMode)) { return REPAINT_BLOCK; } //6 %
            else if (ran < 12 && (level >2||!levelMode)) { return BLACK_BLOCK; } //6%
            else if (ran < 15 && (level >3||!levelMode)) { return REMOVER_BLOCK; } //3%
            else { return randNormalBlock ; } //85%
    }

    
    
}//end class
