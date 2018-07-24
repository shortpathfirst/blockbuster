package blockbuster.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.*;



public class SoundPlayer implements Runnable 
{
    private final static boolean IS_DIST_VERSION = true;// this flag must be set to true when compiling for the dist version
    
    private static SoundPlayer instance = null;
    private String relFileLocation;
    private final String gameoverLocation = "\\source\\gameover.wav";
    private final String backgroundLocation ="\\source\\bcMusic.wav";
    private Clip clip;
    
    public SoundPlayer(String fileToPlay) {
            relFileLocation ="\\source\\"+fileToPlay+".wav";
    }
    
    public void run()
    {
        try{
                playSound(getFileLocation()+relFileLocation);
        }
        catch (URISyntaxException | UnsupportedAudioFileException | IOException | LineUnavailableException ex){
              ex.printStackTrace();
        }
    }
    
    public void play() 
    {  
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
    
    
    
    private String getFileLocation() throws URISyntaxException{
        if (IS_DIST_VERSION)
                return getHomeFolderForDistVersion();
        else{
                File configFile = null;
                File byteCodeFileOfThisClass;
                byteCodeFileOfThisClass = new File(SoundPlayer.class.getResource("SoundPlayer.class").toURI());
                configFile = byteCodeFileOfThisClass.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();;
                return configFile.toString();
        }
    }
    	private String getHomeFolderForDistVersion() throws URISyntaxException {
		String homeDir = null;
		String jarPath = SoundPlayer.class.getResource("SoundPlayer.class").toURI().toString();
		int indexOfExclamationMark = jarPath.indexOf("!");
		String prefix = "jar:file:/"; // this is the prefix for Windows OS platform
		if (System.getProperty("os.name").startsWith("Linux")) {
			prefix = "jar:file:";
		}
		homeDir = jarPath.substring(prefix.length(), indexOfExclamationMark);
		int lastIndexOfSlash = homeDir.lastIndexOf("/");
		homeDir = homeDir.substring(0, lastIndexOfSlash);
		return homeDir;
	}
        
}
