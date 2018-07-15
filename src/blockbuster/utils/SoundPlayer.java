/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbuster.utils;

/**
 *
 * @author Andrea
 */

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.*;



public class SoundPlayer implements Runnable 
{
    private static SoundPlayer instance = null;
    private String fileLocation = "\\source\\bcMusic.wav";
    private String gameoverLocation = "\\source\\gameover.wav";
    Clip clip;
    
    public SoundPlayer(String fileToPlay) {
            fileLocation ="\\source\\"+fileToPlay+".wav";
    }
    public SoundPlayer(){}
    
    public void play() 
    {  
        if(instance==null){
            instance = new SoundPlayer();
            Thread t = new Thread(this);
            t.start();
        }else
            this.start();
    }
    public void playGameOver() 
    {  
        Thread t = new Thread(this);
        t.start();
    }
    public void init(){
        instance=null;
    }
    public void stop(){
        if(clip != null)
            clip.close();
    }
    public void pause(){
        if(clip != null)
            clip.stop();
    }
    public void start(){
        if(clip != null && !clip.isRunning())
            clip.start();
    }
    public void run()
    {
        try{
                playSound(getFileLocation()+fileLocation);
        }
        catch (URISyntaxException ex){
            
        }catch(UnsupportedAudioFileException uaf){
            
        }catch(IOException e){
            
        }catch(LineUnavailableException lue){
            
        }
        
    }

    private void playSound(String fileName) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException 
    {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            new File(fileName));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl = 
            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
        if(gameoverLocation.equals(fileLocation)) 
            clip.start();
        else clip.loop(3);
    }
    public String getFileLocation() throws URISyntaxException{
            File configFile = null;
            File byteCodeFileOfThisClass;
            byteCodeFileOfThisClass = new File(SoundPlayer.class.getResource("SoundPlayer.class").toURI());
            configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();;
        return configFile.toString();
    }
}