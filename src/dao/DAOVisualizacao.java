package dao;

import java.util.List;
import com.db4o.query.Query;
import modelo.Visualizacao;

public class DAOVisualizacao extends DAO<Visualizacao>{

	public Visualizacao read (Object chave) {
		int id = (Integer) chave;
		
		Query q = manager.query();
		q.constrain(Visualizacao.class);
		q.descend("id").constrain(id);
		List<Visualizacao> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
}

