import java.util.Set;

public interface MembreDao {
    public void insere(Membre m);
    public Set<Incident> chargerListIncidents();
}
