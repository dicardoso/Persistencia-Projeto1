package modelo;

public class Visualizacao {
	private int id;
	private int nota;
	private Usuario usuario;
	private Video video;
	
	public Visualizacao(int id, int nota, Usuario usuario, Video video) {
		this.id = id;
		this.nota = nota;
		this.usuario = usuario;
		this.video = video;
	}
	
	public void adicionar(Video video) {
		this.video = video;
	}
	
	public void adicionar(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public int getNota() {
		return nota;
	}
	
	public Video getVideo() {
		return video;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Visualizacao [id=" + id + 
				", nota=" + nota +
				"\n usuario=" + usuario.getEmail() + ", video=" + video.getNome() + "]\n";
	}

	

	
	
	

}
