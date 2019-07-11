package Visao;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Modelo.Bloco;
import Modelo.Fase;
import Modelo.Inimigo;
import Modelo.Mapa;
import Modelo.Missel;
import Modelo.Sprite;

public class Camera {
	private Sprite personagem,personagem2;
	private Mapa mapa1,mapa2;
	private Missel missel;
	private Inimigo inimigo;
	private boolean visibilidadePlayer2=true,visibilidadePlayer1=true;
	private Bloco barreira;
	
	static int x=0;
	static int y=0;
	private BufferedImage tela;
	private Graphics g;

	public Camera(Sprite personagem,Sprite personagem2, Mapa mapa1,Mapa mapa2,
			Missel missel,Inimigo inimigo,Bloco barreira) {
		super();
		this.personagem = personagem;
		this.personagem2 = personagem2;
		this.missel=missel;
		this.inimigo=inimigo;
		this.setBarreira(barreira);
		this.mapa1 = mapa1;
		this.mapa2 = mapa2;

		tela=new BufferedImage(mapa1.getMapaLargura(), mapa1.getMapaAltura(), BufferedImage.TYPE_4BYTE_ABGR);
		g=tela.getGraphics();


		mapa1.montarMapa();
		mapa2.montarMapa();

	}
	public void renderinzar(){

		
		g.drawImage(mapa1.getMapa(), 0, 0, null);
		g.drawImage(mapa2.getMapa(), 0, 0, null);

		if(visibilidadePlayer1)g.drawImage(personagem.sprites[personagem.aparencia], personagem.getX(), personagem.getY(), null);
		if(visibilidadePlayer2)g.drawImage(personagem2.sprites[personagem2.aparencia], personagem2.getX(), personagem2.getY(), null);
		
		
		for (int i = 0; i < missel.getMisseis().size(); i++) {

			Missel m = (Missel)  missel.getMisseis().get(i);

			if(m.getVisivel()) {
				g.drawImage(m.getImagem(), m.getX(), m.getY(), null);

			}
		}
		for (int i = 0; i < inimigo.getInimigos().size(); i++) {

			Inimigo in = inimigo.getInimigos().get(i);
			if(in.isVisivel()) {
				g.drawImage(in.getImagem(), in.getX(), in.getY(), null);
			}
		}
		for (int i = 0; i < Bloco.getBarreiras().size(); i++) {

			Bloco b =  Bloco.getBarreiras().get(i);
				if(b.isVisivel()) {
					g.drawImage(b.getImagem(), b.getX(), b.getY(), null);
				}
			}
		
	}

	public void draw(Graphics g){
		if(personagem.getX()>Fase.LARGURA/2)
			if(personagem.getX()<(mapa1.getMapaLargura()-Fase.LARGURA/2))
				x=personagem.getX()-(Fase.LARGURA/2);
		if(personagem.getY()>Fase.ALTURA/2)
			if(personagem.getY()<(mapa1.getMapaAltura()-Fase.ALTURA/2))
				y=personagem.getY()-Fase.ALTURA/2;

		g.drawImage(tela, -x, -y, null);
	}
	public boolean isVisibilidadePlayer2() {
		return visibilidadePlayer2;
	}
	public void setVisibilidadePlayer2(boolean visibilidadePlayer2) {
		this.visibilidadePlayer2 = visibilidadePlayer2;
	}
	public boolean isVisibilidadePlayer1() {
		return visibilidadePlayer1;
	}
	public void setVisibilidadePlayer1(boolean visibilidadePlayer1) {
		this.visibilidadePlayer1 = visibilidadePlayer1;
	}
	public Bloco getBarreira() {
		return barreira;
	}
	public void setBarreira(Bloco barreira) {
		this.barreira = barreira;
	}
	
}
