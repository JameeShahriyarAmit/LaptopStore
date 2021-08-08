
package model;
import java.io.Serializable;
/**
 *
 * @author shahr
 */
public class Product implements Serializable {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;

    public Product() {
        id = 0;
        name = "";
        description = "";
        price = 0.0;
        image = "";
    }

    public Product(int id, String name, String description, double price, String image) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.price = price;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
