package br.com.senior.robo.enums;

public enum Directions {
	EAST("E"), WEST("W"), NORTH("N"), SOUTH("S");

	private final String value;

	Directions(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
