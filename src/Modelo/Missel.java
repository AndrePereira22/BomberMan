package Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;


import javax.swing.ImageIcon;

public class Missel extends Thread {

	
	
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;
	private static final int VELOCIDADE = 15;
	private String direcao;
	
	ArrayList<Missel> misseis = new ArrayList<Missel>();
	

	public Missel(int x, int y,String d) {

		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon(getClass().getResource("/bomba.gif"));
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		this.direcao=d;

		isVisivel = true;
		start();
	}
	public void run() {
		super.run();
		
		while(isVisivel) {
			mexer(direcao);
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public boolean collisao(ArrayList<Rectangle> tmp, int x,int y) {
		Rectangle personagem=new Rectangle(getX()+x, getY()+y, getLargura(), getAltura());
		for (Rectangle rectangle : tmp) {
			if(rectangle.intersects(personagem)){
				return true;
			}
		}
		return false;
	}

	public void mexer(String direcao){
			
		
		if(direcao.equals("cima"))	{
			y -= VELOCIDADE;
		}
		if(direcao.equals("baixo"))	{
			y += VELOCIDADE;
		}
		if(direcao.equals("direita"))	{
			x += VELOCIDADE;
		}
		if(direcao.equals("esquerda"))	{
			x -= VELOCIDADE;
		
		}
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	public boolean getVisivel() {
		return isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public  ArrayList<Missel> getMisseis() {
		return misseis;
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
	public  void add(Missel missel) {
		misseis.add(missel);
		
	}
	
	
	

}
