
import java.io.*;
import javax.sound.sampled.*;

public class PlayAudio
{
  private boolean m_stopped;

  public static void main(String args[])
  {
    if (args.length != 1)
    {
      System.out.println("Please specify an audio file.");
      AudioFileFormat.Type[] formats = AudioSystem.getAudioFileTypes();
      for (AudioFileFormat.Type t : formats)
      {
        System.out.println(t);
      }
    }
    else
    {
      PlayAudio player = new PlayAudio();
      player.play(args[0]);
    }
  }

  public void play(String song)
  {
    m_stopped = false;
    File audioFile = new File(song);

    try
    {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);
        Clip audioClip = (Clip) AudioSystem.getLine(info);

        audioClip.addLineListener(new AudioListener());
        audioClip.open(audioStream);
        audioClip.start();

        while (!m_stopped)
        {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
        audioClip.close();
    }
    catch (UnsupportedAudioFileException ex)
    {
        System.out.println("The specified audio file is not supported.");
    }
    catch (LineUnavailableException ex)
    {
        System.out.println("Audio line for playing back is unavailable.");
    }
    catch (IOException ex)
    {
        System.out.println("Error playing the audio file.");
    }
  }

  private class AudioListener implements LineListener
  {
    @Override
    public void update(LineEvent event)
    {
      LineEvent.Type type = event.getType();

      if (type == LineEvent.Type.START)
      {
        System.out.println("Playback started.");
      }
      else if (type == LineEvent.Type.STOP)
      {
        System.out.println("Playback completed.");
        m_stopped = true;
      }
}
  }
}
