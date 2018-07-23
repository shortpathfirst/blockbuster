/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;
import java.awt.Dimension;
import javax.swing.JPanel;
import blockbuster.controller.ControllerForView;
import blockbuster.utils.Config;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;

/**
 *
 * @author Andrea
 */
public class BoardPanel extends JPanel implements MouseListener{ 
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

	private double uX;
	private double uY;
	private Line2D.Double line;
	private Rectangle2D.Double block;
        
        private int selectedCell;
        private int[] pos;
        private int scorePerformed;
        
        EndLevelAnimation endLevelAnimation;

	public BoardPanel() {
		super();
		this.line = new Line2D.Double();
		this.block = new Rectangle2D.Double();
                this.selectedCell = -1;
                this.scorePerformed = 0;
		this.setBackground(Color.BLACK);   
                this.setEnabled(false);
                addMouseListener(this);     
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
                int row= getRowIndex(e.getY());
                int column =getColumnIndex(e.getX()); 
		this.selectedCell = ControllerForView.getInstance().getBoardBlock(row,column);
                if( this.selectedCell != 0){ 
                    this.pos = new int[]{e.getX(),e.getY()};
                    this.scorePerformed=ControllerForView.getInstance().remove(row,column,selectedCell);
                }
            }
	}
	public void mouseReleased(MouseEvent e)  {
		// do nothing
	}
	//---------------------------------------------------------------
	// PRIVATE INSTANCE METHODS
	//---------------------------------------------------------------
        private int getRowIndex(int y) {
		int i = -1;
                i = (int)((double)(y - Y_MARGIN) / this.uY);
		return ControllerForView.getInstance().getNumRowsOfBoard()-i-1;
	}
	private int getColumnIndex(int x) {
		int j = -1;
                j = (int)((double)(x - X_MARGIN) / this.uX);
		return j;
	}

        private void paintGrid(Graphics2D g2d) {
		Color oldColor = g2d.getColor();

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
	} 
        
        private void drawBlocks(Graphics2D g2d) { 
           for (int j = 0; j < ControllerForView.getInstance().getNumColumnsOfBoard(); j++) 
                for (int i = 0; i < ControllerForView.getInstance().getNumRowsOfBoard(); i++){
                    BufferedImage sprite = imgSetting.getInstance().getBlockSprite(ControllerForView.getInstance().getBoardBlock(i, j));
                    if(sprite != null)
                        g2d.drawImage(sprite, (int)(X_MARGIN + this.uX * j),(int)(Y_MARGIN+this.uY *(ControllerForView.getInstance().getNumRowsOfBoard() -1- i)), (int) this.uX, (int)this.uY, this);
                    else
                        this.drawBlockAtCell(g2d,i,j,ControllerForView.getInstance().getBoardBlock(i,j));
                }      
        }
        private void drawBlockAtCell(Graphics2D g2d, int i, int j, int blockNumber) { 
                Color oldColor = g2d.getColor();
                g2d.setColor(imgSetting.getInstance().getBlockColor(blockNumber));
                this.block.setRect(X_MARGIN + this.uX * (double)j, Y_MARGIN + this.uY * (double)(ControllerForView.getInstance().getNumRowsOfBoard() - 1 - i), this.uX, this.uY);
                g2d.fill(this.block);
                g2d.setColor(imgSetting.getInstance().getGridColor()); 
                g2d.draw(this.block);
                g2d.setColor(oldColor);
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
		this.paintGrid(g2d);  
                this.drawBlocks(g2d); 
                if(this.endLevelAnimation!=null && this.endLevelAnimation.isAnimationOn()){
                    endLevelAnimation.start();
                    for (int j = 0; j < ControllerForView.getInstance().getNumColumnsOfBoard(); j++) 
                       for (int i = 0; i < ControllerForView.getInstance().getNumRowsOfBoard(); i++){
                            drawBlockAtCell(g2d, i, j,endLevelAnimation.getBlock(i, j));
                       } 
                }            
                if(Config.getInstance().isScoreLabelOn() && this.pos != null && this.selectedCell!=0 && this.scorePerformed>4){
                    g2d.setFont(new Font("default", Font.BOLD, 16));
                    g2d.drawString(""+this.scorePerformed,this.pos[0],this.pos[1]);
                }
        }
       public void initEndAnimation(){
            endLevelAnimation = new EndLevelAnimation();
        }
        public void StopAnimation(){
            if(this.endLevelAnimation != null)
                this.endLevelAnimation.StopAnimation();
        }
        public void updateBoard(){
            this.repaint();
        }
}//end class
