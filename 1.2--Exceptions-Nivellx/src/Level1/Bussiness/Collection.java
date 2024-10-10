package Level1.Bussiness;

import java.util.ArrayList;
import java.util.List;

public class Collection {

    private ArrayList<Product> products;
    private final String collectionName;

    public Collection(String collectionName) {
        this.collectionName = collectionName;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
