/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.model;

import java.util.Random;

/**
 *
 * @author Andrea
 */
public class Block {
    protected int blockType;
    private int x;
    private int y;
    protected Block[] neighbors; 
    private boolean isVisited;
    public Block(){//int x, int y
        isVisited=false;
//        this.blockType = blockType;
        neighbors = this.getNeighborns(x, y);
        
        
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isVisited(){
        return isVisited;
    }
    public void setVisited(boolean visited){
        this.isVisited = visited;
    }
    public Block[] getNeighborns(int x, int y){
       return this.neighbors;
    }
    public static int randomBlock(int level) { //Da inserire su eventuale classe Block in model
            boolean levelMode = true;
            if(level <0)
               levelMode = false;
            Random myRandom = new Random();
            final int ran = myRandom.nextInt(100); // random da 0 a 100
            int randNormalBlock=new Random().nextInt(2)+1;
            if(level>6 || !levelMode)
                randNormalBlock=new Random().nextInt(4)+1;


            if (ran < 6 && (level >1||!levelMode)) { return REPAINT_BLOCK; } //6 %
            else if (ran < 12 && (level >2||!levelMode)) { return BLACK_BLOCK; } //6%
            else if (ran < 15 && (level >3||!levelMode)) { return REMOVER_BLOCK; } //3%
            else { return randNormalBlock ; } //85%
    }
     private final static int BLACK_BLOCK = 6; // Non rimovibile
    private final static int REMOVER_BLOCK = 7; //Sceglie colore a caso(presente in tavola) e rimuove 
    private final static int REPAINT_BLOCK = 8; //Colora i vicini 3x3
    
    

}//end class
