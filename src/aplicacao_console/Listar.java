package aplicacao_console;

import fachada.Fachada;
import modelo.Usuario;
import modelo.Video;
import modelo.Visualizacao;

public class Listar {
	public Listar(){
		try {
			Fachada.inicializar();

			System.out.println("\nListagem de V�deos:");
			for(Video v : Fachada.listarVideos() )		
				System.out.println(v);

			System.out.println("\nListagem de Usu�rios:");
			for(Usuario u : Fachada.listarUsuarios())	
				System.out.println(u);
			
			System.out.println("\nListagem de Visualiza��es:");
			for(Visualizacao v : Fachada.listarVisualizacoes())	
				System.out.println(v);
			
			System.out.println("\nLocalizar Visualiza��o: 3");
			Visualizacao v = Fachada.localizarVisualizacao(3);
			System.out.println(v);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			Fachada.finalizar();
		}
	}


	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

