package game;

import java.sql.*;
import javax.swing.JOptionPane;
public class conexao{
	
	public Connection c = null;
	private final String driver = "com.mysql.jdbc.Driver";
	private final String dbname = "handgun";
	private final String url = "jdbc:mysql://localhost:3306/"+dbname;
	private final String login = "root";
	private final String senha = "";
	
	
	public boolean getConnection(){
		try{
			Class.forName(driver);
			c = DriverManager.getConnection(url,login,senha);
			System.out.println("Conectou!");
			return true;
		}
			catch (ClassNotFoundException erro){
				JOptionPane.showMessageDialog(null,"Driver não encontrado!\n" + erro.toString());
				return false;
			}
			catch (SQLException erro){
				JOptionPane.showMessageDialog(null,"Problema de conexão com a fonte de dados!\n" + erro.toString());
				return false;
			}
			
		}
	public void close(){
		try{
			c.close();
			System.out.println("Desconectou");
		}catch(SQLException erro){
			
		}
	}
}