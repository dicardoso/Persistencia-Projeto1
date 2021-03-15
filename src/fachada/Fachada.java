package fachada;

import java.util.List;
import dao.DAO;
import dao.DAOAssunto;
import dao.DAOUsuario;
import dao.DAOVideo;
import dao.DAOVisualizacao;
import modelo.Assunto;
import modelo.Usuario;
import modelo.Video;
import modelo.Visualizacao;

public class Fachada {
	private static DAOAssunto daoAssunto = new DAOAssunto();  
	private static DAOUsuario daoUsuario = new DAOUsuario();  
	private static DAOVideo daoVideo = new DAOVideo();  
	private static DAOVisualizacao daoVisualizacao = new DAOVisualizacao();  

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}
	
	public static Video cadastrarVideo(String link, String nome, String palavra) throws  Exception{
		if ((link.equals(""))||(nome.equals(""))||(palavra.equals(""))) {
			DAO.rollback();
			throw new Exception("Preencha os campos vazios");
		}
		DAO.begin();	
		Video v = daoVideo.read(link);
		if(v != null) {
			DAO.rollback();
			throw new Exception("video ja cadastrado: " + link);
		}
		
		Assunto assunto = daoAssunto.read(palavra);
		if(assunto == null) {
			assunto = new Assunto(palavra);
			daoAssunto.create(assunto);		//assunto novo
		}
		
		v = new Video(link, nome, assunto);
		daoVideo.create(v);	
		DAO.commit();
		return v;
	}
	
	public static void adicionarAssunto(String link, String palavra) throws  Exception{
		DAO.begin();	
		Video v = daoVideo.read(link);
		if(v == null) {
			DAO.rollback();
			throw new Exception("video inexistente: " + link);
		}
		
		Assunto a = daoAssunto.read(palavra);
		if(a != null) {
			DAO.rollback();
			throw new Exception("assunto existente: " + palavra);		//assunto novo
		}
		
		a = new Assunto(palavra);
		daoAssunto.create(a);
		
		v.adicionar(a);
		a.adicionar(v);
		daoVideo.update(v);
		daoAssunto.update(a);	
		DAO.commit();
	}
	
	public static Visualizacao registrarVisualizacao(String link, String email, int nota) throws  Exception{
		DAO.begin();	
		
		if ((link.equals(""))||(email.equals(""))) {
			DAO.rollback();
			throw new Exception("Preencha os campos vazios");
		}
		if ((nota<1)||(nota>5)) {
			DAO.rollback();
			throw new Exception("Nota deve ser entre 1 e 5");
		}
		Video video = daoVideo.read(link);
		if(video == null) {
			DAO.rollback();
			throw new Exception("video inexistente: " + link);
		}
		
		Usuario us = daoUsuario.read(email);
		if(us == null) {
			us = new Usuario(email);
			daoUsuario.create(us);		//usuario novo
		}
		
		List<Visualizacao> list = daoVisualizacao.readAll();
		if(list == null) {
			DAO.rollback();
			throw new Exception("erro na lista de visualização: ");
		}
		
		
		
		int id = list.get(list.size()-1).getId(); // retorna o id da última visualização
		Visualizacao visu = new Visualizacao(id+1, nota, us, video);
		
		
		
		daoVisualizacao.create(visu);
		
		video.adicionar(visu);
		us.adicionar(visu);
		visu.adicionar(video);
		visu.adicionar(us);
		
		daoVideo.update(video);
		daoUsuario.update(us);	
		daoVisualizacao.update(visu);	
		DAO.commit();
		
		return visu;
	}
	
	public static Visualizacao localizarVisualizacao(int id) throws Exception{
		DAO.begin();
		Visualizacao v = daoVisualizacao.read(id);
		if (v == null) {
			DAO.rollback();	
			throw new Exception("Visualização inexistente: " + id);
		}
		return v;
	}
	
	public static void apagarVisualizacao(int id) throws Exception {
		DAO.begin();
		Visualizacao v = daoVisualizacao.read(id);
		if (v == null) {
			DAO.rollback();	
			throw new Exception("Visualização inexistente: " + id);
		}
		
		daoVisualizacao.delete(v);
		DAO.commit();
	}
	
	public static List<Video> listarVideos(){
		return daoVideo.readAll();
	}
	public static List<Usuario> listarUsuarios() {
		return daoUsuario.readAll();
	}
	public static List<Visualizacao> listarVisualizacoes() {
		return daoVisualizacao.readAll();
	}
	public static List<Assunto> listarAssuntos() {
		return daoAssunto.readAll();
	}
	
	public static List<Video> consultarVideosPorAssunto(String palavra){
		DAO.begin();
		
		List<Video> lVideo = daoVideo.consultarVideosPorAssunto(palavra);
		System.out.println(lVideo);
		return lVideo;		
	}
	
	public static List<Video> consultarVideosPorUsuario(String email){
		DAO.begin();
		List<Video> lVideo = daoVideo.consultarVideosPorUsuario(email);
		
		return lVideo;			
	} 
	
	public static List<Usuario> consultarUsuariosPorVideo(String link){
		DAO.begin();
		List<Usuario> lUsuario = daoUsuario.consultarUsuarioPorVideo(link);
		
		return lUsuario;	
	} 
}
