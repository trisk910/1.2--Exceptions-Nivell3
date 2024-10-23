package Level1.Presentation;

import Level1.Bussiness.Collection;
import Level1.Bussiness.Product;
import Level1.Bussiness.Sell;
import Level1.Persistance.SellException;

import java.util.ArrayList;
import java.util.Scanner;

public class UIController {

    private MainMenu mainMenu;

    public UIController(MainMenu mainMenu) {
        this.mainMenu = new MainMenu();
    }

    public void startProductManagement() {
        ArrayList<Collection> collectionArrayList = new ArrayList<>();

        collectionArrayList.add(new Collection("Collection1"));
        System.out.print("Catch Exception:");
        try{
            collectionArrayList.get(1);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        collectionArrayList.remove(0);

        Scanner scnMenu = new Scanner(System.in);
        int menuOption  = 0;
        while(menuOption !=5) {
            mainMenu.ShowMenu();
            try {
                System.out.print("\nOption: ");
                menuOption = Integer.parseInt(scnMenu.nextLine());
                if (menuOption < 1 || menuOption > 5)
                    System.out.println("Menu not avaliable\n");
                else {
                    switch (menuOption){
                        case 1:
                            createCollection(collectionArrayList);
                            break;
                        case 2:
                            addProduct(collectionArrayList);
                            break;
                        case 3:
                            sellProducts(collectionArrayList);
                            break;
                        case 4:
                            showCollection(collectionArrayList);
                            break;
                        default:
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number\n");
            }
        }
    }

    private void createCollection(ArrayList<Collection> collections) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the collection name: ");
        String collectionName = scn.nextLine();

        collections.add(new Collection(collectionName));
    }
    private void addProduct(ArrayList<Collection> collections) {
        Scanner scn = new Scanner(System.in);
        if (collections.isEmpty()) {
            System.out.println("No s'ha trobat cap colleccio\n");
        } else {
            System.out.print("Introdueix el nom de la collecio: ");
            String collectionName = scn.nextLine();
            boolean collectionFound = false;
            for (Collection collection : collections) {
                if (collection.getCollectionName().equals(collectionName)) {
                    collectionFound = true;
                    System.out.print("Introdueix el nom del producte: ");
                    String productName = scn.nextLine();

                    boolean validPrice = false;
                    while (!validPrice){
                        try {
                            System.out.print("Introdueix el preu del producte: ");
                            Scanner  scnPrice = new Scanner(System.in);
                            double productPrice = Double.parseDouble(scnPrice.nextLine());
                            collection.addProduct(new Product(productName, productPrice));
                            validPrice = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Introdueix un preu valid\n");
                        }
                    }
                    if(validPrice){
                        System.out.println("Producte afegit correctament\n");
                    }
                }
            }
            if (!collectionFound) {
                System.out.println("No s'ha trobat la colleccio\n");
            }
        }
    }
    private void sellProducts(ArrayList<Collection> collections) {
        Scanner scn = new Scanner(System.in);
        if (collections.isEmpty()) {
            System.out.println("No s'ha trobat cap col·lecció\n");
        } else {
            System.out.print("Introdueix el nom de la colleccio: ");
            String collectionName = scn.nextLine();
            Collection collectionToSell = null;
            for (Collection collection : collections) {
                if (collection.getCollectionName().equals(collectionName)) {
                    collectionToSell = collection;
                    break;
                }
            }
            if (collectionToSell == null) {
                System.out.println("No s'ha trobat la col·lecció\n");
            } else {
                try {
                    Sell sell = new Sell();
                    sell.calculateTotal(collectionToSell.getProducts());
                    System.out.println("Preu total de la coleccio: " + sell.getTotalPrice()+"€");
                    collections.remove(collectionToSell);
                    System.out.println("Col·lecció venuda correctament\n");
                } catch (SellException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            }
        }
    }
    private void showCollection(ArrayList<Collection> collections){
        if (collections.isEmpty()) {
            System.out.println("No s'ha trobat cap col·lecció\n");
        } else {
            for (Collection collection : collections) {
                System.out.println("Col·lecció: " + collection.getCollectionName());
                for (Product product : collection.getProducts()) {
                    System.out.println("Producte: " + product.getName() + " Preu: " + product.getPrice());
                }
            }
        }
    }
}

