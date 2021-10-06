package game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
	//CLASSE MAIN
public class Jogo extends JFrame implements MouseListener {
	//VARIAVEIS
	private JPanel pane;
	private Rank globalRank;
	private JComboBox comboBox;
	private Container container;
	private int score, difficulty, width = 100, height = 100;
	private JTextField textField = new JTextField();
	private JButton start, setNickName, mainMenu, guns, levels, rank, btdelete;
	private String name, gun = "", cenarioNome = "cenario1", alvoNome = "among";
	private ImageIcon imgselecao1, imgselecao2, imgselecao3, imgselecao4, imgselecao5, alvo, cenarioImg, icocenario1, icocenario2, icocenario3, icocenario4, icocenario5, imgmira, imgaim,
	imgtag, btstart, imgdelete, btnickname, btlevels, btrank, btguns, imgname, imgfundonickname, imgfundolevels, imgfundoguns, fundotable, 
	imgbrilhos, imgcaveirinha, imgtiro, imgawp, imgak47, imgblacktail, imgdeagle, imgshotgun, imgm4, imgmp5, namearea, imgmainMenu;
	private JLabel selecao, label, cenario, scoreField, timerField, mira, aim, namegame, tag, fundonickname, fundolevels, fundoguns, brilhos, caveirinha,
	tiro, awp, ak47, blacktail, shotgun, deagle, m4, mp5, fase1, fase2, fase3, fase4, fase5, fundo1, fundo2;

	private Font newFont;
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

	SoundGuns sons = new SoundGuns();
	//CONSTRUTOR
	public Jogo() {

		inicializarComponentes();
		definirEventos();

	}
	//INICIALIZAR COMPONENTES
	private void inicializarComponentes() {
		//NEW FONT
		try {
			newFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Adventure.otf"));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ge.registerFont(newFont);
		
		container = getContentPane();

		sons.maintheme();
		//ADICIONANDO COMPONENTES AO JPANEL
		pane = new JPanel(null);

		imgaim = new ImageIcon(getClass().getResource("/game/imagens/aim.gif"));
		aim = new JLabel(imgaim);
		aim.setBounds(0, 0, 800, 600);

		imgmira = new ImageIcon(getClass().getResource("/game/imagens/crosshair.gif"));
		mira = new JLabel(imgmira);
		mira.setBounds(180, 350, 150, 150);

		imgname = new ImageIcon(getClass().getResource("/game/imagens/name.png"));
		namegame = new JLabel(imgname);
		namegame.setBounds(360, 160, 400, 400);
		pane.add(namegame);

		imgbrilhos = new ImageIcon(getClass().getResource("/game/imagens/brilhos.gif"));
		brilhos = new JLabel(imgbrilhos);
		brilhos.setBounds(600, 300, 100, 100);
		pane.add(brilhos);

		imgcaveirinha = new ImageIcon(getClass().getResource("/game/imagens/caveirinha.png"));
		caveirinha = new JLabel(imgcaveirinha);
		caveirinha.setBounds(650, 10, 32, 32);
		pane.add(caveirinha);

		comboBox = new JComboBox(new String[] { "Normal", "Hard", "Overkill" });
		comboBox.setBounds(690, 10, 100, 30);
		comboBox.setFocusable(false);
		comboBox.setOpaque(true);
		comboBox.setBorder(null);
		comboBox.setBackground(Color.black);
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font(newFont.getName(), newFont.getStyle(), 20));
		pane.add(comboBox);

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

		btstart = new ImageIcon(getClass().getResource("/game/imagens/btstart.jpg"));
		start = new JButton(btstart);
		start.setBounds(350, 550, 100, 35);
		pane.add(start);

		pane.add(mira);
		pane.add(aim);

		imgdelete = new ImageIcon(getClass().getResource("/game/imagens/btdelete.jpg"));
		btdelete = new JButton(imgdelete);
		btdelete.setBounds(450, 500, 100, 35);

		imgmainMenu = new ImageIcon(getClass().getResource("/game/imagens/btmenu.jpg"));
		mainMenu = new JButton(imgmainMenu);

		container.add(pane);

	}
	//EVENTOS
	private void definirEventos() {

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				score = 0;

				switch (comboBox.getSelectedIndex()) {
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
				
				if (textField.getText().equals("")) name = "Player Unknown";
				else name = textField.getText();
				
				globalRank = new Rank();
				
				System.out.println("Nickname: " + name);
				
				if(globalRank.getDadosDB().length > 0) {
					
					for(int i = 0; i < globalRank.getDadosDB().length; i+=2) {
						
						if(globalRank.getDadosDB()[i].equals(name)) {
						
							System.out.println("Already registered");
							break;
						
						}else {
							
							if((i + 2) < globalRank.getDadosDB().length) {
								
								continue;
								
							}
							
							globalRank.dados.setNickname(name);
							System.out.println(globalRank.atualizar(Rank.INCLUSAO));
						
						}
					
					}
					
				}else {
					
					globalRank.dados.setNickname(name);
					System.out.println(globalRank.atualizar(Rank.INCLUSAO));
					
				}
				
				
				container.removeAll();
				container.add(game());
				container.validate();

			}
		});

		btdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				globalRank = new Rank();

				name = textField.getText();

				System.out.println("Nickname Deletado!: " + name);

				globalRank.dados.setNickname(name);

				System.out.println(globalRank.atualizar(Rank.EXCLUSAO));

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
	//MENU
	public JPanel mainMenu() {

		if (!sons.getClip().isRunning()) {

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
		pane.add(caveirinha);
		pane.add(brilhos);
		pane.add(aim);

		return pane;

	}
	//JOGO
	public JPanel game() {

		sons.getClip().stop();

		alvo = new ImageIcon(getClass().getResource("/game/imagens/" + alvoNome + ".gif"));
		label = new JLabel(alvo);

		cenarioImg= new ImageIcon(getClass().getResource("/game/imagens/" + cenarioNome + ".jpg"));
		
		cenario = new JLabel(cenarioImg);
		cenario.setBounds(0, 0, 800, 600);

		scoreField = new JLabel("Score: 0");
		scoreField.setBounds(20, 10, 150, 30);
		scoreField.setForeground(Color.WHITE);
		scoreField.setFont(new Font(newFont.getName(), newFont.getStyle(), 20));
		
		timerField = new JLabel("30");
		timerField.setBounds(750, 10, 30, 30);
		timerField.setForeground(Color.WHITE);
		timerField.setFont(new Font(newFont.getName(), newFont.getStyle(), 20));

		pane = new JPanel(null);
		pane.add(scoreField);
		pane.add(timerField);

		pane.add(label);
		pane.add(cenario);

		new PlayGame().start();

		return pane;

	}
	//NICKNAME
	public JPanel nickName() {

		imgfundonickname = new ImageIcon(getClass().getResource("/game/imagens/fundonickname.gif"));
		fundonickname = new JLabel(imgfundonickname);
		fundonickname.setBounds(0, 0, 800, 600);

		textField.setBounds(276, 285, 155, 30);
		textField.setOpaque(false);
		textField.setFont(new Font(newFont.getName(), newFont.getStyle(), 18));
		textField.setForeground(Color.BLACK);
		textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));

		label = new JLabel();
		label.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		label.setForeground(Color.BLACK);
		label.setBounds(250, 220, 300, 30);

		imgtag = new ImageIcon(getClass().getResource("/game/imagens/nameTag.png"));
		tag = new JLabel(imgtag);
		tag.setBounds(200, 150, 400, 300);

		imgtiro = new ImageIcon(getClass().getResource("/game/imagens/tiro.png"));
		tiro = new JLabel(imgtiro);
		tiro.setBounds(212, 271, 63, 60);

		start.setBounds(250, 500, 100, 35);
		mainMenu.setBounds(20, 20, 100, 35);

		new AnimatedTextLabel(label, "Enter your nick name: ").start();

		pane = new JPanel(null);
		pane.add(textField);
		pane.add(start);
		pane.add(btdelete);
		pane.add(label);
		pane.add(tiro);
		pane.add(tag);
		pane.add(mainMenu);
		pane.add(fundonickname);

		return pane;

	}
	//RANK
	public JPanel globalRank() {

		pane = new JPanel(null);
		
		globalRank = new Rank();
		pane.add(globalRank.getScrollPane());
		
		mainMenu.setBounds(10,10,100,35);
		start.setBounds(690, 10, 100, 35);
		pane.add(mainMenu);
		pane.add(start);
		
		fundotable = new ImageIcon(getClass().getResource("/game/imagens/fundorank.jpg"));
		fundo1 = new JLabel(fundotable);
		fundo1.setBounds(-35, 0, 338, 600);
		fundo2 = new JLabel(fundotable);
		fundo2.setBounds(500, 0, 338, 600);
		
		pane.add(fundo1);
		pane.add(fundo2);

		return pane;

	}
	//ARMEIRO
	public JPanel guns() {

		pane = new JPanel(null);

		imgfundoguns = new ImageIcon(getClass().getResource("/game/imagens/fundoguns.gif"));
		fundoguns = new JLabel(imgfundoguns);
		fundoguns.setBounds(0, 0, 800, 600);

		mainMenu.setBounds(20, 20, 100, 35);
		pane.add(mainMenu);

		namearea = new ImageIcon(getClass().getResource("/game/imagens/gunsmith.png"));
		label = new JLabel(namearea);
		label.setBounds(520, 0, 318, 100);
		pane.add(label);

		imgak47 = new ImageIcon(getClass().getResource("/game/imagens/ak47.gif"));
		ak47 = new JLabel("Ak-47", imgak47, JLabel.CENTER);
		ak47.setHorizontalTextPosition(JLabel.CENTER);
		ak47.setVerticalTextPosition(JLabel.BOTTOM);
		ak47.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		ak47.setForeground(Color.WHITE);
		ak47.setBounds(80, 240, 200, 200);
		ak47.addMouseListener(this);
		pane.add(ak47);

		imgawp = new ImageIcon(getClass().getResource("/game/imagens/awp.gif"));
		awp = new JLabel("AWP", imgawp, JLabel.CENTER);
		awp.setHorizontalTextPosition(JLabel.CENTER);
		awp.setVerticalTextPosition(JLabel.BOTTOM);
		awp.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		awp.setForeground(Color.WHITE);
		awp.setBounds(250, 0, 200, 200);
		awp.addMouseListener(this);
		pane.add(awp);

		imgdeagle = new ImageIcon(getClass().getResource("/game/imagens/deagle.gif"));
		deagle = new JLabel("Desert Eagle", imgdeagle, JLabel.CENTER);
		deagle.setHorizontalTextPosition(JLabel.CENTER);
		deagle.setVerticalTextPosition(JLabel.BOTTOM);
		deagle.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		deagle.setForeground(Color.WHITE);
		deagle.setBounds(320, 380, 200, 200);
		deagle.addMouseListener(this);
		pane.add(deagle);

		imgshotgun = new ImageIcon(getClass().getResource("/game/imagens/shotgun.gif"));
		shotgun = new JLabel("Shotgun", imgshotgun, JLabel.CENTER);
		shotgun.setHorizontalTextPosition(JLabel.CENTER);
		shotgun.setVerticalTextPosition(JLabel.BOTTOM);
		shotgun.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		shotgun.setForeground(Color.WHITE);
		shotgun.setBounds(420, 150, 200, 200);
		shotgun.addMouseListener(this);
		pane.add(shotgun);

		imgblacktail = new ImageIcon(getClass().getResource("/game/imagens/blacktail.gif"));
		blacktail = new JLabel("Blacktail", imgblacktail, JLabel.CENTER);
		blacktail.setHorizontalTextPosition(JLabel.CENTER);
		blacktail.setVerticalTextPosition(JLabel.BOTTOM);
		blacktail.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		blacktail.setForeground(Color.WHITE);
		blacktail.setBounds(630, 300, 200, 150);
		blacktail.addMouseListener(this);
		pane.add(blacktail);

		imgm4 = new ImageIcon(getClass().getResource("/game/imagens/m4.gif"));
		m4 = new JLabel("M4", imgm4, JLabel.CENTER);
		m4.setHorizontalTextPosition(JLabel.CENTER);
		m4.setVerticalTextPosition(JLabel.BOTTOM);
		m4.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		m4.setForeground(Color.WHITE);
		m4.setBounds(0, 30, 200, 200);
		m4.addMouseListener(this);
		pane.add(m4);

		imgmp5 = new ImageIcon(getClass().getResource("/game/imagens/mp5.gif"));
		mp5 = new JLabel("MP5", imgmp5, JLabel.CENTER);
		mp5.setHorizontalTextPosition(JLabel.CENTER);
		mp5.setVerticalTextPosition(JLabel.BOTTOM);
		mp5.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		mp5.setForeground(Color.WHITE);
		mp5.setBounds(580, 30, 200, 200);
		mp5.addMouseListener(this);
		pane.add(mp5);

		pane.add(fundoguns);

		return pane;

	}
	//FASES
	public JPanel levels() {

		pane = new JPanel(null);

		namearea = new ImageIcon(getClass().getResource("/game/imagens/phases.png"));
		label = new JLabel(namearea);
		label.setBounds(600, 0, 318, 100);
		pane.add(label);

		imgfundolevels = new ImageIcon(getClass().getResource("/game/imagens/fundolevels.gif"));
		fundolevels = new JLabel(imgfundolevels);
		fundolevels.setBounds(0, 0, 800, 600);

		mainMenu.setBounds(20, 20, 100, 35);
		pane.add(mainMenu);

		icocenario1 = new ImageIcon(getClass().getResource("/game/imagens/cenario1Icon.jpg"));
		fase1 = new JLabel(icocenario1);
		fase1.setBounds(80, 350, 150, 100);
		fase1.addMouseListener(this);
		pane.add(fase1);

		icocenario2 = new ImageIcon(getClass().getResource("/game/imagens/cenario2Icon.jpg"));
		fase2 = new JLabel(icocenario2);
		fase2.setBounds(320, 350, 150, 100);
		fase2.addMouseListener(this);
		pane.add(fase2);

		icocenario3 = new ImageIcon(getClass().getResource("/game/imagens/cenario3Icon.jpg"));
		fase3 = new JLabel(icocenario3);
		fase3.setBounds(560, 350, 150, 100);
		fase3.addMouseListener(this);
		pane.add(fase3);

		icocenario4 = new ImageIcon(getClass().getResource("/game/imagens/cenario4Icon.jpg"));
		fase4 = new JLabel(icocenario4);
		fase4.setBounds(200, 490, 150, 100);
		fase4.addMouseListener(this);
		pane.add(fase4);

		icocenario5 = new ImageIcon(getClass().getResource("/game/imagens/cenario5Icon.jpg"));
		fase5 = new JLabel(icocenario5);
		fase5.setBounds(450, 490, 150, 100);
		fase5.addMouseListener(this);
		pane.add(fase5);
		
		selecao = new JLabel();
		selecao.setBounds(200, 50, 400, 250);
		selecao.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
		selecao.addMouseListener(this);
		pane.add(selecao);
		
		imgselecao1	= new ImageIcon(getClass().getResource("/game/imagens/selecao1.jpg"));
		
		imgselecao2	= new ImageIcon(getClass().getResource("/game/imagens/selacao2.jpg"));
		
	    imgselecao3	= new ImageIcon(getClass().getResource("/game/imagens/selecao3.jpg"));
	    
	    imgselecao4	= new ImageIcon(getClass().getResource("/game/imagens/selecao4.jpg"));
	    
	    imgselecao5	= new ImageIcon(getClass().getResource("/game/imagens/selecao5.jpg"));
		
		pane.add(fundolevels);
		
		return pane;

	}

	public static void main(String[] args) {
		
		new Splash();
		
		ImageIcon icon = new ImageIcon("src\\game\\imagens\\handgunIcon.png");
		Jogo frame = new Jogo();
		frame.setTitle("Hand-Gun");
		frame.setIconImage(icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	//ANIMAÇÃO DE TEXTO
	public class AnimatedTextLabel extends Thread {

		JLabel label;
		String text;

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
				for (int i = 0; i < text.length(); i++) {
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
	//TIMER
	public void startCountDown() {

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				timerField.setText("" + setInterval(timer));

				try {

					Thread.sleep(200);

					if (Integer.parseInt(timerField.getText()) == 0) {
						
						globalRank = new Rank();
						
						for(int i = 1; i <= globalRank.getDadosDB().length; i += 2) {
							
							if(score > Integer.parseInt(globalRank.getDadosDB()[i])) {
							
								globalRank.dados.setScore(score);
								globalRank.dados.setNickname(name);
								
								System.out.println("pontuacao atualizada!: " + score);
								System.out.println(globalRank.atualizar(Rank.ALTERACAO));
								
								break;
							
							}
						}
						
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

		if (Integer.parseInt(timerField.getText()) == 1) timer.cancel();

		return Integer.parseInt(timerField.getText()) - 1;

	}
	//AÇÕES COM MOUSE LISTENER
	public class PlayGame extends Thread implements MouseListener {

		int x = 500, y = 374, randX = 1, randY = 1;

		public void run() {

			startCountDown();
			label.addMouseListener(this);
			pane.addMouseListener(this);

			try {
		
				while (true) {

					for (int i = 0; i <= 300; i++) {

						label.setBounds(x, y, width, height);
						pane.add(label);
						pane.add(cenario);
						Thread.sleep(difficulty);

						if (timerField.getText().equals("0")) break;

						if (i <= 150) {

							if (i == 150) {
								randX = (int) (Math.random() * 5) + 1;
								randY = (int) (Math.random() * 5) + 1;
							}

							if (x <= 0 || x >= 800 || y <= 0 || y >= 600) {
								x = (int) (Math.random() * 700 + 50);
								y = (int) (Math.random() * 500 + 50);
								randX *= -1;
								if (randY < 0) {
									randY *= -1;
								}
							}

							x += randX;
							y += randY;
							// System.out.println(i);
							continue;
						}

					if (x <= 0 || x >= 800 || y <= 0 || y >= 600) {
						x = (int) (Math.random() * 700 + 50);
						y = (int) (Math.random() * 500 + 50);
						randY *= -1;
						if (randX < 0) {
							randX *= -1;
						}
					}

					x -= randX;
					y -= randY;
					
					}

				if (timerField.getText().equals("0")) break;
				
				}

			} catch (Exception e) {

			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (e.getSource().equals(label)) {
				
				score += 10;
				x = (int) (Math.random() * 700 + 50);
				y = (int) (Math.random() * 500 + 50);
				scoreField.setText("Score: " + score);
				sons.acerto();

			}
			
			switch(gun) {
			
				case "Blacktail":
					sons.Blacktail();
					break;
				case "Desert Eagle":
					sons.DesertEagle();
					break;
				case "Ak-47":
					sons.AK47();
					break;
				case "M4":
					sons.M4();
					break;
				case "Shotgun":
					sons.Shotgun();
					break;
				case "MP5":
					sons.mp5();
					break;
				case "AWP":
					sons.awp();
					break;
				default:
					sons.AlienPistol();
					break;
			}
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
		
		
		if(e.getClickCount() == 2) {
		
			if(((JLabel)(e.getSource())).getText() != null) {
			
				((JLabel)(e.getSource())).setForeground(Color.GREEN); 
				gun = ((JLabel)(e.getSource())).getText();
				
				switch(((JLabel)(e.getSource())).getText()) {
				
					case "Blacktail":
						awp.setForeground(Color.WHITE);
						ak47.setForeground(Color.WHITE);
						shotgun.setForeground(Color.WHITE);
						deagle.setForeground(Color.WHITE);
						m4.setForeground(Color.WHITE);
						mp5.setForeground(Color.WHITE);
						break;
					case "Desert Eagle":
						awp.setForeground(Color.WHITE);
						ak47.setForeground(Color.WHITE);
						shotgun.setForeground(Color.WHITE);
						blacktail.setForeground(Color.WHITE);
						m4.setForeground(Color.WHITE);
						mp5.setForeground(Color.WHITE);
						break;
					case "Ak-47":
						awp.setForeground(Color.WHITE);
						deagle.setForeground(Color.WHITE);
						shotgun.setForeground(Color.WHITE);
						blacktail.setForeground(Color.WHITE);
						m4.setForeground(Color.WHITE);
						mp5.setForeground(Color.WHITE);
						break;
					case "M4":
						awp.setForeground(Color.WHITE);
						deagle.setForeground(Color.WHITE);
						shotgun.setForeground(Color.WHITE);
						blacktail.setForeground(Color.WHITE);
						ak47.setForeground(Color.WHITE);
						mp5.setForeground(Color.WHITE);
						break;
					case "Shotgun":
						awp.setForeground(Color.WHITE);
						deagle.setForeground(Color.WHITE);
						m4.setForeground(Color.WHITE);
						blacktail.setForeground(Color.WHITE);
						ak47.setForeground(Color.WHITE);
						mp5.setForeground(Color.WHITE);
						break;
					case "MP5":
						awp.setForeground(Color.WHITE);
						deagle.setForeground(Color.WHITE);
						shotgun.setForeground(Color.WHITE);
						blacktail.setForeground(Color.WHITE);
						ak47.setForeground(Color.WHITE);
						shotgun.setForeground(Color.WHITE);
						break;
					case "AWP":
						m4.setForeground(Color.WHITE);
						deagle.setForeground(Color.WHITE);
						shotgun.setForeground(Color.WHITE);
						blacktail.setForeground(Color.WHITE);
						ak47.setForeground(Color.WHITE);
						mp5.setForeground(Color.WHITE);
						break;
						
				}
			
			}
			
			if(((JLabel)(e.getSource())).getText() == null) {
			
				((JLabel)(e.getSource())).setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN)); 
			
				cenarioNome = (((JLabel)(e.getSource())).getIcon().toString().substring((((JLabel)(e.getSource())).getIcon().toString().length() - 16), (((JLabel)(e.getSource())).getIcon().toString().length() - 8)));
			
				switch(cenarioNome) {
			
					case "cenario1":
						alvoNome = "among";
						width = 100;
						height = 100;
						fase2.setBorder(null);
						fase3.setBorder(null);
						fase4.setBorder(null);
						fase5.setBorder(null);
						selecao.setIcon(imgselecao1);
						break;
					case "cenario2":
						alvoNome = "powergroot";
						width = 125;
						height = 180;
						fase1.setBorder(null);
						fase3.setBorder(null);
						fase4.setBorder(null);
						fase5.setBorder(null);
						selecao.setIcon(imgselecao2);
						break;
					case "cenario3":
						alvoNome = "ghost";
						width = 130;
						height = 150;
						fase2.setBorder(null);
						fase1.setBorder(null);
						fase4.setBorder(null);
						fase5.setBorder(null);
						selecao.setIcon(imgselecao3);
						break;
					case "cenario4":
						alvoNome = "star";
						width = 180;
						height = 180;
						fase2.setBorder(null);
						fase1.setBorder(null);
						fase3.setBorder(null);
						fase5.setBorder(null);
						selecao.setIcon(imgselecao4);
						break;
					case "cenario5":
						alvoNome = "tubarao";
						width = 100;
						height = 130;
						fase2.setBorder(null);
						fase3.setBorder(null);
						fase4.setBorder(null);
						fase1.setBorder(null);
						selecao.setIcon(imgselecao5);
						break;
				}
			
			}
			
		}else {
			
			if(((JLabel)(e.getSource())).getText() != null) {
				
				((JLabel)(e.getSource())).setForeground(Color.WHITE);
				gun = "";
			
			}
			
			if(((JLabel)(e.getSource())).getText() == null) {
				
				selecao.setIcon(null);
				
				((JLabel)(e.getSource())).setBorder(null); 
				cenarioNome = "cenario1";
			}
			
		}
		
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
