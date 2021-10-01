package game;

import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Rank {

	public Dados dados;
	public Conexao conexao;
	private JTable table;
	private PreparedStatement statement;
	private String men, sql;
	private String[] dadosDB;
	private DefaultTableModel tablemodel;
	private JScrollPane scrollpane;
	private ImageIcon fundotable;
	private JLabel fundo1,fundo2;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;
	
	public Rank() {
		
		conexao = new Conexao();
		dados = new Dados();
		
		InicializarComponentes();
		
	}

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
				String query = "select * from dados order by pontuacao desc";
				PreparedStatement ps = conexao.c.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					tablemodel.addColumn(rs.getMetaData().getColumnName(i));
				}
				
				while (rs.next()) {
					dadosDB = new String[rs.getMetaData().getColumnCount()];
					for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
						dadosDB[i - 1] = rs.getString(i);
					}
					tablemodel.addRow(dadosDB);
				}
				
				table.getTableHeader().setReorderingAllowed(false);
				table.setFont(new Font("Adventure", 0, 20));
				table.setRowHeight(30);
				table.getTableHeader().setFont(new Font("Adventure", 0, 20));
				table.getTableHeader().setBackground(new Color(47,79,79));
				table.getTableHeader().setForeground(Color.WHITE);
				table.setOpaque(false);
				fundotable = new ImageIcon(getClass().getResource("/game/imagens/fundorank.jpg"));
				fundo1 = new JLabel(fundotable);
				fundo1.setBounds(0,0,338,600);
				fundo2 = new JLabel(fundotable);
				fundo2.setBounds(480,0,338,600);
				
				DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
				
				renderer.setHorizontalAlignment(SwingConstants.CENTER);
				renderer.setBackground(new Color(46,139,87));
				for(int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
					
					table.getColumnModel().getColumn(i).setWidth(100);
					table.getColumnModel().getColumn(i).setCellRenderer(renderer);
					table.getColumnModel().getColumn(i).setResizable(false);
					
				}
			
			}
			
		} catch (SQLException error) {
		
		}
		
		scrollpane = new JScrollPane();
		scrollpane.setFocusable(false);
		scrollpane.setBounds(270, 0, 270,600);
		scrollpane.setViewportView(table);
		scrollpane.getViewport().setBackground(new Color (46,139,87));
		
	}

	public JScrollPane getScrollPane() {
		
		return scrollpane;
		
	}
	
	public JLabel getFundo1() {
		
		return fundo1;
		
	}
	
	public JLabel getFundo2() {
		
		return fundo2;
		
	}
	
	public String[] getDadosDB() {
		
		return dadosDB;
		
	}
	
}