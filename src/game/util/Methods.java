package game.util;

public class Methods {
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void pressEnterToContinue() {
    System.out.println("Premi INVIO per continuare...");
    Constants.scanner.nextLine();
  }

  public static int readNumber(int min, int max) {
    while (true) {
      try {
        String input = Constants.scanner.nextLine().trim();
        int n = Integer.parseInt(input);

        if (n >= min && n <= max)
          return n;

        System.out.printf("Pick a number between %d and %d \n", min, max);
      } catch (NumberFormatException e) {
        System.out.println("Invalid parameter, insert a number.\n");
      }
    }
  }
}
