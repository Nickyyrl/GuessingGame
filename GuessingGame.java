import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

public static void main(String[] args) {
    game();
}

public static void game() {
    int[] result = startGame(); //Cette partie de code permet de récupérer les valeurs de départ pour le jeu. 
                                //Les valeurs sont générées aléatoirement grâce à la méthode startGame(). Ensuite, les variables a, b, c, d, S, centaines, dizaines, unites sont utilisées pour stocker les valeurs récupérées dans le tableau result. 
                                //Ces valeurs sont nécessaires pour les différents calculs effectués dans le jeu.
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
//Liste d'indice pour piocher des indices differents lors du jeu

    int essais = 1; // demarrage du compteur d'essais
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
    //Proposition pour quitter le jeu 
    System.out.println("Voulez-vous rejouer ? O / N");
    Scanner scanner = new Scanner(System.in);
    String rejouer = scanner.nextLine();
    if (rejouer.equalsIgnoreCase("O")) {
        game();
    }
}//fin de class

public static int[] startGame() {
    Random rand = new Random();
    int[] nbr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int a = rand.nextInt(nbr.length) + 1;
    int b = rand.nextInt(nbr.length) + 1;
    int c = rand.nextInt(nbr.length) + 1;
//generation des chiffres de 1 a 10 inclus, avec la classe random et l'operation nextInt() pour choisir un index aleatoire dans la liste
    if (a == 10 || b == 10 || c == 10) {
        a = 9;
    }
//Calcul sur les chiffres random en utilisant le modulo pour et la division euclidienne
    int d = a * b * c;

    int centaines = d / 100;
    int reste = d % 100;
    int dizaines = reste / 10;
    int unites = reste % 10;
//calcul de la somme 
    int S = centaines + dizaines + unites;
//Debut du jeu
    System.out.printf("Le nombre mystère d est un multiple de A : %d et de B : %d;\nLeur somme est S : %d\nLe nombre a 3 chiffres, devinez-le !\n", a, b, S);
    return new int[]{a, b, c, d, S, centaines, dizaines, unites};
}// fin de class

public static int[] indice(int c, int d, int u) {
    int hint1 = (c + d) * u;
    int hint2 = (c - d) * u;
    int[] hintList = new int[]{hint1, hint2};
    Random rand = new Random();
    int hint = hintList[rand.nextInt(hintList.length)];
    int hintIndex = hint == hint1 ? 0 : 1;
    return new int[]{hintList[hintIndex], hint};
}//c -> centaines, d -> dizaines, u -> unites, fin de class
}//fin de main

//Explications du code global : 
//La ligne 3 importe les classes Random et Scanner qui sont nécessaires pour le programme.
//La méthode main() à la ligne 5 appelle la méthode game() pour lancer le jeu.
//La méthode game() à partir de la ligne 7 est la méthode principale qui gère le jeu. 
//Elle appelle la méthode startGame() pour générer le nombre mystère, puis demande à l'utilisateur de deviner le nombre. 
//Si l'utilisateur trouve le nombre, le jeu se termine et l'utilisateur gagne. 
//Sinon, le programme affiche un indice pour aider l'utilisateur et continue jusqu'à ce que l'utilisateur ait utilisé tous ses 6 essais. 
//S'il n'a pas réussi à deviner le nombre après 6 essais, il perd et le jeu se termine.
//La méthode startGame() à partir de la ligne 43 génère le nombre mystère en choisissant aléatoirement trois nombres entre 1 et 9, 
//multiplie ces nombres pour obtenir un nombre à trois chiffres, puis calcule la somme de ces chiffres. 
//Enfin, elle affiche un message pour indiquer à l'utilisateur que le jeu a commencé et renvoie un tableau contenant les différents paramètres générés.
//La méthode indice() à partir de la ligne 62 génère deux indices pour aider l'utilisateur à trouver le nombre mystère.
//Ces indices sont calculés en utilisant les chiffres centaines, dizaines et unités du nombre mystère et sont stockés dans un tableau. 
//Un indice est choisi au hasard parmi les deux indices générés et renvoyé.


//Fix a faire :
// La Class indice n'est pas totalement fonctionnelle


