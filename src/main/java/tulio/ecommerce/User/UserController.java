package tulio.ecommerce.User;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        this.userRepository.save(userModel);
        return ResponseEntity.ok().body("Cadastrado com sucesso!");
    }

    @PostMapping("/login")
    public String login(@RequestHeader("Authorization") String authString)
    {
        authString =  authString.substring("Basic".length()).trim();
        String decodedauth = new String(Base64.getDecoder().decode(authString));
        String[] credential = decodedauth.split(":");

        return credential[0] + " " + credential[1];
        
    }
}
