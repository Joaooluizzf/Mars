package br.com.senior.robo;

import br.com.senior.robo.enums.Commands;
import br.com.senior.robo.enums.Directions;
import br.com.senior.robo.exceptions.InvalidRoboStateException;

public class RoboInMemory {

	private int positionX;
	private int positionY;
	private Directions direction;

	public RoboInMemory() {
		direction = Directions.NORTH;
	}

	@Override
	public String toString() {
		return "(" + positionX + ", " + positionY + ", " + direction.getValue() + ")";
	}

	public void execute(String command) throws InvalidRoboStateException {
		Commands CommandFound = Commands.find(command);
		if (CommandFound == null) {
			throw new InvalidRoboStateException();
		}
		switch (CommandFound) {
		case MOVE:
			move();
			break;
		case LEFT:
			turnLeft();
			break;
		case RIGHT:
			turnRight();
			break;
		default:
			throw new InvalidRoboStateException();		
		}

	}

	private void move() throws InvalidRoboStateException {
		switch (direction) {
		case NORTH:
			setPositionY(++positionY);
			break;
		case SOUTH:
			setPositionY(--positionY);
			break;
		case EAST:
			setPositionX(++positionX);
			break;
		case WEST:
			setPositionX(--positionX);
			break;
		default:
			throw new InvalidRoboStateException();		
		}
	}

	private void turnRight() throws InvalidRoboStateException {
		switch (direction) {
		case NORTH:
			direction = Directions.EAST;
			break;
		case SOUTH:
			direction = Directions.WEST;
			break;
		case EAST:
			direction = Directions.SOUTH;
			break;
		case WEST:
			direction = Directions.NORTH;
			break;
		default:
			throw new InvalidRoboStateException();		
		}
	}

	private void turnLeft() throws InvalidRoboStateException {
		switch (direction) {
		case NORTH:
			direction = Directions.WEST;
			break;
		case SOUTH:
			direction = Directions.EAST;
			break;
		case EAST:
			direction = Directions.NORTH;
			break;
		case WEST:
			direction = Directions.SOUTH;
			break;
		default:
			throw new InvalidRoboStateException();			
		}
	}

	public void setPositionX(int positionX) throws InvalidRoboStateException {
		if (positionX < 0 || positionX > 4) {
			throw new InvalidRoboStateException();
		}
		this.positionX = positionX;
	}

	public void setPositionY(int positionY) throws InvalidRoboStateException {
		if (positionY < 0 || positionY > 4) {
			throw new InvalidRoboStateException();
		}
		this.positionY = positionY;
	}

}