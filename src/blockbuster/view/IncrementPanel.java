/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;
import blockbuster.controller.ControllerForView;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

/**
 *
 * @author Andrea
 */
public class IncrementPanel extends JPanel {
    
    private final static int NUM_COLUMNS = 15;
    private static int CELL_SIZE = 25;//Number of pixel given by a proprtion
    private final static Dimension PREFERRED_SIZE = new Dimension(NUM_COLUMNS * CELL_SIZE, CELL_SIZE);
    private final static int X_MARGIN = 5;
    private final static int Y_MARGIN = 5;
    //private boolean isNotPaused;
    
    public double uX; // Indici di posizione
    public double uY;
    private Line2D.Double line;
    private Rectangle2D.Double block;
    
    public IncrementPanel(){
        super();
        //this.setBackground(/*ColorSettings.getInstance().getColorBackgroundPreview()*/);   //Here you set the background
//        isNotPaused =Config.getInstance().isPaused() || Config.getInstance().isPrevSave();
        this.line = new Line2D.Double();
        this.block = new Rectangle2D.Double();
        this.setBackground(Color.BLACK);
    }
    private void paintGrid(Graphics2D g2d) {
		Color oldColor = g2d.getColor();
		g2d.setColor(Color.GRAY); //colorsetting getcolor for textures

		// Paint the horizontal grid lines
		
                for (int i = 0; i <= 1; i++) {
			this.line.setLine(X_MARGIN, Y_MARGIN + i * this.uY,
							  X_MARGIN + 15 * this.uX, Y_MARGIN + i * this.uY);
			g2d.draw(this.line);
		}

		// Paint the vertical grid lines
		for (int j = 0; j <= 15; j++) {
			this.line.setLine(X_MARGIN + j * this.uX, Y_MARGIN,
							  X_MARGIN + j * this.uX, Y_MARGIN + 1 * this.uY);
			g2d.draw(this.line);
		}

		g2d.setColor(oldColor);
	} // end method paintGrid()
	//---------------------------------------------------------------
	// PUBLIC INSTANCE METHODS
	//---------------------------------------------------------------
	public void setGridUnit() {
		this.uX = (double)(getWidth() - 2 * X_MARGIN) / (double)NUM_COLUMNS;
		this.uY = (double)(getHeight() - 2 * Y_MARGIN);
	}
	@Override
	public Dimension getMaximumSize() {
		return PREFERRED_SIZE;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERRED_SIZE;
	}
        
        
        
//
//                private void paintFilledBoardCells(Graphics2D g2d) {
//		Color oldColor = g2d.getColor();
//		for (int j = 0; j < 15; j++)
//                            if (!ControllerForView.getInstance().isEmptyCell(1, j))
//                                    this.drawBlockAtCell(g2d, 1, j, ControllerForView.getInstance().getBlock(1, j));
//		g2d.setColor(oldColor);
//	} // end method paintFilledBoardCells()
//                private void drawBlockAtCell(Graphics2D g2d, int i, int j, int pieceNumber) { 
//                Color oldColor = g2d.getColor();
//                g2d.setColor(BlockStyle.getInstance().getBlockColor(pieceNumber)); //each piace a color
//                this.block.setRect(X_MARGIN + this.uX * (double)j, Y_MARGIN + this.uY, this.uX, this.uY);
//                g2d.fill(this.block);
//                g2d.setColor(Color.CYAN);  //  ColorSettings.getInstance().getColorForOutlineOfPiece(pieceNumber)
//                g2d.draw(this.block);
//                g2d.setColor(oldColor);
//        }
        
    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            this.paintGrid(g2d);
            //if (this.isNotPaused )// boolean di -> Pausa o salvataggio precedente
            //Here draw the panel
    }
    
    
    
    
}
