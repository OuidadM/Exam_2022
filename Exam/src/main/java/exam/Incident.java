package exam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
public class Incident {
    private String reference;
    private LocalDateTime time;
    private String status;
    private Membre membre;
    public Incident(String reference, String status, Membre membre) {
        this.reference = reference;
        this.time=LocalDateTime.now();
        this.status=status;
        this.membre=membre;
    }

}
