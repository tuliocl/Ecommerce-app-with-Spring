package tulio.ecommerce.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tulio.ecommerce.Repositories.UserRepository;
import tulio.ecommerce.User.UserModel;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager; 
    
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
            System.out.println("TESTE");
            return ResponseEntity.badRequest().body("Login já existente");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userModel.password);
        userModel.password = encryptedPassword;

        this.userRepository.save(userModel);
        return ResponseEntity.ok().body("Cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserModel usermodel)
    {
        var UsernamePassword = new UsernamePasswordAuthenticationToken(usermodel.login, usermodel.password);
        var auth = authenticationManager.authenticate(UsernamePassword);

        return ResponseEntity.ok().body("Login feito");
    }
}
