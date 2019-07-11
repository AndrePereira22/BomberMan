package Modelo;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import Visao.Camera;

public class Fase extends Jogo {

	private static final long serialVersionUID = -2316510197558395731L;

	private Mapa mapa1,mapa2,mapaColisaoBomber;
	private static ArrayList<Rectangle> retangulosColisao;
	private static ArrayList<Rectangle> retangulosColisaoBomba;
	private Camera camera;
	private Sprite bomber1,bomber2;
	private Missel missel;
	private Inimigo inimigo;
	private Bloco barreira;
	
	public Fase() {
		super();
		setFocusable(true);
		setDoubleBuffered(true);
		
		Load();

	}
	public void Load() {

		mapa1= new Mapa("tileset.png","cenario.txt");
		mapa2= new Mapa("tileset.png","cenario2.txt");
		mapaColisaoBomber=new Mapa("tileset.png","cenario2.txt");
		
		mapa1.montarMapa();
		mapa2.montarMapa();
		retangulosColisao=mapaColisaoBomber.montarColi();
		retangulosColisaoBomba=mapaColisaoBomber.montarColi();
		
		missel= new Missel(16,16,"");
		inimigo= new Inimigo(10,10,0);
		barreira= new Bloco(10,10);
	
		try {	
				bomber1 = new Sprite("sprite1.png",1,5,4,55,74);
				bomber2 = new Sprite("sprite2.png",1,5,4,695,446);
		} catch (IOException e) {

			e.printStackTrace();
		}
		camera= new Camera(bomber1,bomber2,mapa1,mapa2,missel,inimigo,barreira);
		setVisible(false);
	}
	public void Update() {
		mapa1.montarMapa();
		camera.renderinzar();
		 	
	}
	public void Render() {
		camera.draw(g);		
	}
	public Sprite getBomber() {
		return bomber1;
	}
	public Sprite getBomber2() {
		return bomber2;
	}
	public static ArrayList<Rectangle> getRetangulosColisao() {
		return retangulosColisao;
	}
	public Camera getCamera() {
		return camera;
	}
	public static ArrayList<Rectangle> getRetangulosColisaoBomba() {
		return retangulosColisaoBomba;
	}
	public Missel getMissel() {
		return missel;
	}
	public Inimigo getInimigo() {
		return inimigo;
	}
	public Bloco getBarreira() {
		return barreira;
	}
	
		
	}



