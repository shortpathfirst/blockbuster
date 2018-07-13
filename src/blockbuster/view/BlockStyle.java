/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import java.awt.Color;
import java.lang.reflect.Field;
/**
 *
 * @author Andrea
 */
public class BlockStyle {
        //---------------------------------------------------------------
	// STATIC ATTRIBUTE
	//---------------------------------------------------------------
	private static BlockStyle instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTE
	//---------------------------------------------------------------
        private Color colorBackgroundBoard;
	private Color colorBackgroundTimer;
	private Color colorGridLineBoard;
	private Color colorBlock1;
        private Color colorBlock2;
        private Color colorBlock3;
        private Color colorBlock4;
        private Color colorSpecialBlock;
        
        private Color colorBoardLine;  //For highlight
        
        private BlockStyle(){
           //Costruttore
        }
        private Color ColorSettings() {
//		try {
//			this.setColorBackgroundBoard();
//			this.setColorBackgroundTimer();
//			this.setColorOfBlock1();
//			this.setColorOfBlock2();
//                        this.setColorOfBlock3();
//                        this.setColorOfBlock4();
//                        this.setColorOfSpecialBlock();
//		}
//		catch(NoSuchFieldException nsfe) {
//			nsfe.printStackTrace();
//		}
//		catch(IllegalAccessException iae) {
//			iae.printStackTrace();
//		}
            return Color.cyan;
        }
                                                                                 //Manca il set color
        private Color getColorOfBlock1() {
                return this.colorBlock1;
	}
        private Color getColorOfBlock2() {
                return this.colorBlock2;
	}
        private Color getColorOfBlock3() {
                return this.colorBlock3;
	}
        private Color getColorOfBlock4() {
                return this.colorBlock4;
	}
        
        public Color getBlockColor(int numBlock) {  // Piace name
            
            if(numBlock ==1)
                return Color.ORANGE;
            if(numBlock ==2)
                return Color.MAGENTA;
            if(numBlock ==3)
                return Color.BLUE;
            if(numBlock ==4)
                return Color.YELLOW;
            if(numBlock ==5)
                return Color.GREEN;
            if(numBlock ==6)
                return Color.CYAN;
            if(numBlock ==7)
                return Color.BLACK;
            if(numBlock ==8)
                 return Color.GREEN;
            if(numBlock == 9)
                return Color.RED;
            else
            return Color.WHITE;
        }
        
        
	//---------------------------------------------------------------
	// STATIC METHODS
	//---------------------------------------------------------------
	public static BlockStyle getInstance() {
		if (instance == null)
			instance = new BlockStyle();
		return instance;
	}
        
}//end class
