package game;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class Jogo extends JFrame implements MouseListener {

	private Rank r;
	private JPanel pane;
	private String name;
	private Rank globalRank;
	private JComboBox comboBox;
	private Container container;
	private int score, difficulty;
	private JTextField textField = new JTextField();
	private JButton start, setNickName, mainMenu, guns, levels, rank, startbd, btdelete;
	private ImageIcon alvo1, alvo2, alvo3, alvo4, alvo5, alvo6, alvo7, alvo8, imgcenario2, imgcenario3, imgcenario4,
	imgcenario5, icocenario1, icocenario2, icocenario3, icocenario4, icocenario5, imgmira, imgaim, imgcenario,
	imgcenario1, imgtag, btstart, imgdelete, btnickname, btlevels, btrank, btguns, imgname, imgfundonickname,
	imgfundolevels, imgfundoguns, imgbrilhos, imgcaveirinha, imgtiro, imgawp, imgak47, imgblacktail, imgdeagle,
	imgshotgun, imgm4, imgmp5, namearea, imgmainMenu;
	private JLabel label, cenario1, cenario2, cenario3, cenario4, cenario5, timerField, mira, aim, namegame, tag,
	fundonickname, fundolevels, fundoguns, brilhos, caveirinha, tiro, awp, ak47, blacktail, shotgun, deagle, m4,
	mp5, fase1, fase2, fase3, fase4, fase5;

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
		startbd = new JButton(btstart);
		startbd.setBounds(350, 550, 100, 35);
		pane.add(startbd);

		pane.add(mira);
		pane.add(aim);

		imgdelete = new ImageIcon(getClass().getResource("/game/imagens/btdelete.jpg"));
		btdelete = new JButton(imgdelete);
		btdelete.setBounds(450, 500, 100, 35);

		imgmainMenu = new ImageIcon(getClass().getResource("/game/imagens/btmenu.jpg"));
		mainMenu = new JButton(imgmainMenu);

		container.add(pane);

	}

	private void definirEventos() {

		startbd.addActionListener(new ActionListener() {
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

				r = new Rank();
				if (!r.conexao.getConnection()) {
					JOptionPane.showMessageDialog(null, "falha na conexao");
					System.exit(0);
				}

				if (textField.getText().equals(""))
					name = "Player Unknown";

				else
					name = textField.getText();

				System.out.println("Nickname: " + name);

				r.dados.setNickname(name);

				JOptionPane.showMessageDialog(null, r.atualizar(Rank.INCLUSAO));

				container.removeAll();
				container.add(game());
				container.validate();

			}
		});

		btdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				r = new Rank();
				if (!r.conexao.getConnection()) {
					JOptionPane.showMessageDialog(null, "falha na conexao");
					System.exit(0);
				}

				name = textField.getText();

				System.out.println("Nickname Deletado!: " + name);

				r.dados.setNickname(name);

				JOptionPane.showMessageDialog(null, r.atualizar(Rank.EXCLUSAO));

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

	public JPanel game() {

		sons.getClip().stop();

		alvo1 = new ImageIcon(getClass().getResource("/game/imagens/among.gif"));
		alvo2 = new ImageIcon(getClass().getResource("/game/imagens/ghost.gif"));
		alvo3 = new ImageIcon(getClass().getResource("/game/imagens/powergroot.gif"));
		alvo4 = new ImageIcon(getClass().getResource("/game/imagens/star.gif"));
		alvo5 = new ImageIcon(getClass().getResource("/game/imagens/tubarao.gif"));

		label = new JLabel(alvo1);

		imgcenario1 = new ImageIcon(getClass().getResource("/game/imagens/cenario1.jpg"));
		cenario1 = new JLabel(imgcenario1);
		cenario1.setBounds(0, 0, 1067, 600);

		imgcenario2 = new ImageIcon(getClass().getResource("/game/imagens/cenario2.jpg"));
		cenario2 = new JLabel(imgcenario1);
		cenario2.setBounds(0, 0, 1067, 600);

		imgcenario3 = new ImageIcon(getClass().getResource("/game/imagens/cenario3.jpg"));
		cenario3 = new JLabel(imgcenario1);
		cenario3.setBounds(0, 0, 1067, 600);

		imgcenario4 = new ImageIcon(getClass().getResource("/game/imagens/cenario4.jpg"));
		cenario4 = new JLabel(imgcenario1);
		cenario4.setBounds(0, 0, 1067, 600);

		imgcenario5 = new ImageIcon(getClass().getResource("/game/imagens/cenario5.jpg"));
		cenario5 = new JLabel(imgcenario1);
		cenario5.setBounds(0, 0, 1067, 600);

		timerField = new JLabel("100");
		timerField.setBounds(750, 10, 30, 30);
		timerField.setForeground(Color.WHITE);
		timerField.setFont(new Font(newFont.getName(), newFont.getStyle(), 20));

		pane = new JPanel(null);
		pane.add(timerField);

		pane.add(label);
		pane.add(cenario1);

		new PlayGame().start();

		return pane;

	}

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

		startbd.setBounds(250, 500, 100, 35);
		mainMenu.setBounds(20, 20, 100, 35);

		new AnimatedTextLabel(label, "Enter your nick name: ").start();

		pane = new JPanel(null);
		pane.add(textField);
		pane.add(startbd);
		pane.add(btdelete);
		pane.add(label);
		pane.add(tiro);
		pane.add(tag);
		pane.add(mainMenu);
		pane.add(fundonickname);

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

		imgfundoguns = new ImageIcon(getClass().getResource("/game/imagens/fundoguns.gif"));
		fundoguns = new JLabel(imgfundoguns);
		fundoguns.setBounds(0, 0, 800, 600);

		mainMenu.setBounds(20, 20, 100, 35);
		pane.add(mainMenu);

		namearea = new ImageIcon(getClass().getResource("/game/imagens/gunsmith.png"));
		label = new JLabel(namearea);
		label.setBounds(550, 0, 318, 100);
		pane.add(label);

		imgak47 = new ImageIcon(getClass().getResource("/game/imagens/ak47.gif"));
		ak47 = new JLabel("Ak-47", imgak47, JLabel.CENTER);
		ak47.setHorizontalTextPosition(JLabel.CENTER);
		ak47.setVerticalTextPosition(JLabel.BOTTOM);
		ak47.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		ak47.setForeground(Color.WHITE);
		ak47.setBounds(80, 240, 200, 250);
		pane.add(ak47);

		imgawp = new ImageIcon(getClass().getResource("/game/imagens/awp.gif"));
		awp = new JLabel("AWP", imgawp, JLabel.CENTER);
		awp.setHorizontalTextPosition(JLabel.CENTER);
		awp.setVerticalTextPosition(JLabel.BOTTOM);
		awp.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		awp.setForeground(Color.WHITE);
		awp.setBounds(250, 0, 200, 250);
		pane.add(awp);

		imgdeagle = new ImageIcon(getClass().getResource("/game/imagens/deagle.gif"));
		deagle = new JLabel("Desert Eagle", imgdeagle, JLabel.CENTER);
		deagle.setHorizontalTextPosition(JLabel.CENTER);
		deagle.setVerticalTextPosition(JLabel.BOTTOM);
		deagle.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		deagle.setForeground(Color.WHITE);
		deagle.setBounds(320, 380, 200, 250);
		pane.add(deagle);

		imgshotgun = new ImageIcon(getClass().getResource("/game/imagens/shotgun.gif"));
		shotgun = new JLabel("Shotgun", imgshotgun, JLabel.CENTER);
		shotgun.setHorizontalTextPosition(JLabel.CENTER);
		shotgun.setVerticalTextPosition(JLabel.BOTTOM);
		shotgun.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		shotgun.setForeground(Color.WHITE);
		shotgun.setBounds(420, 150, 200, 250);
		pane.add(shotgun);

		imgblacktail = new ImageIcon(getClass().getResource("/game/imagens/blacktail.gif"));
		blacktail = new JLabel("Blacktail", imgblacktail, JLabel.CENTER);
		blacktail.setHorizontalTextPosition(JLabel.CENTER);
		blacktail.setVerticalTextPosition(JLabel.BOTTOM);
		blacktail.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		blacktail.setForeground(Color.WHITE);
		blacktail.setBounds(630, 300, 200, 250);
		pane.add(blacktail);

		imgm4 = new ImageIcon(getClass().getResource("/game/imagens/m4.gif"));
		m4 = new JLabel("M4", imgm4, JLabel.CENTER);
		m4.setHorizontalTextPosition(JLabel.CENTER);
		m4.setVerticalTextPosition(JLabel.BOTTOM);
		m4.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		m4.setForeground(Color.WHITE);
		m4.setBounds(0, 30, 200, 250);
		pane.add(m4);

		imgmp5 = new ImageIcon(getClass().getResource("/game/imagens/mp5.gif"));
		mp5 = new JLabel("MP5", imgmp5, JLabel.CENTER);
		mp5.setHorizontalTextPosition(JLabel.CENTER);
		mp5.setVerticalTextPosition(JLabel.BOTTOM);
		mp5.setFont(new Font(newFont.getName(), newFont.getStyle(), 30));
		mp5.setForeground(Color.WHITE);
		mp5.setBounds(580, 30, 200, 250);
		pane.add(mp5);

		pane.add(fundoguns);

		return pane;

	}

	public JPanel levels() {

		pane = new JPanel(null);

		namearea = new ImageIcon(getClass().getResource("/game/imagens/phases.png"));
		label = new JLabel(namearea);
		label.setBounds(550, 0, 318, 100);
		pane.add(label);

		imgfundolevels = new ImageIcon(getClass().getResource("/game/imagens/fundolevels.gif"));
		fundolevels = new JLabel(imgfundolevels);
		fundolevels.setBounds(0, 0, 800, 600);

		imgcenario = new ImageIcon(getClass().getResource("/game/imagens/cenario1.jpg"));

		mainMenu.setBounds(20, 20, 100, 35);
		pane.add(mainMenu);

		icocenario1 = new ImageIcon(getClass().getResource("/game/imagens/ico1.jpg"));
		fase1 = new JLabel(icocenario1);
		fase1.setBounds(80, 300, 150, 100);
		pane.add(fase1);

		icocenario2 = new ImageIcon(getClass().getResource("/game/imagens/ico2.jpg"));
		fase2 = new JLabel(icocenario2);
		fase2.setBounds(320, 300, 150, 100);
		pane.add(fase2);

		icocenario3 = new ImageIcon(getClass().getResource("/game/imagens/ico3.jpg"));
		fase3 = new JLabel(icocenario3);
		fase3.setBounds(560, 300, 150, 100);
		pane.add(fase3);

		icocenario4 = new ImageIcon(getClass().getResource("/game/imagens/ico4.jpg"));
		fase4 = new JLabel(icocenario4);
		fase4.setBounds(200, 450, 150, 100);
		pane.add(fase4);

		icocenario5 = new ImageIcon(getClass().getResource("/game/imagens/ico5.jpg"));
		fase5 = new JLabel(icocenario5);
		fase5.setBounds(450, 450, 150, 100);
		pane.add(fase5);

		pane.add(fundolevels);
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

	public void startCountDown() {

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				timerField.setText("" + setInterval(timer));

				try {

					Thread.sleep(200);

					if (Integer.parseInt(timerField.getText()) == 0) {

						r = new Rank();
						if (!r.conexao.getConnection()) {
							JOptionPane.showMessageDialog(null, "falha na conexao");
							System.exit(0);
						}

						System.out.println("pontuacao atualizada!: " + name + score);

						r.dados.setScore(score);
						r.dados.setNickname(name);

						JOptionPane.showMessageDialog(null, r.atualizar(Rank.ALTERACAO));

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

		if (Integer.parseInt(timerField.getText()) == 1)
			timer.cancel();

		return Integer.parseInt(timerField.getText()) - 1;

	}

	public class PlayGame extends Thread implements MouseListener {

		int x = 500, y = 374, randX = 1, randY = 1;

		public void run() {

			startCountDown();
			label.addMouseListener(this);
			pane.addMouseListener(this);

			try {
				while (true) {

					for (int i = 0; i <= 300; i++) {

						label.setBounds(x, y, 300, 300);
						pane.add(label);
						pane.add(cenario1);
						Thread.sleep(difficulty);

						if (timerField.getText().equals("0"))
							break;

						if (i <= 150) {

							if (i == 150) {
								randX = (int) (Math.random() * 5) + 1;
								randY = (int) (Math.random() * 5) + 1;
							}

							if (x <= 0 || x >= 800 || y <= 0 || y >= 600) {
								x = (int) (Math.random() * 400);
								y = (int) (Math.random() * 300);
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
							x = (int) (Math.random() * 400);
							y = (int) (Math.random() * 300);
							randY *= -1;
							if (randX < 0) {
								randX *= -1;
							}
						}

						x -= randX;
						y -= randY;
					}

					if (timerField.getText().equals("0"))

						break;
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
