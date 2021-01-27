import ui.Game;


/**
 * This class is only meant for testing the api in java. It should not be used outside of development.
 */
public class Main {
	public static void main(String[] args) {
		var game = new Game();
		game.startGui();

		var msg = game.buttonInput("Choose a greeting", "Hej", "Olloh");
		System.out.println(msg);
	}
}
