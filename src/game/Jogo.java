package game;

import java.awt.Container;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jogo extends JFrame {

	private JPanel pane;
	private Container container;

	public Jogo() {

		inicializarComponentes();
		definirEventos();

	}

	private void inicializarComponentes() {

		container = getContentPane();

		pane = new JPanel(null);

	}

	private void definirEventos() {

	}

	public static void main(String args[]) {

		Jogo frame = new Jogo();
		frame.setTitle("Hand-Gun");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

	}

}
