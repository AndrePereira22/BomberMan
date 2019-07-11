package App;

import Controle.Controle;
import Modelo.Fase;
import Visao.Inventario;
import Visao.Janela;
import Visao.Menu;

public class App {

	public static void main(String[] args) {
		 
		Janela janela = new Janela(600,490 );
		Fase  fase =  new Fase();
		Inventario inventario = new Inventario(116,550);
		Menu menu = new Menu(600,450);
		
		Controle control =  new Controle(janela, fase,inventario,menu);
		control.run();
		
		///// pronto
		
	}

}
