package game.util;

import java.util.Scanner;

public class Methods {
  private static final Scanner scanner = new Scanner(System.in);

  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void pressEnterToContinue() {
    System.out.println("Press ENTER to continue...");
    scanner.nextLine();
  }

  public static String readString() {
    return scanner.nextLine().trim();
  }

  public static void closeInput() {
    scanner.close();
  }

  public static int readNumber(int min, int max) {
    while (true) {
      try {
        String input = scanner.nextLine().trim();
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
