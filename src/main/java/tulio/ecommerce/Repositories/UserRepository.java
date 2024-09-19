package tulio.ecommerce.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tulio.ecommerce.Models.User.UserModel;

public interface UserRepository extends JpaRepository<UserModel,UUID> {
    @Query("SELECT p FROM TB_User p WHERE p.login LIKE %:login%")
    UserModel findbylogin(@Param("login") String nome);
    
} 

