package dao;

import java.util.List;
import com.db4o.query.Query;

import modelo.Video;

public class DAOVideo extends DAO<Video>{

	public Video read (Object chave) {
		String link = (String) chave;
		
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("link").constrain(link);
		List<Video> resultados = q.execute();
		
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	public List<Video> consultarVideosPorAssunto(Object chave) {
		String palavra = (String) chave;
		
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("assuntos").descend("palavra").constrain(palavra);
		List<Video> result = q.execute(); 
		return result;
	}

	public List<Video> consultarVideosPorUsuario(Object chave) {
		String email = (String) chave;
		
		Query q = manager.query();
		q.constrain(Video.class);
		q.descend("visualizacoes").descend("usuario").descend("email").constrain(email);
		List<Video> result = q.execute(); 
		return result;
	}
}

