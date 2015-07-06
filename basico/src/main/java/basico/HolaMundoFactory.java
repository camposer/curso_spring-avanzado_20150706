package basico;

public abstract class HolaMundoFactory {
	private static HolaMundo INSTANCE = new HelloWorldImpl(); 
	public static HolaMundo createHolaMundo() {
		return INSTANCE;
	}
}
