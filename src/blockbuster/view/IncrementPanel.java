/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;
import blockbuster.controller.ControllerForView;
import blockbuster.model.Model;
import blockbuster.model.IncrementLine;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Andrea
 */
public class IncrementPanel extends JPanel implements ActionListener {
    
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
    private Timer timer;
    private boolean initialized;
    public IncrementPanel(){
        super();
        //this.setBackground(/*ColorSettings.getInstance().getColorBackgroundPreview()*/);   //Here you set the background
//        isNotPaused =Config.getInstance().isPaused() || Config.getInstance().isPrevSave();
        this.line = new Line2D.Double();
        this.block = new Rectangle2D.Double();
        this.setBackground(Color.BLACK);
//        l=new IncrementLine();
        initialized = false;
    }
    public void actionPerformed(ActionEvent e) { //è sul jpanel non jframe
//                startIncrement();
//                startTimer(Model.getInstance().getLevel()); //passa da controllerforview
                
//                l.addBlock();//nextmove() through controller for view
                //questione di istanze incLine su panel, su model ecc..
		this.repaint();
	}
//    public void startIncrement(){
//
//    }
    public void startTimer(int level){
       
        if(level<3)
            this.timer = new Timer(200, this);
        if(level==4)
            this.timer = new Timer(160, this);
        if(level==5)
            this.timer = new Timer(120, this);
        if(level==6)
            this.timer = new Timer(100, this);
        if(level>7)
            this.timer = new Timer(80, this);
       this.timer.start();
       initialized = true;
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
        
        
        
//            private void drawReferenceBlockAtCell(Graphics2D g2d, int i, int j, String pieceName) {
//		Color oldColor = g2d.getColor();
//		g2d.setColor(Color.BLUE); //get color of block or sprite
//		this.block.setRect(X_MARGIN + this.uX * (double)j, Y_MARGIN + this.uY, this.uX, this.uY);
//		g2d.fill(this.block);
//		g2d.setColor(Color.DARK_GRAY);
//		g2d.draw(this.block);
//		this.block.setRect(X_MARGIN + this.uX * 0.25 + this.uX * (double)j, Y_MARGIN + this.uY * 0.25 + this.uY * (double)(ControllerForView.getInstance().getNumRowsOfBoard() - 1 - i), this.uX * 0.5, this.uY * 0.5);
//		g2d.draw(this.block);
//		g2d.setColor(oldColor);		
//	}
     
        
        private void drawBlockAtCell(Graphics2D g2d,int j, int pieceNumber) { 
                Color oldColor = g2d.getColor();
                g2d.setColor(BlockStyle.getInstance().getBlockColor(pieceNumber)); //each piace a color
                this.block.setRect(X_MARGIN + this.uX * (double)j, Y_MARGIN, this.uX, this.uY);
                
                g2d.fill(this.block);
                g2d.setColor(Color.CYAN);  //  ColorSettings.getInstance().getColorForOutlineOfPiece(pieceNumber)
                g2d.draw(this.block);
                g2d.setColor(oldColor);
        }
        
    @Override
    public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            this.paintGrid(g2d);
            //if (this.isNotPaused )// boolean di -> Pausa o salvataggio precedente
            //Here draw the panel
            if(initialized)
                for(int j=0; j<15; j++)
                      this.drawBlockAtCell(g2d,j,Model.getInstance().getBlockAt(j));								
    }//end paint
    
    
    
    
}
