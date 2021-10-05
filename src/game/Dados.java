package game;
//"CLASSE TRANSPORTADORA"
public class Dados {
	private String name;
	int score;

	public String getNickname() {
		return name;
	}

	public void setNickname(String nickname) {
		this.name = nickname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int pontuacao) {
		this.score = pontuacao;
	}
}
