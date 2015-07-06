package basico;

public class HelloWorldImpl implements HolaMundo {
	private Envoltorio envoltorio;
	
	public String saludar(String nombre) {
		return "Hello " + nombre;
	}

	public String saludar() {
		return envoltorio.toString();
	}

	public void setEnvoltorio(Envoltorio envoltorio) {
		this.envoltorio = envoltorio;
	}
}
