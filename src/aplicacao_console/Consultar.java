package aplicacao_console;

import fachada.Fachada;

public class Consultar {

	public Consultar(){
		Fachada.inicializar();
		
		System.out.println("\nVídeos por assunto: Política");
		System.out.println(Fachada.consultarVideosPorAssunto("politica"));
		
		System.out.println("\nVídeos por usuário: Zé");
		System.out.println(Fachada.consultarVideosPorUsuario("ze@gmail.com"));
		
		System.out.println("\nUsuários por vídeo: Java");
		System.out.println(Fachada.consultarUsuariosPorVideo("video.com/javaFilme"));

		Fachada.finalizar();
		System.out.println("fim do programa");
	}

	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}

