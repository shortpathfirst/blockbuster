/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.controller.ControllerForView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Andrea
 */
public class EndLevelAnimation implements ActionListener {
        private Timer t;
        private boolean endAnimation;
        private int[][] board;
        private int i;
        private int j;
        
        
        public EndLevelAnimation(){
            initEndAnimation();
        }
        public void initEndAnimation(){
                this.t = new Timer(40, this);
                this.board = new int[ControllerForView.getInstance().getNumRowsOfBoard()][ControllerForView.getInstance().getNumColumnsOfBoard()];
                for (int j = 0; j < ControllerForView.getInstance().getNumColumnsOfBoard(); j++) 
                       for (int i = 0; i < ControllerForView.getInstance().getNumRowsOfBoard(); i++)
                           this.board[i][j] = ControllerForView.getInstance().getBoardBlock(i, j);     
                this.endAnimation=true;
                this.i=0;
                this.j=0;
        }
        public void start(){
            this.t.start();
        }
        public int getBlock(int i,int j){
            return this.board[i][j];
        }
        public void actionPerformed(ActionEvent e) {
//        clearAnimation();
//        Animation1();
//             this.repaint(); tell board to repaint
         }
        private void clearAnimation(){
    this.board[i][j] = 0;
        if(j<this.board[0].length-1){
             j++;
        }else if (i<this.board.length-1){
            j=0;
            i++;
        }else
            StopAnimation();  
}
    private void Animation1(){
        while(board[i][j]!=0){
            if(j<board[0].length-1)
                 j++;
            else {
                j=0;
                i++;
            }
        }
        if(i<this.board.length-1){
            board[i][j]=1;
            if(j<board[0].length-1)
            j++;
            else j=0;
        }else
            StopAnimation();
    }
    public void StopAnimation(){
        this.t.stop();
        i=0;
        j=0;
        this.endAnimation = false;    
    }
}
