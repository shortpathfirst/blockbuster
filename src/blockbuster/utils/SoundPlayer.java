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
    private String fileLocation;
    private final String gameoverLocation = "\\source\\gameover.wav";
    private final String backgroundLocation ="\\source\\bcMusic.wav";
    private Clip clip;
    
    public SoundPlayer(String fileToPlay) {
            fileLocation ="\\source\\"+fileToPlay+".wav";
            instance=null;                                                      //initialized 1 time per object
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

        if (Config.getInstance().isVolumeOn()) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = (Config.getInstance().getSoundVolume()/100.0f);
            if (volume < 0f || volume > 1f)
                 throw new IllegalArgumentException("Volume not valid: " + volume);
            control.setValue(20f * (float) Math.log10(volume));
            if(gameoverLocation.equals(fileLocation)) 
            clip.start();
            else clip.loop(-1);
        }
        
    }
    public String getFileLocation() throws URISyntaxException{
            File configFile = null;
            File byteCodeFileOfThisClass;
            byteCodeFileOfThisClass = new File(SoundPlayer.class.getResource("SoundPlayer.class").toURI());
            configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();;
        return configFile.toString();
    }
}
