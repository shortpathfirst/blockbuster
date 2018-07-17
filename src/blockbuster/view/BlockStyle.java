/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.utils.Config;
import blockbuster.utils.SoundPlayer;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
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
	private Color colorGridLineBoard;
	private Color colorBlock1;
        private Color colorBlock2;
        private Color colorBlock3;
        private Color colorBlock4;
        private Color colorSpecialBlock;
        
        private Color colorBoardLine;  //For highlight
        
        private BufferedImage[] sprites;
        private Image logoImg;
        private Image levelImg;
        private BlockStyle(){
           //Costruttore
           loadImage();
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
//        private Color getColorOfBlock1() {
//                return this.colorBlock1;
//	}
//        private Color getColorOfBlock2() {
//                return this.colorBlock2;
//	}
//        private Color getColorOfBlock3() {
//                return this.colorBlock3;
//	}
//        private Color getColorOfBlock4() {
//                return this.colorBlock4;
//	}
        public Color getGridColor() {
            if(this.colorGridLineBoard== null){
//                this.colorGridLineBoard = Config.getInstance()
                  this.colorGridLineBoard = Color.LIGHT_GRAY;
            }
                return this.colorGridLineBoard;
	}
        public Color getBlockColor(int numBlock) { 
            
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
        public BufferedImage getBlockSprite(int numBlock){
            int i = Config.getInstance().getBlockStyle();
            return getBlockSprite(numBlock,i);
        }
        public BufferedImage getBlockSprite(int numBlock,int i){
            if(numBlock ==1)
                return this.sprites[0+i];
            if(numBlock ==2)
                return this.sprites[6+i];
            if(numBlock ==3)
               return this.sprites[12+i];
            if(numBlock ==4)
                return this.sprites[18+i];
            if(numBlock ==5)
                return this.sprites[24+i];
            if(numBlock ==6)
                return this.sprites[48+i];
            if(numBlock ==7)
                return this.sprites[30+i];
            if(numBlock ==8)
                return this.sprites[36+i];
            if(numBlock == 9)
                return this.sprites[42+i];
            else
            return null;
        }
        public String getFileLocation() throws URISyntaxException{
            File configFile = null;
            File byteCodeFileOfThisClass;
            byteCodeFileOfThisClass = new File(BlockStyle.class.getResource("BlockStyle.class").toURI());
            configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
        return configFile.toString();
    }
        public Image getButtonIcon(){
            return logoImg;
        }
        public Image getEndLevelImage(){
            levelImg.getScaledInstance(130, -1, Image.SCALE_SMOOTH);
            return levelImg;
        }
        private void loadImage() {
                BufferedImage bigImg= null;
                try{
                    bigImg = ImageIO.read(new File(getFileLocation()+"\\source\\BlockSprites.png"));
                    logoImg = ImageIO.read(new File(getFileLocation()+"\\source\\logo.png")); 
                    levelImg = ImageIO.read(new File(getFileLocation()+"\\source\\level.png"));  
                }catch (IOException | URISyntaxException ex) {
                     System.out.println("Source not found");
                }

                final int width =24;
                final int height = 24;
                final int rows = 9;
                final int cols = 6;
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
