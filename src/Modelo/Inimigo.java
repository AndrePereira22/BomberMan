package Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;


import javax.swing.ImageIcon;

public class Inimigo extends Thread  {

	private Image imagem;
	private int x, y;
	private int largura, altura;
	private String direcao="esquerda";
	private ImageIcon referencia;
	private boolean isVisivel;

	private int[][] coordenadas = {{ 445,208},{ 555,308},{ 320,68},{170,68},{ 227,455}};

	ArrayList<Inimigo> inimigos = new ArrayList<Inimigo>();

	public Inimigo(int x, int y,int opcao) {

		this.x = x;
		this.y = y;

		if(opcao %2 ==0){
			referencia = new ImageIcon(getClass().getResource("/gif1.gif"));
		}else{
			referencia = new ImageIcon(getClass().getResource("/inimigo3.gif"));
		}
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);

		isVisivel = true;
		start();
	}

	public void run() {
		super.run();

		while(isVisivel) {
			try {
				mover();
				sleep(100);
			} catch (InterruptedException e) {}
		}

	}
	public boolean collision(ArrayList<Rectangle> tmp, int x,int y) {
		Rectangle personagem=new Rectangle(getX()+x, getY()+y, getLargura(), getAltura());
		for (Rectangle rectangle : tmp) {
			if(rectangle.intersects(personagem)){
				return true;
			}
		}
		return false;
	}
	public boolean collisaoBloco(ArrayList<Bloco> tmp, int x,int y) {
		Rectangle personagem=new Rectangle(getX()+x, getY()+y, getLargura(), getAltura());
		for (Bloco rectangle : tmp) {

			Rectangle formaBloco =rectangle.getBounds();

			if(formaBloco.intersects(personagem) && rectangle.isVisivel()){
				return true;
			}		
		}
		return false;
	}
	public void mover() {

		if(direcao.equals("esquerda")  ) {
			x-=4;
			if(collision(Fase.getRetangulosColisao(),0,0) ||collisaoBloco(Bloco.getBarreiras(),0,0)) {
				direcao="baixo";
				x+=4;
			}	
		}
		if(direcao.equals("baixo")  ) {
			y+=4;
			if(collision(Fase.getRetangulosColisao(),0,0) ||collisaoBloco(Bloco.getBarreiras(),0,0)) {
				direcao="direita";
				y-=4;
			}	
		}
		if(direcao.equals("direita")  ) {
			x+=4;
			if(collision(Fase.getRetangulosColisao(),0,0) ||collisaoBloco(Bloco.getBarreiras(),0,0)) {
				direcao="cima";
				x-=4;
			}	
		}
		if(direcao.equals("cima")  ) {
			y-=4;
			if(collision(Fase.getRetangulosColisao(),0,0) ||collisaoBloco(Bloco.getBarreiras(),0,0)) {
				direcao="esquerda";
				y+=4;
			}	
		}	
	}
	public void inicializaInimigos() {

		for (int i = 0; i < coordenadas.length; i++) {
			inimigos.add(new Inimigo(coordenadas[i][0], coordenadas[i][1],i));

		}
	}

	public int[][] getCoordenadas() {
		return coordenadas;
	}
	public ArrayList<Inimigo> getInimigos() {
		return inimigos;
	}
	public boolean isVisivel() {
		return isVisivel;	
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	public int getLargura() {
		return largura;
	}
	public int getAltura() {
		return altura;
	}

}
