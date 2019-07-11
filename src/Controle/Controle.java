package Controle;


import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLabel;
import Modelo.Audio;
import Modelo.Bloco;
import Modelo.Fase;
import Modelo.Inimigo;
import Modelo.Missel;
import Modelo.Sprite;
import Visao.Camera;
import Visao.Inventario;
import Visao.Janela;
import Visao.Menu;

public class Controle implements Runnable,KeyListener,ActionListener{

	Janela janela;
	Fase  fase;
	Menu menu;
	Sprite bomber1,bomber2;
	Audio audio;
	Inventario inventario;
	MovimentoP1 eventos1;
	MovimentoP2 eventos2;
	Missel missel;
	Inimigo inimigo;
	Bloco barreira;
	Camera camera;
	static HashMap<Integer, Boolean> keyPool;  
	boolean ativo,gameOver;;
	String direcao="";
	int contador1=0,contador2=0;

	public Controle(Janela janela,Fase fase,Inventario inventario, Menu menu) {

		this.janela = janela;
		this.fase = fase;
		this.inventario=inventario;
		this.menu=menu;

		bomber1= fase.getBomber();
		bomber2= fase.getBomber2();

		eventos1 = new MovimentoP1(bomber1);
		eventos2 = new MovimentoP2(bomber2);

		camera= fase.getCamera();
		missel= fase.getMissel();
		inimigo = fase.getInimigo();
		barreira = fase.getBarreira();

		keyPool = new HashMap<Integer, Boolean>();

		audio= new Audio();
		gameOver=false;

		janela.add(fase);
		janela.add(inventario);
		janela.add(menu);

		controleEventos();

		janela.setVisible(true);
	}

	public void inicializar() {

		janela.setSize(904,585);
		inventario.setVisible(true);
		fase.setVisible(true);
		menu.setVisible(false);
		audio.getBatalha().loop();
		inimigo.inicializaInimigos();
		barreira.inicializaBarreiras();

	}

	public void controleEventos() {

		fase.addKeyListener(eventos1);
		fase.addKeyListener(eventos2);
		fase.addKeyListener(this);
		menu.getBtnMultiPlayer().addActionListener(this);
		menu.getBtnJogar().addActionListener(this);
		menu.getBtnSair().addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==menu.getBtnMultiPlayer()) {

			inventario.setImagem("inventario2.png");
			inventario.getVidaBomber2().setVisible(true);
			camera.setVisibilidadePlayer2(true);

			inicializar();
		}
		if(e.getSource()==menu.getBtnJogar()) {

			inventario.setImagem("inventario1.png");
			inventario.getVidaBomber2().setVisible(false);
			camera.setVisibilidadePlayer2(false);

			inicializar();			
		}
		if(e.getSource()==menu.getBtnSair()) {
			System.exit(0);			
		}
	}

	public void run() {
		ativo = true;
		while(ativo) {
			Atualizar();
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	private void Atualizar() {

		runControleDoJogo();

	}
	private void runControleDoJogo() {

		if(camera.isVisibilidadePlayer2()) {
			checarColisoesNoInimigo2(bomber2,inventario.getVidaBomber2());
			checarColisoesBombaNoPersonagem(bomber2,inventario.getVidaBomber2());
		}
		if(camera.isVisibilidadePlayer1()) {

			checarColisoesNoInimigo1(bomber1,inventario.getVidaBomber1());
			checarColisoesBombaNoPersonagem(bomber1,inventario.getVidaBomber1());
		}
		
		colisoesMisselNoBloco();
		bomber1.colisao(Fase.getRetangulosColisao(), 0, 0);
		bomber1.colisaoBloco(Bloco.getBarreiras(), 0, 0);

		checarColisoesBombaNoInimigo();
		checarColisoesBombaNoCenario();


		if(inventario.getVidaBomber1().getWidth() < 1 && camera.isVisibilidadePlayer1()) {
			audio.getRisada().play();
			camera.setVisibilidadePlayer1(false);
		}
		if(inventario.getVidaBomber2().getWidth() < 1 && camera.isVisibilidadePlayer2()) {
			audio.getRisada().play();
			camera.setVisibilidadePlayer2(false);

		}
		if(inventario.getVidaBomber1().getWidth() < 1 && inventario.getVidaBomber2().getWidth() < 1) {

			if(!gameOver) {
				gameOver=true;
			}
		}
	}

	public void atira(Sprite bomber){

		if(missel.getMisseis().size() < 20) {

			if(bomber.aparencia==0 || bomber.aparencia==4 || bomber.aparencia==8 || bomber.aparencia==12 || bomber.aparencia==16)	{
				missel.add(new Missel(bomber.getX()+3,bomber.getY()-17,"cima"));	

			}else if(bomber.aparencia==2 || bomber.aparencia==6 || bomber.aparencia==10 || bomber.aparencia==14 || bomber.aparencia==18)	{
				missel.add(new Missel(bomber.getX()-17,bomber.getY()+20,"esquerda"));

			}else if(bomber.aparencia==3 || bomber.aparencia==7 || bomber.aparencia==11 || bomber.aparencia==15 || bomber.aparencia==19) {
				missel.add(new Missel(bomber.getX()+27,bomber.getY()+23,"direita"));

			}else if(bomber.aparencia==1 || bomber.aparencia==4 || bomber.aparencia==9 || bomber.aparencia==13 || bomber.aparencia==17) {

				missel.add(new Missel(bomber.getX()+5,bomber.getY()+52,"baixo"));

			}		
		}
	}
	public void keyPressed(KeyEvent e) {

		keyPool.put(e.getKeyCode(), true);

		if(e.getKeyCode()==KeyEvent.VK_SPACE && camera.isVisibilidadePlayer1()) {	

			atira(bomber1);	
		}
		if(e.getKeyCode()==KeyEvent.VK_X && camera.isVisibilidadePlayer2()) {	
			atira(bomber2);
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if(e.getKeyCode()==KeyEvent.VK_ALT) {
			reiniciarValores();
			menu();
		}
	}
	public void keyReleased(KeyEvent e) {
		keyPool.remove(e.getKeyCode());

	}

	public void keyTyped(KeyEvent arg0) {}	 

	public void colisoesMisselNoBloco() {

		Rectangle formaMissel;
		Rectangle formaBloco;

		List<Missel> misseis = missel.getMisseis();

		for (int i = 0; i < missel.getMisseis().size(); i++) {

			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < Bloco.getBarreiras().size(); j++) {

				Bloco tempBarreira = Bloco.getBarreiras().get(j);
				formaBloco = tempBarreira.getBounds();

				if (formaMissel.intersects(formaBloco) && tempBarreira.isVisivel()) {
					audio.getExplosao().play();
					tempBarreira.setVisivel(false);
					tempMissel.setVisivel(false);
					missel.getMisseis().remove(i);
					
				}
			}
		}
	}
	public void checarColisoesNoInimigo1(Sprite player,JLabel vida) {


		Rectangle formaBomber = player.getBounds();
		Rectangle formaInimigo;

		for (int i = 0; i < inimigo.getInimigos().size(); i++) {

			Inimigo tempInimigo = inimigo.getInimigos().get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaBomber.intersects(formaInimigo)) {

				contador1++;
				
				if(contador1>4) {
					
					audio.getMorte().play();
					vida.setBounds(vida.getX(),vida.getY(),vida.getWidth()-1,vida.getHeight());

					contador1=0;
				}
			}
		}	
	}
	public void checarColisoesNoInimigo2(Sprite player,JLabel vida) {


		Rectangle formaBomber = player.getBounds();
		Rectangle formaInimigo;

		for (int i = 0; i < inimigo.getInimigos().size(); i++) {

			Inimigo tempInimigo = inimigo.getInimigos().get(i);
			formaInimigo = tempInimigo.getBounds();

			if (formaBomber.intersects(formaInimigo)) {

				contador2++;
				if(contador2>4) {
					
					audio.getMorte().play();
					vida.setBounds(vida.getX(),vida.getY(),vida.getWidth()-1,vida.getHeight());

					contador2=0;
				}
			}
		}	
	}

	public void checarColisoesBombaNoInimigo() {

		Rectangle formaInimigo;
		Rectangle formaMissel;

		List<Missel> misseis = missel.getMisseis();

		for (int i = 0; i < missel.getMisseis().size(); i++) {

			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigo.getInimigos().size(); j++) {

				Inimigo tempInimigo = inimigo.getInimigos().get(j);
				formaInimigo = tempInimigo.getBounds();

				if (formaMissel.intersects(formaInimigo)) {
					audio.getExplosao().play();
					tempInimigo.setVisivel(false);
					inimigo.getInimigos().remove(j);
					tempMissel.setVisivel(false);
					missel.getMisseis().remove(i);
				}
			}
		}
	}
	public void checarColisoesBombaNoPersonagem(Sprite player,JLabel vida) {

		Rectangle formaPlayer= player.getBounds();
		Rectangle formaMissel;

		List<Missel> misseis = missel.getMisseis();

		for (int i = 0; i < missel.getMisseis().size(); i++) {

			Missel tempMissel = misseis.get(i);

			formaMissel = tempMissel.getBounds();

			if (formaMissel.intersects(formaPlayer)) {
				audio.getGrito1().play();
				tempMissel.setVisivel(false);
				missel.getMisseis().remove(i);
				vida.setBounds(vida.getX(),vida.getY(),vida.getWidth()-5,vida.getHeight());
			}
			
		}
	
}
public void checarColisoesBombaNoCenario() {

	for (int i = 0; i < missel.getMisseis().size(); i++) {

		if(missel.getMisseis().get(i).collisao(Fase.getRetangulosColisaoBomba(), 0, 0)) {

			missel.getMisseis().get(i).setVisivel(false);
			missel.getMisseis().remove(i);
			audio.getExplosao().play();
		}
	}
}
public void menu() {	

	fase.setVisible(false);
	inventario.setVisible(false);
	fase.setLocation(1000, 0);
	janela.setSize(600,470);
	menu.setVisible(true);
	menu.requestFocus();
	camera.setVisibilidadePlayer2(true);
	camera.setVisibilidadePlayer1(true);
	inventario.getVidaBomber1().setBounds(6,99,104,18);
	inventario.getVidaBomber2().setBounds(6,346,104,18);
	audio.getBatalha().stop();

}
public void reiniciarValores() {

	contador1=0;contador2=0;
	bomber1.setX(55);bomber1.setY(74); bomber2.setX(55); bomber2.setY(458);
	inimigo.getInimigos().clear();
	Bloco.getBarreiras().clear();
	missel.getMisseis().clear();
}	
}
