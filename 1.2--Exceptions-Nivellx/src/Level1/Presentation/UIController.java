package Level1.Presentation;

import Level1.Bussiness.Collection;
import Level1.Bussiness.Product;

import java.util.ArrayList;
import java.util.Scanner;

public class UIController {

    private MainMenu mainMenu;

    public UIController(MainMenu mainMenu) {
        this.mainMenu = new MainMenu();
    }

    public void StartProductManagement() {
        ArrayList<Collection> collectionArrayList = new ArrayList<>();

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

                            break;
                        case 4:

                            break;
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
                            Scanner  scnPrice = new Scanner(System.in);
                            double productPrice = Double.parseDouble(scnPrice.nextLine());
                            System.out.println("Introdueix el preu del producte: ");
                            collection.addProduct(new Product(productName, productPrice));
                            validPrice = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Introdueix un preu valid\n");
                        }
                    }
                }
            }
            if (!collectionFound) {
                System.out.println("No s'ha trobat la colleccio\n");
            }
        }
    }
}
