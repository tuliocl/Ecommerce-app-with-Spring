package tulio.ecommerce.Models.Cart;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartProductRequest {
    private UUID cartID;
    private UUID itemID;


}