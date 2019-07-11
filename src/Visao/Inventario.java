package Visao;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Inventario extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel imagem,vidaBomber1,vidaBomber2;

	public Inventario(int largura,int altura) {

		setPreferredSize(new Dimension(largura, altura));
		setLayout(null);

		imagem = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("inventario1.png")));
		vidaBomber1=new JLabel(new ImageIcon(getClass().getClassLoader().getResource("barra.png")));
		vidaBomber2=new JLabel(new ImageIcon(getClass().getClassLoader().getResource("barra.png")));

		imagem.setBounds(0,0,116,550);
		vidaBomber1.setBounds(6,99,104,18);
		vidaBomber2.setBounds(6,346,104,18);

		add(vidaBomber2);
		add(vidaBomber1);
		add(imagem);


		setVisible(false);
	}

	public void FecharVisible() { setVisible(false);	}

	public void AbriVisible() { setVisible(true);	}

	public JLabel getVidaBomber1() {
		return vidaBomber1;
	}
	public JLabel getVidaBomber2() {
		return vidaBomber2;
	}

	public JLabel getImagem() {
		return imagem;
	}
	public void setImagem(String url) {
		this.imagem.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
	}
}