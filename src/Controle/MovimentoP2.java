package Controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import Modelo.Sprite;

public class MovimentoP2 extends Thread implements KeyListener{

	Sprite bomber;
	int up, down, left, right;
	static HashMap<Integer, Boolean> keyPool;  

	public MovimentoP2(Sprite player1) {
		this.bomber = player1;	
		keyPool = new HashMap<Integer, Boolean>();
		start();

	}
	public void keyPressed(KeyEvent e) {

		keyPool.put(e.getKeyCode(), true);

	}
	public void keyReleased(KeyEvent e) {

		keyPool.remove(e.getKeyCode());

		if (e.getKeyCode()==KeyEvent.VK_W){
			bomber.aparencia=0;
		}
		if (e.getKeyCode()==KeyEvent.VK_A){
			bomber.aparencia=2;
		}
		if (e.getKeyCode()==KeyEvent.VK_S){
			bomber.aparencia=1;
		}
		if (e.getKeyCode()==KeyEvent.VK_D){
			bomber.aparencia=3;
		}

	}
	public void keyTyped(KeyEvent e) {}

	public void run() {
		boolean ativo = true;
		while(ativo) {
			mover1();

			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	public void mover1() {

		if (keyPool.get(KeyEvent.VK_D) != null)	{

			bomber.setX(bomber.getX()+4);
			switch (right) {
			case 0:
				bomber.aparencia=3;
				break;
			case 1:
				bomber.aparencia=7;
				break;
			case 2:
				bomber.aparencia=11;
				break;
			case 3:
				bomber.aparencia=15;
				break;
			case 4:
				bomber.aparencia=19;
				break;
			}
			if (right==4) right=0;
			else right++;

		}
		if (keyPool.get(KeyEvent.VK_W) != null){
			bomber.setY(bomber.getY()-4);

			switch (up) {
			case 0:
				bomber.aparencia=0;
				break;
			case 1:
				bomber.aparencia=4;
				break;
			case 2:
				bomber.aparencia=8;
				break;
			case 3:
				bomber.aparencia=12;
				break;
			case 4:
				bomber.aparencia=16;
				break;
			}
			if (up==4) up=0;
			else up++;

		}
		if (keyPool.get(KeyEvent.VK_S) != null){
			bomber.setY(bomber.getY()+4);

			switch (down) {
			case 0:
				bomber.aparencia=1;
				break;
			case 1:
				bomber.aparencia=5;
				break;
			case 2:
				bomber.aparencia=9;
				break;
			case 3:
				bomber.aparencia=13;
				break;
			case 4:
				bomber.aparencia=17;
				break;
			}

			if (down==4) down=0;
			else down++;	
		}

		if (keyPool.get(KeyEvent.VK_A) != null)	{

			bomber.setX(bomber.getX()-4);

			switch (left) {
			case 0:
				bomber.aparencia=2;
				break;
			case 1:
				bomber.aparencia=6;
				break;
			case 2:
				bomber.aparencia=10;
				break;
			case 3:
				bomber.aparencia=14;
				break;
			case 4:
				bomber.aparencia=18;
				break;
			}

			if (left==4) left=0;
			else left++;
		}
	}

}


