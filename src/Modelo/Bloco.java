
package Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bloco {

	private Image imagem;
	private int x, y;
	private int largura, altura;
	private ImageIcon referencia;
	private boolean isVisivel;

	private int[][] coordenadas = {{ 94,68},{ 30,160},{ 30,355},{ 160,160},{ 220,68},{ 350,68},{ 480,68},{ 608,68},
			{94,160},{ 220,160},{ 350,160},{ 480,160},{ 608,160},{94,260},{ 220,260},{ 350,260},{ 480,260},{ 608,260},
			{94,355},{ 220,355},{287,355},{ 350,355},{ 480,355},{ 608,355},{94,455},{ 220,455},{ 350,455},{ 480,455},{ 608,455},{ 161,455}};

	static ArrayList<Bloco> barreiras = new ArrayList<Bloco>();
	

	public Bloco(int x, int y) {

		this.x = x;
		this.y = y;

		referencia = new ImageIcon(getClass().getResource("/parede.png"));

		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		isVisivel = true;
	}
	public void inicializaBarreiras() {
		for (int i = 0; i < coordenadas.length; i++) {
			barreiras.add(new Bloco(coordenadas[i][0], coordenadas[i][1]));
			
		}
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

	public boolean isVisivel() {
		return isVisivel;
	}

	public static ArrayList<Bloco> getBarreiras() {
		return barreiras;
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

	public int[][] getCoordenadas() {
		return coordenadas;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	

}
