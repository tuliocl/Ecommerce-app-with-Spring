package tulio.ecommerce.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tulio.ecommerce.Models.Cart.CartDTO;
import tulio.ecommerce.Models.Cart.CartProductRequest;
import tulio.ecommerce.Models.Products.ProductModel;
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
    public ResponseEntity<String> AddItemToCart(@RequestBody CartProductRequest request,@RequestHeader(HttpHeaders.AUTHORIZATION) String token)
    {
        System.out.println(token);
        HttpStatus response = cartService.AddItemLogic(request.getCartID(),request.getItemID(),token);

        if(response != HttpStatus.OK)
        {
            return ResponseEntity.badRequest().body("Falha ao adicionar");
        }
        
        return ResponseEntity.ok().body("Item adicionado");
    }

    @GetMapping("/list/")
    public List<ProductModel> ListCartItens(@RequestBody CartDTO request)
    {
        List<ProductModel> products = cartService.ListCartLogic(request.getId());
        return products;
    }
}