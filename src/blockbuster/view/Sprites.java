/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Andrea
 */
public class Sprites {
    public Sprites(){
        BufferedImage bigImg= null;
        try{
            bigImg = ImageIO.read(new File("C:/Users/Andrea/Desktop/pixel_block_sprites_by_crookedpi.png"));
        }catch(IOException ex){
             System.out.println("Could not find file");
        }

    final int width =17;
    final int height = 17;
    final int rows = 5;
    final int cols = 5;
    BufferedImage[] sprites = new BufferedImage[rows * cols];

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
    }//end costructor
}
