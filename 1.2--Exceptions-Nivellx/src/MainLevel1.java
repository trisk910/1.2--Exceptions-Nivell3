import Level1.Presentation.MainMenu;
import Level1.Presentation.UIController;

public class MainLevel1 {
    public static void main(String[] args) {

        MainMenu menu = new MainMenu();
        UIController controller = new UIController(menu);
        controller.startProductManagement();
    }
}