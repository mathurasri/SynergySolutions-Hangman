package Model;

public class FirstPlayer extends Player {
    private String secretword;

    public FirstPlayer()
    {
        this.setSecretword(null);
    }
     
    public String getSecretword() {
        return secretword;
    }
    public void setSecretword(String secretword) {
        this.secretword = secretword;
    }
}
