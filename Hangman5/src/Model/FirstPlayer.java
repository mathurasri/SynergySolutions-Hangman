package Model;

public class FirstPlayer extends Player {
	
	private String secretword;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String inputName) {
		name = inputName;
	}
	public String getSecretword() {
		return secretword;
	}
	public void setSecretword(String secretword) {
		this.secretword = secretword;
	}
	public FirstPlayer() {
		// TODO Auto-generated constructor stub
	}

}
