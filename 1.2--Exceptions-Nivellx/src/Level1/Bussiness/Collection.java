package Level1.Bussiness;

import java.util.ArrayList;
import java.util.List;

public class Collection {

    private ArrayList<Product> products;
    private final String collectionName;

    public Collection(String collectionName) {
        this.collectionName = collectionName;
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
