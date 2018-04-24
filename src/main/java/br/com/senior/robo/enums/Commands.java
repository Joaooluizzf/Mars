package br.com.senior.robo.enums;

public enum Commands {
	RIGHT("R"), LEFT("L"), MOVE("M");

	private final String value;

	Commands(String command) {
		value = command;
	}

	public static Commands find(String value) {
		for (Commands command : values()) {
			if (command.value.equals(value)) {
				return command;
			}
		}
		return null;
	}

}