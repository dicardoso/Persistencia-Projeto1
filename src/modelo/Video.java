package modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Video {
	private String link;
	private String nome;
	private double classificacao = 0;
	private LocalDateTime datahora;

	private List<Assunto> assuntos = new ArrayList<>();
	private List<Visualizacao> visualizacoes = new ArrayList<>();

	public Video(String link, String nome, Assunto assunto) {
		this.link = link;
		this.nome = nome;
		this.assuntos.add(assunto);
		this.datahora = LocalDateTime.now();
		this.classificacao = 0;
	}
		
	public String getNome() {
		return nome;
	}
	
	public String getLink() {
		return link;
	}
	
	public List<Visualizacao> getVisualizacoes() {
		return visualizacoes;
	}
	
	public double getClassificacao() {
		double total = 0;
		for (Visualizacao v: this.visualizacoes) {
			if(v != null)
				total += v.getNota();
		}
		if (total > 0)
			return total/visualizacoes.size();
		return 0;
	}
	
	public List<Assunto> getAssuntos() {
		return assuntos;
	}
	
	public String getPalavras() {
		String texto = "";
		for(Assunto a: assuntos) {
			texto += a.getPalavra() + (assuntos.indexOf(a) == assuntos.size()-1 ?"" :", ");
		}
		return texto;
	}

	public LocalDateTime getDatahora() {
		return datahora;
	}
	
	public void adicionar(Assunto a) {
		assuntos.add(a);
	}
	public void adicionar(Visualizacao vis) {
		visualizacoes.add(vis);
	}
	

	@Override
	public String toString() {
		String texto = "Video [" + (link != null ? "link=" + link + ", " : "") + (nome != null ? "nome=" + nome + ", " : "")
				+ "media=" + classificacao ;
		
		texto+=", assuntos= ";
		for(Assunto a : assuntos) {
			texto += a.getPalavra() + " ";
		}
		texto+="\n visualizacoes=";
		for(Visualizacao vis : visualizacoes) {
			texto += vis;
		}
		return texto;
	}
}
