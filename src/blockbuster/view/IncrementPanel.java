package blockbuster.view;

import blockbuster.controller.ControllerForView;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Andrea
 */
public class IncrementPanel extends JPanel {
    
    private final static int NUM_COLUMNS = 15;
    private static int CELL_WIDTH = 25;
    private static int CELL_HEIGHT = 40;
    private final static Dimension PREFERRED_SIZE = new Dimension(NUM_COLUMNS * CELL_WIDTH, CELL_HEIGHT);
    private final static int X_MARGIN = 10;
    private final static int Y_MARGIN = 10;
    
    public double uX; // Indici di posizione
    public double uY;
    private Line2D.Double line;
    private Rectangle2D.Double block;

    public IncrementPanel(){
        super();
        this.line = new Line2D.Double();
        this.block = new Rectangle2D.Double();
        this.setBackground(Color.BLACK);
    }
    //---------------------------------------------------------------
    // PRIVATE INSTANCE METHODS
    //---------------------------------------------------------------
    private void paintGrid(Graphics2D g2d) {
            Color oldColor = g2d.getColor();
            g2d.setColor(ImgSetting.getInstance().getGridColor());

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
    }
    private void drawBlockAtCell(Graphics2D g2d, int j, int blockNumber) { 
            Color oldColor = g2d.getColor();
            g2d.setColor(ImgSetting.getInstance().getBlockColor(blockNumber)); 

            this.block.setRect(X_MARGIN + this.uX * (double)j, Y_MARGIN, this.uX, this.uY);

            g2d.fill(this.block);
            g2d.setColor(ImgSetting.getInstance().getGridColor());                  
            g2d.draw(this.block);
            g2d.setColor(oldColor);
    }
     private void drawBlocks(Graphics2D g2d) { 
           for (int j = 0; j < ControllerForView.getInstance().getNumColumnsOfBoard(); j++) {
                    BufferedImage sprite = ImgSetting.getInstance().getBlockSprite(ControllerForView.getInstance().getIncrementBlock(j));
                    if(sprite != null)
                        g2d.drawImage(sprite, (int)(X_MARGIN + this.uX * j),(int)(Y_MARGIN), (int) this.uX, (int)this.uY, this);
                    else
                        this.drawBlockAtCell(g2d,j,ControllerForView.getInstance().getIncrementBlock(j));
           } 
        }
    //---------------------------------------------------------------
    // PUBLIC INSTANCE METHODS
    //---------------------------------------------------------------
    public void setGridUnit() {
            this.uX = (double)(getWidth()- 2*X_MARGIN) / (double)NUM_COLUMNS;
            this.uY = (double)(getHeight()- 2*Y_MARGIN);
    }
    @Override
    public Dimension getMaximumSize() {
            return PREFERRED_SIZE;
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
            drawBlocks(g2d);
    }//end paint
    
    
    
    
    
    
}
