package game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Jogo extends JFrame implements MouseListener{
	
	private JPanel pane;
	private String name;
	private ButtonGroup bg;
	private Rank globalRank;
	private JComboBox comboBox;
	private Container container;
	private int score, difficulty;
	private JTextField textField = new JTextField();
	private JButton start, setNickName, mainMenu, guns, levels, rank;
	private JLabel label, timerField, mira, aim, namegame, cenario4, tag, backgroundnick;
	private JRadioButton rbBlacktail, rbAlienPistol, rbDeagle,rbShotgun, rbMP5, rbAWP, rbM4, rbAK, fase1, fase2, fase3, fase4, fase5;
	private ImageIcon img, imgmira, imgaim, imgcenario4, imgtag, btstart, btnickname, btlevels, btrank, btguns, imgname, imgbackgroundnick;
	
	private Font newFont;
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	SoundGuns sons = new SoundGuns();

	public Jogo() {

		inicializarComponentes();
		definirEventos();
		
	}

	private void inicializarComponentes() {
		
		try {
			newFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Adventure.otf"));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ge.registerFont(newFont);
		
		container = getContentPane();
		
		sons.maintheme();
		
		pane = new JPanel(null);
		
		imgaim = new ImageIcon(getClass().getResource("/game/imagens/aim.gif"));
		aim = new JLabel(imgaim);
		aim.setBounds(0, 0, 800, 600);
		
		imgmira = new ImageIcon(getClass().getResource("/game/imagens/crosshair.gif"));
		mira = new JLabel(imgmira);
		mira.setBounds(180,350,150,150);
		
		imgname = new ImageIcon(getClass().getResource("/game/imagens/name.png"));
		namegame = new JLabel(imgname);
		namegame.setBounds(360, 160, 400, 400);
		pane.add(namegame);
		
		comboBox = new JComboBox(new String[] {"Normal", "Hard", "Overkill"});
		comboBox.setBounds(690, 10, 100, 30);
		comboBox.setFocusable(false);
		comboBox.setOpaque(true);
		comboBox.setBorder(null);
		comboBox.setBackground(Color.black);
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font(newFont.getName(), newFont.getStyle(), 20));
		pane.add(comboBox);
		
		btstart = new ImageIcon(getClass().getResource("/game/imagens/btstart.jpg"));
		start = new JButton(btstart);
		start.setFocusable(false);
		start.setBounds(350, 550, 100, 35);
		pane.add(start);
		
		btnickname = new ImageIcon(getClass().getResource("/game/imagens/btnickname.jpg"));
		setNickName = new JButton(btnickname);
		setNickName.setFocusable(false);
		setNickName.setBounds(200, 405, 100, 35);
		pane.add(setNickName);
		
		btlevels = new ImageIcon(getClass().getResource("/game/imagens/btlevels.jpg"));
		levels = new JButton(btlevels);
		levels.setFocusable(false);
		levels.setBounds(30, 60, 100, 35);
		pane.add(levels);
		
		btrank = new ImageIcon(getClass().getResource("/game/imagens/btrank.jpg"));
		rank = new JButton(btrank);
		rank.setFocusable(false);
		rank.setBounds(10, 10, 100, 35);
		pane.add(rank);
		
		btguns = new ImageIcon(getClass().getResource("/game/imagens/btguns.jpg"));
		guns = new JButton(btguns);
		guns.setFocusable(false);
		guns.setBounds(50, 110, 100, 35);
		pane.add(guns);
		
		pane.add(mira);
		pane.add(aim);
		
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
		guns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				container.removeAll();
				container.add(guns());
				container.validate();
				
			}
		});
		
		
		levels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				container.removeAll();
				container.add(levels());
				container.validate();
				
			}
		});
		
	}
	
	public JPanel mainMenu() {
		
		if(!sons.getClip().isRunning()) {
			
			sons.maintheme();
			
		}
		
		pane = new JPanel(null);
		
		start.setBounds(350, 550, 100, 35);
		
		pane.add(comboBox);
		pane.add(start);
		pane.add(setNickName);
		pane.add(setNickName);
		pane.add(levels);
		pane.add(rank);
		pane.add(guns);
		pane.add(mira);
		pane.add(namegame);
		pane.add(aim);
		
		return pane;
		
	}
	
	public JPanel game() {
		
		sons.getClip().stop();
		
		img = new ImageIcon(getClass().getResource("/game/imagens/ghost.gif"));
		label = new JLabel(img);
		
		imgcenario4 = new ImageIcon(getClass().getResource("/game/imagens/cenario4.jpg"));
		cenario4 = new JLabel(imgcenario4);
		cenario4.setBounds(0, 0, 800, 600);
		
		timerField = new JLabel("100");
		timerField.setBounds(750, 10, 30, 30);
		timerField.setForeground(Color.WHITE);
		timerField.setFont(new Font(newFont.getName(), newFont.getStyle(), 20));
		
		pane = new JPanel(null);
		pane.add(timerField);
		
		pane.add(label);
		pane.add(cenario4);
		
		new PlayGame().start();
		
		return pane;
		
	}
	
	public JPanel nickName() {
		
		imgbackgroundnick= new ImageIcon(getClass().getResource("/game/imagens/backgroundnickname.gif"));
		backgroundnick = new JLabel(imgbackgroundnick);
		backgroundnick.setBounds(0,0,800,600);
		
		textField.setBounds(276, 135, 155, 30);
		textField.setOpaque(false);
		textField.setFont(new Font(newFont.getName(), newFont.getStyle(), 18));
		textField.setForeground(Color.BLACK);
		textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
		
		label = new JLabel();
		label.setFont(new Font(newFont.getName(),newFont.getStyle(),30));
		label.setForeground(Color.BLACK);
		label.setBounds(250, 70, 300, 30);
		
		imgtag = new ImageIcon(getClass().getResource("/game/imagens/nameTag.png"));
		tag = new JLabel(imgtag);
		tag.setBounds(200,0,400,300);
		
		start.setBounds(350, 400, 100, 35);
		mainMenu.setBounds(10, 10, 200, 30);
		
		new AnimatedTextLabel(label, "Enter your nick name: ").start();
		
		pane = new JPanel(null);
		pane.add(textField);
		pane.add(start);
		pane.add(label);
		pane.add(tag);
		pane.add(mainMenu);
		pane.add(backgroundnick);
		
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
	
		pane = new JPanel(null);
		
		label = new JLabel("Escolha uma arma");
		
		mainMenu.setBounds(10, 10, 200, 30);
		
		rbBlacktail = new JRadioButton("Blacktail");
		rbAlienPistol = new JRadioButton("AlienPistol");
		rbDeagle = new JRadioButton("Deagle");
		rbShotgun = new JRadioButton("Shotgun");
		rbMP5 = new JRadioButton("MP5");
		rbAWP = new JRadioButton("AWP");
		rbM4 = new JRadioButton("M4");
		rbAK = new JRadioButton("AK-47");
		
		bg = new ButtonGroup();
		bg.add(rbBlacktail);
		bg.add(rbAlienPistol);
		bg.add(rbDeagle);
		bg.add(rbShotgun);
		bg.add(rbMP5);
		bg.add(rbAWP);
		bg.add(rbM4);
		bg.add(rbAK);
		
		label.setBounds(450, 50, 130, 20);
		
		rbBlacktail.setBounds(150, 150, 100, 20);
		rbAlienPistol.setBounds(450, 150, 100, 20);
		rbDeagle.setBounds(750, 150, 100, 20);
		rbShotgun.setBounds(150, 300, 100, 20);
		rbMP5.setBounds(450, 300, 100, 20);
		rbAWP.setBounds(750, 300, 100, 20);
		rbM4.setBounds(300, 450, 100, 20);
		rbAK.setBounds(650, 450, 100, 20);
		
		pane.add(label);
		pane.add(rbBlacktail);
		pane.add(rbAlienPistol);
		pane.add(rbDeagle);
		pane.add(rbShotgun);
		pane.add(rbMP5);
		pane.add(rbAWP);
		pane.add(rbM4);
		pane.add(rbAK);
		pane.add(mainMenu);

		return pane;
	
}

	public JPanel levels() {
	
		pane =  new JPanel(null);
	
		label = new JLabel("Fases");
		
		mainMenu.setBounds(10, 10, 200, 30);
		
		fase1 = new JRadioButton("Fase 1");
		fase2 = new JRadioButton("Fase 2");
		fase3 = new JRadioButton("Fase 3");
		fase4 = new JRadioButton("Fase 4");
		fase5 = new JRadioButton("Fase 5");
		
		bg = new ButtonGroup();
		
		bg.add(fase1);
		bg.add(fase2);
		bg.add(fase3);
		bg.add(fase4);
		bg.add(fase5);
		
		label.setBounds(450, 50, 130, 20);
		
		fase1.setBounds(100, 200, 80, 20);
		fase2.setBounds(280, 200, 80, 20);
		fase3.setBounds(460, 200, 80, 20);
		fase4.setBounds(640, 200, 80, 20);
		fase5.setBounds(820, 200, 80, 20);
		
		pane.add(label);
		pane.add(fase1);
		pane.add(fase2);
		pane.add(fase3);
		pane.add(fase4);
		pane.add(fase5);
		pane.add(mainMenu);
		
		return pane;
	
}

	public static void main(String[] args) {
		
		Jogo frame = new Jogo();
		frame.setTitle("Hand-Gun");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(800, 600);
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
					
					Thread.sleep(200);
					
					if (Integer.parseInt(timerField.getText()) == 0) { 
						
						container.removeAll();
						container.add(globalRank());
						container.validate();
						
					}

				} catch (InterruptedException e) {

					e.printStackTrace();

				}

			}

		}, 1000, 1000);
	}
	
	private final int setInterval(Timer timer) {
		
		if(Integer.parseInt(timerField.getText()) == 1) timer.cancel();
		
		return Integer.parseInt(timerField.getText()) -1;
		
	}
	
	public class PlayGame extends Thread implements MouseListener{
		
		int x = 500, y = 374, randX = 1, randY = 1;
		
		public void run() {
			
			startCountDown();
			label.addMouseListener(this);
			pane.addMouseListener(this);
			
			try {
				while(true) {
					
					for(int i = 0; i <= 300; i++) {
						
						label.setBounds(x, y, 300, 300);
						pane.add(label);
						pane.add(cenario4);
						Thread.sleep(difficulty);
						
						if(timerField.getText().equals("0")) break;
					
						if(i <= 150) {
							
							if(i == 150) {
								randX = (int) (Math.random() * 5) + 1;
								randY = (int) (Math.random() * 5) + 1;
							}
							
							if(x <= 0 || x >= 800 || y <= 0 || y >= 600) {
								x = (int) (Math.random() * 400);
								y = (int) (Math.random() * 300);
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
						
						if(x <= 0 || x >= 800 || y <= 0 || y >= 600) {
							x = (int) (Math.random() * 400);
							y = (int) (Math.random() * 300);
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
			
			if(e.getSource().equals(label)) {
				
				score += 10;
				x = (int) (Math.random() * 400);
				y = (int) (Math.random() * 300);
				System.out.println("Score: " + score);
				
			}
			
			sons.AlienPistol();
			
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

