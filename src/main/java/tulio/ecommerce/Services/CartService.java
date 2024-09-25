package tulio.ecommerce.Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tulio.ecommerce.Models.Cart.CartModel;
import tulio.ecommerce.Models.Products.ProductModel;
import tulio.ecommerce.Models.User.UserModel;
import tulio.ecommerce.Repositories.CartRepository;
import tulio.ecommerce.Repositories.ProductRepository;
import tulio.ecommerce.Repositories.UserRepository;

@Service
public class CartService {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public HttpStatus AddItemLogic(UUID cartID, UUID itemID,String token)
    {
        token = token.substring(7);
        String UserNameSender = tokenService.validateToken(token);

        Optional<UserModel> userOpt = userRepository.findById(UserNameSender);
        Optional<CartModel> cartOpt = cartRepository.findById(cartID);
        Optional<ProductModel> prodOpt = productRepository.findById(itemID);

        if(cartOpt.isEmpty() || prodOpt.isEmpty())
        {
            return HttpStatus.NOT_FOUND;
        }

        UserModel user = userOpt.get();
        CartModel cart = cartOpt.get();

        if(!user.cart.getId().equals(cartID))
        {
            return HttpStatus.NOT_FOUND;
        }

        cart.getItems().add(itemID);
        cartRepository.save(cart);

        return HttpStatus.OK;
    }

    public List<ProductModel> ListCartLogic(UUID id)
    {
        Optional<CartModel> cartOpt = cartRepository.findById(id);

        if (cartOpt.isEmpty()) {
            return Collections.emptyList();
        }

        CartModel cart = cartOpt.get();
        List<UUID> productIds = cart.getItems();
        
        List<ProductModel> products =  new ArrayList<ProductModel>();
        for(UUID item : productIds)
        {
            Optional<ProductModel> prodcutOpt = productRepository.findById(item);
            if(!prodcutOpt.isEmpty())
            {
                ProductModel existingProduct = prodcutOpt.get();
                products.add(existingProduct);
            }
            
        }

        return products;
    }
}
