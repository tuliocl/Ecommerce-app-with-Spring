package tulio.ecommerce.Models.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity(name = "TB_cart")
public class CartModel {
    @Id
    @GeneratedValue(generator = "UUID")
    UUID id;

    @ElementCollection
    @CollectionTable(name = "TB_cart_items", joinColumns = @JoinColumn(name = "cart_id"))
    private List<UUID> items = new ArrayList<>();
}
