package tulio.ecommerce.Models.Products;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity(name = "TB_product")
public class ProductModel {

    @Id
    @GeneratedValue(generator = "UUID")
    UUID id;
    
    @Column
    String name;

    @Column(columnDefinition = "int default 0")
    int inventory;

    @Column(columnDefinition = "int default 0")
    int totalSold;

    @Column(columnDefinition = "double default 0.0")
    double price;
}
