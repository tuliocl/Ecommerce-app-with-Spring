package tulio.ecommerce.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tulio.ecommerce.Models.Cart.AddItemRequest;
import tulio.ecommerce.Repositories.CartRepository;
import tulio.ecommerce.Repositories.ProductRepository;
import tulio.ecommerce.Services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> AddItemToCart(@RequestBody AddItemRequest request)
    {

        HttpStatus response = cartService.AddItemLogic(request.getCartID(),request.getItemID());

        if(response != HttpStatus.OK)
        {
            return ResponseEntity.badRequest().body("Falha ao adicionar");
        }
        
        return ResponseEntity.ok().body("Item adicionado");
    }

    //to do: GET all cart's itens
}