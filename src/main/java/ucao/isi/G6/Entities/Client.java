package ucao.isi.G6.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;
    @NotNull
    private String gender;
    @NotNull
    private String address;
    @NotNull
    @Column(unique = true)
    private String tel;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String nationality;
    @NotNull
    private LocalDate birthDate;


}
