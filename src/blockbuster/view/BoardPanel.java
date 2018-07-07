/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import blockbuster.controller.ControllerForView;
import blockbuster.model.Model;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import blockbuster.view.BlockStyle;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.event.MouseListener;

/**
 *
 * @author Andrea
 */
public class BoardPanel extends JPanel implements MouseListener{ //implements KeyListener 
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
	public double uX;
	public double uY;
	private Line2D.Double line;
	private Rectangle2D.Double block;
        private BufferedImage[] sprites;//////////////////
        
        private int selectedCell;
        
	public BoardPanel() {
		super();
                this.isHighlighted = true;                                      //Controller.getvalue() se ha input attiva altrimenti toglie
		this.line = new Line2D.Double();
		this.block = new Rectangle2D.Double();
                this.selectedCell = -1;
		this.setBackground(Color.BLACK);    //ColorSettings.getInstance().getColorBackgroundBoard()
		//this.addKeyListener(this);
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
		this.selectedCell = getSelectedCell(ControllerForView.getInstance().getNumRowsOfBoard()-getRowIndex(e.getY())-1, getColumnIndex(e.getX())); //get row number from model
                ControllerForView.getInstance().remove(20-getRowIndex(e.getY())-1,getColumnIndex(e.getX()),selectedCell);
//                System.out.println("[i, j] = [" + (20-getRowIndex(e.getY())-1) + ", " + getColumnIndex(e.getX()) + "]");
//                System.out.println(this.selectedCell);
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
                return Model.getInstance().getBoardBlock(x, y);
	}
        
        private void paintGrid(Graphics2D g2d) {
		Color oldColor = g2d.getColor();                                //to change block style
		g2d.setColor(Color.GRAY);

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
        
        private void drawBlockAtCell(Graphics2D g2d, int i, int j, int pieceNumber) { //piacename --> value (0,1,2..special)
                Color oldColor = g2d.getColor();
                g2d.setColor(BlockStyle.getInstance().getBlockColor(pieceNumber)); //each piace a color
                this.block.setRect(X_MARGIN + this.uX * (double)j, Y_MARGIN + this.uY * (double)(ControllerForView.getInstance().getNumRowsOfBoard() - 1 - i), this.uX, this.uY);
                g2d.fill(this.block);
                g2d.setColor(Color.CYAN);  //  ColorSettings.getInstance().getColorForOutlineOfPiece(pieceNumber)
                g2d.draw(this.block);
                g2d.setColor(oldColor);
        }
        private void drawSprites(Graphics g) { 
                    BufferedImage bigImg= null;
                try{
                    bigImg = ImageIO.read(new File("C:/Users/Andrea/Desktop/LupSalad_BlocksMisc.png"));
                }catch(IOException ex){
                     System.out.println("Could not find file");
                }

            final int width =24;
            final int height = 24;
            final int rows = 5;
            final int cols = 7;
            sprites = new BufferedImage[rows * cols];

            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    sprites[(i * cols) + j] = bigImg.getSubimage(
                        j * width,
                        i * height,
                        width,
                        height
                    );
                }
            }
            g = this.getGraphics();
            g.drawImage(sprites[1], 120, 40, 24, 24,this);
            g.drawImage(sprites[7], 120, 60, 24, 24, this);
            g.drawImage(sprites[8], 120, 80, 24, 24, this);
            g.drawImage(sprites[15], 120, 100, 24, 24, this);
            
            
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
                //drawSprites(g);
           for (int j = 0; j < ControllerForView.getInstance().getNumColumnsOfBoard(); j++) //add increment line (drawline)
			for (int i = 0; i < ControllerForView.getInstance().getNumRowsOfBoard(); i++)
                            this.drawBlockAtCell(g2d,i,j,Model.getInstance().getBoardBlock(i,j));
        }
        
}//end class
