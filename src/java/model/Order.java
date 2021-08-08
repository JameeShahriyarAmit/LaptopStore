package model;
import java.io.Serializable;
/**
 *
 * @author shahr
 */
public class Order implements Serializable{
    private String name;
    private double price;

    public Order(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public Order() {
        name="";
        price=0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
    
}