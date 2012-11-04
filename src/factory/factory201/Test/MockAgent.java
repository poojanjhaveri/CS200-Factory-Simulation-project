package factory.factory201.Test;

public class MockAgent {
	private String name;

	public MockAgent(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return this.getClass().getName() + ": " + name;
	}

}
