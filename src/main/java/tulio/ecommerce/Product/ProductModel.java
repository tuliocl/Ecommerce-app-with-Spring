package tulio.ecommerce.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "TB_Product")
public class ProductModel {

    @Id
    String id;
    String title;
    double price;
    String category;
    double rate;
    int count;
    
}
