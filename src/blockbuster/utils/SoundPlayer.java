package blockbuster.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.*;



public class SoundPlayer implements Runnable
{
    private static SoundPlayer instance = null;
    private String relFileLocation;
    private final String gameoverLocation = "\\source\\gameover.wav";
    private final String backgroundLocation ="\\source\\bcMusic.wav";
    private Clip clip;

    public SoundPlayer(String fileToPlay) {
        
            relFileLocation ="\\source\\"+fileToPlay+".wav";
            if (System.getProperty("os.name").startsWith("Linux")) 
                       relFileLocation = "/source/"+fileToPlay+".wav";
    }

    public void run()
    {
        try{
                playSound(Config.getInstance().getFilePath(relFileLocation));
        }
        catch (URISyntaxException | UnsupportedAudioFileException | IOException | LineUnavailableException ex){
              ex.printStackTrace();
        }
    }

    public void play(){
        Thread t = new Thread(this);
        t.start();
    }
    public void pause(){
        if(clip != null)
            clip.stop();
    }
    public void start(){
        if(clip != null && !clip.isRunning())
            if (Config.getInstance().isVolumeOn()) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = -1;

            if(backgroundLocation.equals(relFileLocation))
                 volume = (Config.getInstance().getSoundVolume()/100.0f);
            else
                volume = (Config.getInstance().getEffectsVolume()/100.0f);

            if (volume < 0f || volume > 1f)
                 throw new IllegalArgumentException("Volume not valid: " + volume);
            control.setValue(20f * (float) Math.log10(volume));
            if(backgroundLocation.equals(relFileLocation))
                clip.loop(-1);
            else  clip.start();
        }
    }

    private void playSound(String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException
    {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
            new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        start();
    }

}
