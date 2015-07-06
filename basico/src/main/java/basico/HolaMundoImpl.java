package basico;

public class HolaMundoImpl implements HolaMundo {
	private Envoltorio envoltorio;
	
	public String saludar(String nombre) {
		return "Hola " + nombre;
	}

	public String saludar() {
		return envoltorio.toString();
	}

	public void setEnvoltorio(Envoltorio envoltorio) {
		this.envoltorio = envoltorio;
	}
}
