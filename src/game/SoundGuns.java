package game;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundGuns {
	
	private AudioInputStream audioStream;
	private Clip clip;
	
	public void AlienPistol() {
		
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/alienpistol.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void Blacktail() {
		
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/blacktail.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void DesertEagle() {
		
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/deserteagle.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void AK47() {

		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/ak47.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void M4() {
	
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/m4.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void Shotgun() {
		
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/shotgun.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void mp5() {
		
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/mp5.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void awp() {
		
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/awp.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void maintheme() {
		
		try {
			
			audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/game/sons/AWOLNATION-maintheme.wav"));
			
			clip = AudioSystem.getClip();
			
			clip.open(audioStream);
			
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Clip getClip() {
		
		return clip;
		
	}
}
