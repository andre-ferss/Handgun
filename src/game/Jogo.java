package game;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Jogo extends JFrame implements MouseListener{

	private int score, difficulty;
	private JPanel pane;
	private String name;
	private JComboBox comboBox;
	private ImageIcon img;
	private Rank globalRank;
	private Container container;
	private JLabel label, timerField;
	private JTextField textField = new JTextField();
	private JButton start, setNickName, mainMenu, guns, levels, rank;

	public Jogo() {

		inicializarComponentes();
		definirEventos();
		
	}

	private void inicializarComponentes() {
		
		container = getContentPane();
		
		pane = new JPanel(null);
		
		
		comboBox = new JComboBox(new String[] {"Normal", "Hard", "Overkill"});
		comboBox.setBounds(700, 400, 100, 20);
		comboBox.setFocusable(false);
		pane.add(comboBox);
		
		start = new JButton("Start");
		start.setFocusable(false);
		start.setBounds(352, 400, 300, 30);
		pane.add(start);
		
		setNickName = new JButton("Nickname");
		setNickName.setFocusable(false);
		setNickName.setBounds(352, 450, 300, 30);
		pane.add(setNickName);
		
		levels = new JButton("Levels");
		levels.setFocusable(false);
		levels.setBounds(352, 500, 300, 30);
		pane.add(levels);
		
		rank = new JButton("Global Rank");
		rank.setFocusable(false);
		rank.setBounds(352, 550, 300, 30);
		pane.add(rank);
		
		guns = new JButton("Guns");
		guns.setFocusable(false);
		guns.setBounds(352, 600, 300, 30);
		pane.add(guns);
		
		mainMenu = new JButton("Main Menu");
		
		container.add(pane);
		
	}

	private void definirEventos() {
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				score = 0;
				
				switch(comboBox.getSelectedIndex()){
					case 0:
						difficulty = 5;
						break;
					case 1:
						difficulty = 3;
						break;
					case 2:
						difficulty = 1;
						break;
				}
				
				if(textField.getText().equals("")) name = "Player Unknown";
				else name = textField.getText();
				
				System.out.println("Nickname: " + name);
				
				container.removeAll();
				container.add(game());
				container.validate();
				
			}
		});
		
		setNickName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				container.removeAll();
				container.add(nickName());
				container.validate();
				
			}
		});
		
		rank.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				container.removeAll();
				container.add(globalRank());
				container.validate();
				
			}
		});
		
		mainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				container.removeAll();
				container.add(mainMenu());
				container.validate();
				
			}
		});
		
	}
	
	public JPanel mainMenu() {
		
		pane = new JPanel(null);
		
		pane.add(comboBox);
		pane.add(start);
		pane.add(setNickName);
		pane.add(setNickName);
		pane.add(levels);
		pane.add(rank);
		pane.add(guns);
		
		return pane;
		
	}
	
	public JPanel game() {
		
		img = new ImageIcon(getClass().getResource("/game/imagens/ghost.gif"));
		label = new JLabel(img);
		
		timerField = new JLabel("100");
		timerField.setBounds(980, 10, 50, 50);
		
		pane = new JPanel(null);
		pane.add(timerField);
		pane.add(label);
			
		new PlayGame().start();
				
		return pane;
		
	}
	
	public JPanel nickName() {
		
		textField.setBounds(300, 100, 300, 30);
		
		label = new JLabel();
		label.setBounds(300, 50, 300, 30);
		
		start.setBounds(352, 400, 300, 30);
		
		new AnimatedTextLabel(label, "Enter your nick name: ").start();
		
		pane = new JPanel(null);
		pane.add(textField);
		pane.add(start);
		pane.add(label);
		
		return pane;
		
	}
	
	public JPanel globalRank() {
		
		pane = new JPanel(new BorderLayout());
		
		label = new JLabel("Score " + score);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		globalRank = new Rank();
		
		
		pane.add(label, BorderLayout.NORTH);
		pane.add(globalRank.getScrollPane(), BorderLayout.CENTER);
		pane.add(mainMenu, BorderLayout.SOUTH);
		
		return pane;
		
	}

	
	public JPanel guns() {
	
		pane = new JPanel();
	
	
		return pane;
	
}

	public JPanel levels() {
	
		pane =  new JPanel();
	
		return pane;
	
}

	public static void main(String[] args) {
		
		Jogo frame = new Jogo();
		frame.setTitle("Hand-Gun");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	public class AnimatedTextLabel extends Thread{
		
		JLabel label; String text;
		
		public AnimatedTextLabel(JLabel label, String text) {
			
			this.label = label;
			this.text = text;
			
		}
		
		public void run() {
			
			newText(label, text);
			
		}
		
		public void newText(JLabel label, String text) {
			try {
				String finalText = "";
				for(int i = 0; i < text.length(); i++) {
					finalText = finalText + text.charAt(i);
					label.setText(finalText);
					Thread.sleep(50);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void startCountDown() {
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				timerField.setText("" + setInterval(timer));

				try {

					if (Integer.parseInt(timerField.getText()) == 0) { 
						
						container.removeAll();
						container.add(globalRank());
						container.validate();
						
					}

					Thread.sleep(200);

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			}

		}, 1000, 1000);
	}
	
	private final int setInterval(Timer timer) {
		
		if(Integer.parseInt(timerField.getText()) == 0) timer.cancel();
		
		return Integer.parseInt(timerField.getText()) -1;
		
	}
	
	public class PlayGame extends Thread implements MouseListener{
		
		SoundGuns sons = new SoundGuns();
		
		int x = 500, y = 374, randX = 1, randY = 1;
		
		public void run() {
			
			startCountDown();
			label.addMouseListener(this);
			
			try {
				while(true) {
					
					for(int i = 0; i < 300; i++) {
						
						label.setBounds(x, y, 300, 300);
						pane.add(label);
						Thread.sleep(difficulty);
						
						if(timerField.getText().equals("0")) break;
					
						if(i <= 150) {
							
							if(i == 150) {
								randX = (int) (Math.random() * 5) + 1;
								randY = (int) (Math.random() * 5) + 1;
							}
							
							if(x <= 0 || x >= 1024 || y <= 0 || y >= 768) {
								x = (int) (Math.random() * 900);
								y = (int) (Math.random() * 700);
								randX *= -1;
								if(randY < 0) {
									randY *= -1;
								}
							}
							
							x += randX;
							y += randY;
							//System.out.println(i);
							continue;
						}
						
						if(x <= 0 || x >= 1024 || y <= 0 || y >= 768) {
							x = (int) (Math.random() * 900);
							y = (int) (Math.random() * 700);
							randY *= -1;
							if(randX < 0) {
								randX *= -1;
							}
						}
						
							x -= randX;
							y -= randY;
					}
					
					if(timerField.getText().equals("0")) break;
					
				}
				
			}catch(Exception e) {
				
			}
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			sons.AlienPistol();
			
			score += 10;
			x = (int) (Math.random() * 900);
			y = (int) (Math.random() * 700);
			System.out.println("Score: " + score);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


