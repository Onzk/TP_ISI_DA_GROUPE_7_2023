package ucao.isi.G6.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
@Entity
@Table(name="accounts")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;
    @Column(unique = true)
    private String number;
    @NotNull
    private String type;
    private double amount = 0;
    @NotNull
    private LocalDate creationDate = LocalDate.now();

}



