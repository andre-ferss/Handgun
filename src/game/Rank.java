package game;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Rank {

	public Dados dados;
	private JTable table;
	private PreparedStatement statement;
	private String men, sql;
	private DefaultTableModel tablemodel;
	private JScrollPane scrollpane;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	
	public Rank() {
		
		conexao = new Conexao();
		dados = new Dados();
		
		InicializarComponentes();
		DefinirEventos();
		
	}

	Conexao conexao = new Conexao();

	public String atualizar(int operacao) {
		men = "Operação realizada com sucesso ";
		try {
			if(operacao == INCLUSAO) {
				sql = "insert into dados (nickname) values (?)";
				statement = conexao.c.prepareStatement(sql);
				statement.setString(1, dados.getNickname());
				
			} else if(operacao == ALTERACAO) {
				sql = "update dados set pontuacao = ? where nickname = ?";
				statement = conexao.c.prepareStatement(sql);
				statement.setInt(1, dados.getScore());
				statement.setString(2, dados.getNickname());
				
			} else if(operacao == EXCLUSAO) {
				sql = "delete from dados where nickname =?";
				statement = conexao.c.prepareStatement(sql);
				statement.setString(1,dados.getNickname());
		
			}
			if (statement.executeUpdate() == 0) {
				men = "Falha na operação";
			}
			
		} catch(SQLException erro) {
			men = "Falha na operação " + erro.toString();
		}
		return men;
	}
	
	public void InicializarComponentes() {
		try {
			if (conexao.getConnection()) {
				tablemodel = new DefaultTableModel(new String[] {}, 0) {
					public boolean isCellEditable(int col, int row) {
						return false;
					}
				};
				table = new JTable(tablemodel);
				String query = "select * from dados";
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