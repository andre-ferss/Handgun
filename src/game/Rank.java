package game;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

public class Rank {

	private JTable table;
	private DefaultTableModel tablemodel;
	private JScrollPane scrollpane;
	
	public Rank() {
		
		InicializarComponentes();
		DefinirEventos();
		
	}

	conexao conexao = new conexao();

	public void InicializarComponentes() {
		try {
			if (conexao.getConnection()) {
				tablemodel = new DefaultTableModel(new String[] {}, 0) {
					public boolean isCellEditable(int col, int row) {
						return false;
					}
				};
				table = new JTable(tablemodel);
				String query = "select * from estoque";
				PreparedStatement ps = conexao.c.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					tablemodel.addColumn(rs.getMetaData().getColumnName(i));
				}
				while (rs.next()) {
					String[] dados = new String[rs.getMetaData().getColumnCount()];
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						dados[i - 1] = rs.getString(i);
					}
					tablemodel.addRow(dados);
				}

			}
		} catch (SQLException error) {

		}
		
		scrollpane = new JScrollPane();
		scrollpane.setFocusable(false);
		scrollpane.setBounds(100, 100, 500, 300);
		scrollpane.setViewportView(table);
		
	}

	public JScrollPane getScrollPane() {
		
		return scrollpane;
		
	}
	
	private void DefinirEventos() {

	}
}