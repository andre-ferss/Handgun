package game;

import java.applet.AudioClip;

public class SoundGuns {
	public void AlienPistol() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/alienpistol.wav"));
		Sound.play();
		//Sound.stop();
	}

	public void Blacktail() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/blacktail.wav"));
		Sound.play();
	}

	public void DesertEagle() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/deserteagle.wav"));
		Sound.play();
	}

	public void AK47() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/ak47.wav"));
		Sound.play();
	}

	public void M4() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/m4.wav"));
		Sound.play();
	}

	public void Shotgun() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/shotgun.wav"));
		Sound.play();
	}
	public void mp5() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/mp5.wav"));
		Sound.play();
	}
	public void awp() {
		AudioClip Sound;
		Sound = java.applet.Applet.newAudioClip(getClass().getResource("/game/sons/awp.wav"));
		Sound.play();
	}
}
