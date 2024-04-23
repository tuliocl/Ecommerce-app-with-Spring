package tulio.ecommerce.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity(name = "TB_User")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    UUID id;
    
    String login;
    
    String password;
    
    @Column(columnDefinition = "double default 0.0")
    double limite;
    
    @Column(columnDefinition = "double default 0.0")
    double saldo;
}