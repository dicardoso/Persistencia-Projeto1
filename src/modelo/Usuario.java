package modelo;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String email;
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	public Usuario(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	public void adicionar(Visualizacao vis) {
		visualizacoes.add(vis);
	}
	
	public List<Visualizacao> getListaVisualizacoes(){
		return visualizacoes;
	}
	
	public String getVisualizacoes(){
		String texto = "";
		for(Visualizacao v: visualizacoes) {
			if (v != null)
				texto += v.getId() + (visualizacoes.indexOf(v) == visualizacoes.size()-1 ?"" :", ");
		}
		return texto;
	}

	@Override
	public String toString() {
		String texto =  "Usuario [email=" + email + "]";
		
		texto+="\n visualizacoes=";
		for(Visualizacao vis : visualizacoes) {
			texto += vis;
		}
		return texto;
	}

	
	
	
}
