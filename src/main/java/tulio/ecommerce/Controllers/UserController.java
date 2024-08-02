package tulio.ecommerce.Controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tulio.ecommerce.Repositories.UserRepository;
import tulio.ecommerce.User.UserModel;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserRepository userRepository;
    
    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrar(@RequestBody UserModel userModel)
    {
        if(userModel.password == null || userModel.login == null)
        {
            return ResponseEntity.badRequest().body("Ausencia de dados");
        
        }
        
        //verify if login name already exists
        if(userRepository.findbylogin(userModel.login) != null)
        {
            return ResponseEntity.badRequest().body("Login já existente");
        }

        this.userRepository.save(userModel);
        return ResponseEntity.ok().body("Cadastrado com sucesso!");
    }
}
