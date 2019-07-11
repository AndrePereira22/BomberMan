package Visao;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Menu extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel  lblBomber,imagem ;
	private JButton btnJogar,btnMultiPlayer,btnAjuda,btnSair;

	public Menu(int Largura, int Altura) {
		setPreferredSize(new Dimension(Largura, Altura));
		setLayout(null);
		
		
		lblBomber = new JLabel("Bomber-Lab");
		lblBomber.setForeground(Color.BLUE);
		lblBomber.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 43));
		lblBomber.setBounds(206, 0, 254, 60);
		add(lblBomber);
		
		btnJogar = new JButton("Jogar");
		btnJogar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnJogar.setBounds(47, 371, 89, 40);
		add(btnJogar);
		
		btnMultiPlayer = new JButton("Multi Player");
		btnMultiPlayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMultiPlayer.setBounds(175, 371, 143, 40);
		add(btnMultiPlayer);
		
		btnAjuda = new JButton("Ajuda");
		btnAjuda.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAjuda.setBounds(357, 371, 89, 40);
		add(btnAjuda);
		
		btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSair.setBounds(479, 371, 89, 40);
		add(btnSair);
		
		imagem = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("fundo.png")));
		imagem.setBounds(-10, -20, 600, 470);
		add(imagem);

	}

	public JButton getBtnJogar() {
		return btnJogar;
	}

	public JButton getBtnMultiPlayer() {
		return btnMultiPlayer;
	}

	public JButton getBtnAjuda() {
		return btnAjuda;
	}

	public JButton getBtnSair() {
		return btnSair;
	}
	
}
