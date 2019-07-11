package Modelo;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Audio {

	AudioClip bonus,morte,batalha,explosao,grito1,grito2,risada;

	public Audio() {
		URL url1 = getClass().getResource("/Bonus.mid");
		URL url2 = getClass().getResource("/Die.mid");
		URL url3 = getClass().getResource("/Battle.mid");
		URL url4 = getClass().getResource("/Explosion.mid");
		URL url5 = getClass().getResource("/grito1.wav");
		URL url6 = getClass().getResource("/grito1.wav");
		URL url7 = getClass().getResource("/risada.wav");

		bonus = Applet.newAudioClip(url1);
		morte = Applet.newAudioClip(url2);
		batalha = Applet.newAudioClip(url3);
		explosao = Applet.newAudioClip(url4);
		grito1= Applet.newAudioClip(url5);
		grito2 = Applet.newAudioClip(url6);
		risada = Applet.newAudioClip(url7);
		
	}
	public AudioClip getBonus() {
		return bonus;
	}
	public AudioClip getMorte() {
		return morte;
	}
	public AudioClip getBatalha() {
		return batalha;
	}
	public AudioClip getExplosao() {
		return explosao;
	}
	public AudioClip getGrito1() {
		return grito1;
	}
	public AudioClip getGrito2() {
		return grito2;
	}
	public AudioClip getRisada() {
		return risada;
	}
	
	
}
