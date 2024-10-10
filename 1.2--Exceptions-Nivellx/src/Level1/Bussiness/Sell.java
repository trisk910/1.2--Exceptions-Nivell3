package Level1.Bussiness;

import Level1.Persistance.SellException;

import java.util.Collection;

public class Sell {

    private double totalPrice;

    public void calculateTotal(Collection<Product> collection) throws SellException {
        if (collection.isEmpty()) {
            throw new SellException("Per fer una venda primer has d’afegir productes");
        }

        totalPrice = 0;
        for (Product product : collection) {
            totalPrice += product.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
