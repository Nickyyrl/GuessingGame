import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

public static void main(String[] args) {
    game();
}

public static void game() {
    int[] result = startGame();
    int a = result[0];
    int b = result[1];
    int c = result[2];
    int d = result[3];
    int S = result[4];
    int centaines = result[5];
    int dizaines = result[6];
    int unites = result[7];

    int[] hints = indice(centaines, dizaines, unites);
    int hint2 = hints[0];
    int hint = hints[1];

    int essais = 1;
    for (int i = 0; i < 6; i++) {
        System.out.printf("Le jeu vous écoute, Essai n° %d / 5 : ", essais);
        Scanner scanner = new Scanner(System.in);
        int guess = scanner.nextInt();

        if (guess == d) {
            System.out.println("Bravo !");
            return;
        } else {
            essais += 1;
            if (essais == 2) {
                System.out.printf("INDICE : %d\n", hint);
            } else if (essais == 4) {
                System.out.printf("INDICE : %d\n", hint2);
            } else if (essais == 6) {
                System.out.println("GAME OVER | Le nombre etait : " + d);
                return;
            }
        }
    }
    System.out.println("Voulez-vous rejouer ? O / N");
    Scanner scanner = new Scanner(System.in);
    String rejouer = scanner.nextLine();
    if (rejouer.equalsIgnoreCase("O")) {
        game();
    }
}

public static int[] startGame() {
    Random rand = new Random();
    int[] nbr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int a = rand.nextInt(nbr.length) + 1;
    int b = rand.nextInt(nbr.length) + 1;
    int c = rand.nextInt(nbr.length) + 1;

    if (a == 10 || b == 10 || c == 10) {
        a = 9;
    }

    int d = a * b * c;

    int centaines = d / 100;
    int reste = d % 100;
    int dizaines = reste / 10;
    int unites = reste % 10;

    int S = centaines + dizaines + unites;

    System.out.printf("Le nombre mystère d est un multiple de A : %d et de B : %d;\nLeur somme est S : %d\nLe nombre a 3 chiffres, devinez-le !\n", a, b, S);
    return new int[]{a, b, c, d, S, centaines, dizaines, unites};
}

public static int[] indice(int c, int d, int u) {
    int hint1 = (c + d) * u;
    int hint2 = (c - d) * u;
    int[] hintList = new int[]{hint1, hint2};
    Random rand = new Random();
    int hint = hintList[rand.nextInt(hintList.length)];
    int hintIndex = hint == hint1 ? 0 : 1;
    return new int[]{hintList[hintIndex], hint};
}
}

