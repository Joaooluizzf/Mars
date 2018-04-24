package br.com.senior.robo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.senior.robo.exceptions.InvalidRoboStateException;

public class RoboHandlerTest {

	@Test
	public void testa_movimento_com_rotacao_para_direita() throws InvalidRoboStateException {
		String command = "MMRMMRMM";
		String expected = "(2, 0, S)";

		String robo = RoboHandler.execute(command, new RoboInMemory());

		assertEquals(expected, robo.toString());
	}

	@Test
	public void testa_movimento_para_esquerda() throws InvalidRoboStateException {
		String command = "MML";
		String expected = "(0, 2, W)";

		String robo = RoboHandler.execute(command, new RoboInMemory());

		assertEquals(expected, robo.toString());
	}

	@Test
	public void testa_repetição_da_requisicao_com_movimento_para_esquerda() throws InvalidRoboStateException {
		String command = "MML";
		String expected = "(0, 2, W)";

		String robo = RoboHandler.execute(command, new RoboInMemory());

		assertEquals(expected, robo.toString());

		robo = null;
		robo = RoboHandler.execute(command, new RoboInMemory());

		assertEquals(expected, robo.toString());
	}

	@Test(expected = InvalidRoboStateException.class)
	public void testa_comando_invalido() throws InvalidRoboStateException {
		String command = "AAA";
		RoboHandler.execute(command, new RoboInMemory());
	}

	@Test(expected = InvalidRoboStateException.class)
	public void testa_posicao_invalida() throws InvalidRoboStateException {
		String command = "MMMMMMMMMMMMMMMMMMMMMMMM";
		RoboHandler.execute(command, new RoboInMemory());
	}

}
