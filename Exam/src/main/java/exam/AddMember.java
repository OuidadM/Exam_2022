package exam;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class AddMember {
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneField;
    @FXML
    public  void addMember(){
        Membre membre=new Membre(nomField.getText(),prenomField.getText(),email.getText(),phoneField.getText());
        MembreDaoImpl memberDao=new MembreDaoImpl();
        memberDao.insere(membre);
    }

}
