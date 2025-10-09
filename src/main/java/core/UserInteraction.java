package core;

import java.util.Scanner;

public class UserInteraction {
    private Scanner scanner;

    public UserInteraction() {
        scanner = new Scanner(System.in);
    }

    public int AskInteger(String message, int min, int max) {
        int valeur = -1;
        boolean valide = false;

        while (!valide) {
            System.out.print(message);
            try {
                valeur = Integer.parseInt(scanner.nextLine());
                if (valeur >= min && valeur <= max) {
                    valide = true;
                } else {
                    System.out.println("️ Entrez un nombre entre " + min + " et " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("️ Veuillez entrer un nombre valide !");
            }
        }
        return valeur;
    }

    public int ChooseGameMode() {
        System.out.println("=== Sélection du mode de jeu ===");
        System.out.println("1 :  Joueur vs Joueur");
        System.out.println("2 : Joueur vs IA");
        System.out.println("3 : IA vs IA");
        return AskInteger("Votre choix : ", 1, 3);
    }

    public void CloseScanner() {
        scanner.close();
    }
}
