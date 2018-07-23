/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import blockbuster.utils.Config;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import javax.imageio.ImageIO;
/**
 *
 * @author Andrea
 */
public class imgSetting {
        //---------------------------------------------------------------
	// STATIC ATTRIBUTE
	//---------------------------------------------------------------
	private static imgSetting instance = null;

	//---------------------------------------------------------------
	// INSTANCE ATTRIBUTE
	//---------------------------------------------------------------
	private Color colorGridLineBoard;
        private BufferedImage[] sprites;
        private Image logoImg;
        private Image levelImg;
        
        private imgSetting(){
             loadImage();  
        }

        public Color getGridColor() {
            if(this.colorGridLineBoard== null)
                  this.colorGridLineBoard = Color.LIGHT_GRAY;
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
            byteCodeFileOfThisClass = new File(imgSetting.class.getResource("imgSetting.class").toURI());
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

        public String getImagePath(String name){ 
            try {
                return getFileLocation()+"\\source\\"+name;
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }
            return null;
        }


        private void loadImage() {
                BufferedImage bigImg= null;
                try{
                    bigImg = ImageIO.read(new File(getFileLocation()+"\\source\\BlockSprites.png"));
                    logoImg = ImageIO.read(new File(getFileLocation()+"\\source\\logo.png")); 
                    levelImg = ImageIO.read(new File(getFileLocation()+"\\source\\level.png"));
                }catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
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
	public static imgSetting getInstance() {
		if (instance == null)
			instance = new imgSetting();
		return instance;
	}
        
}//end class
