/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;
import java.awt.Dimension;
import javax.swing.JPanel;
import blockbuster.controller.ControllerForView;
import blockbuster.model.Model;
import blockbuster.utils.Config;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import javax.swing.Timer;

/**
 *
 * @author Andrea
 */
public class BoardPanel extends JPanel implements MouseListener,ActionListener{ //implements KeyListener 
    //---------------------------------------------------------------
    // STATIC CONSTANTS
    //---------------------------------------------------------------
	private final static int CELL_SIZE = 25; // numer of pixels
	private final static Dimension PREFERRED_SIZE = new Dimension(CELL_SIZE * ControllerForView.getInstance().getNumColumnsOfBoard(), CELL_SIZE * ControllerForView.getInstance().getNumRowsOfBoard());
	private final static int X_MARGIN = 10;
	private final static int Y_MARGIN = 10;
        
	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTES
	//---------------------------------------------------------------

	private boolean isHighlighted;                                            //able/disable keyboard imput
	private double uX;
	private double uY;
	private Line2D.Double line;
	private Rectangle2D.Double block;
        
        private int selectedCell;
        private int[] pos;
        //Variable for animation
        private Timer t;
        public boolean endAnimation;
        private int[][] board;
        private int i;
        private int j;
	public BoardPanel() {
		super();
                this.isHighlighted = true;                                      //Controller.getvalue() se ha input attiva altrimenti toglie
		this.line = new Line2D.Double();
		this.block = new Rectangle2D.Double();
                this.selectedCell = -1;
		this.setBackground(Color.BLACK);    //ColorSettings.getInstance().getColorBackgroundBoard()
		//this.addKeyListener(this);
                addMouseListener(this);   
                //For endAnimation
                this.t = new Timer(40, this);
                endAnimation=false;
                board= Model.getInstance().getboardArray();
                i=0;
                j=0;
	}//end constructor

        //--------------------------------------
	// java.awt.event.MouseListener methods
	//--------------------------------------

	public void mouseClicked(MouseEvent e) {
		// do nothing
	}

	public void mouseEntered(MouseEvent e)  {
		// do nothing
	}

	public void mouseExited(MouseEvent e) {
		// do nothing
	}

	public void mousePressed(MouseEvent e) {
            if(this.isEnabled()){
		this.selectedCell = getSelectedCell(e.getX(), e.getY()); 
                if(selectedCell != 0){
                    ControllerForView.getInstance().remove(ControllerForView.getInstance().getNumRowsOfBoard()-getRowIndex(e.getY())-1,getColumnIndex(e.getX()),selectedCell);
                    this.pos = new int[]{e.getX(),e.getY()};
                }
            }
	}

	public void mouseReleased(MouseEvent e)  {
		// do nothing
	}
	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
        private int getRowIndex(int y) {// [20,0] out index
		int i = -1;
                i = (int)((double)(y - Y_MARGIN) / this.uY);
		return i;
	}
	private int getColumnIndex(int x) {// [0,15] out index
		int j = -1;
                j = (int)((double)(x - X_MARGIN) / this.uX);
		return j;
	}
        private int getSelectedCell(int x, int y) {
                return Model.getInstance().getBoardBlock(ControllerForView.getInstance().getNumRowsOfBoard()-getRowIndex(y)-1, getColumnIndex(x));                 // controller...
	}
        
        public void paintGrid(Graphics2D g2d) {
		Color oldColor = g2d.getColor();                                //to change block style
//		g2d.setColor(Color.GRAY);

		int numColumns = ControllerForView.getInstance().getNumColumnsOfBoard();
		int numRows = ControllerForView.getInstance().getNumRowsOfBoard();

		// Paint the horizontal grid lines
		for (int i = 0; i <= numRows; i++) {
			this.line.setLine(X_MARGIN, Y_MARGIN + i * this.uY,
							  X_MARGIN + numColumns * this.uX, Y_MARGIN + i * this.uY);
			g2d.draw(this.line);
		}

		// Paint the vertical grid lines
		for (int j = 0; j <= numColumns; j++) {
			this.line.setLine(X_MARGIN + j * this.uX, Y_MARGIN,
							  X_MARGIN + j * this.uX, Y_MARGIN + numRows * this.uY);
			g2d.draw(this.line);
		}

		g2d.setColor(oldColor);
	} // end method paintGrid()
        
        public void drawBlockAtCell(Graphics2D g2d, int i, int j, int pieceNumber) { //piacename --> value (0,1,2..special)
                Color oldColor = g2d.getColor();
                g2d.setColor(BlockStyle.getInstance().getBlockColor(pieceNumber)); //each piace a color
                this.block.setRect(X_MARGIN + this.uX * (double)j, Y_MARGIN + this.uY * (double)(ControllerForView.getInstance().getNumRowsOfBoard() - 1 - i), this.uX, this.uY);
                g2d.fill(this.block);
                g2d.setColor(BlockStyle.getInstance().getGridColor()); 
                g2d.draw(this.block);
                g2d.setColor(oldColor);
        }

        private void drawBlocks(Graphics2D g2d) { 
           for (int j = 0; j < ControllerForView.getInstance().getNumColumnsOfBoard(); j++) 
                for (int i = 0; i < ControllerForView.getInstance().getNumRowsOfBoard(); i++){
                    BufferedImage sprite = BlockStyle.getInstance().getBlockSprite(Model.getInstance().getBoardBlock(i, j));
                    if(sprite != null)
                        g2d.drawImage(sprite, (int)(X_MARGIN + this.uX * j),(int)(Y_MARGIN+this.uY *(ControllerForView.getInstance().getNumRowsOfBoard() -1- i)), (int) this.uX, (int)this.uY, this);
                    else
                        this.drawBlockAtCell(g2d,i,j,Model.getInstance().getBoardBlock(i,j));
                }      
        }
        
	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public void setGridUnit() {
		this.uX = (double)(getWidth() - 2 * X_MARGIN) / (double)ControllerForView.getInstance().getNumColumnsOfBoard();
		this.uY = (double)(getHeight() - 1 * Y_MARGIN) / (double)ControllerForView.getInstance().getNumRowsOfBoard();
	}
        @Override
	public Dimension getPreferredSize() {
		return PREFERRED_SIZE;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
                
		Graphics2D g2d = (Graphics2D)g;
		paintGrid(g2d);  
                this.drawBlocks(g2d); 
                if(this.endAnimation == true){ //move to method
                    this.t.start();
                    for (int j = 0; j < ControllerForView.getInstance().getNumColumnsOfBoard(); j++) 
                       for (int i = 0; i < ControllerForView.getInstance().getNumRowsOfBoard(); i++){
                           drawBlockAtCell(g2d, i, j,board[i][j]);
                       } 
                }            
                if(this.pos != null && this.selectedCell!=0 && Config.getInstance().isScoreLabelOn()){
                    g2d.setFont(new Font("default", Font.BOLD, 16));
                    g2d.drawString(""+Model.getInstance().getIncrementedScore(),this.pos[0],this.pos[1]);
                }else{
                    g2d.drawString("",0,0);
                }
        }
       
        
@Override
    public void actionPerformed(ActionEvent e) {
        clearAnimation();
//        Animation1();
        this.repaint();
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
        }else{
            StopAnimation();
        }
        //to do
    }
    public void StopAnimation(){
        this.t.stop();
        i=0;
        j=0;
        this.endAnimation = false;    
    }
}//end class
