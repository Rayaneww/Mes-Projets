import java.util.Scanner;

public class Dev1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);  
    String[][] adherents = {
            {"2025", "4", "Dany Boon"},
            {"2024", "12", "Omar sy"},
            {"2024", "11", "Killan Mbappe"},
            {"2025", "2", "Olivier Giroud"},
            {"2025", "8", "Antoine Griezmann"},
            {"2024", "6", "Zinedine Zidane"},
            {"2024", "5", "Jean Dujardin"}
    };

    //System.out.println(adherents[2][1]); // verrifier [ligne][colonne]
    
    int mois;
    System.out.println("Entrez le mois courant");
    mois = scanner.nextInt();  
    
    int anneeEnCours;
    System.out.println("Entrez l'année courante");
    anneeEnCours = scanner.nextInt();  

    while (true) {
    int choix;
    System.out.println("1-Afficher tous les adhérents (à jour ou non)");
    System.out.println("2-Afficher les adhérents à jour");
    System.out.println("3-Afficher les adhésions échues");
    System.out.println("4-Ajouter ou Renouveler un adhérent");
    System.out.println("5-Quitter");
    System.out.print("Votre choix: ");
    choix = scanner.nextInt();  
    
    while (choix < 1 || choix > 5) {
        System.out.print("Choix incorrect. ");
        System.out.print("Votre choix: ");
        choix = scanner.nextInt();  
    }

    if (choix == 1) {
        afficherTableau(adherents);
    } else if (choix == 2) {
        afficheradherentsajour(adherents, anneeEnCours);
    } else if (choix == 3) {
        afficherAdherentsEchue(adherents, anneeEnCours);
    } else if (choix == 4) {
        System.out.println("Donner le prénom suivi du nom: ");
        scanner.nextLine();  
        adherents = ajouteradherents(adherents, mois, anneeEnCours, scanner);
        System.out.println("\nTableau après ajout de l'adhérent");
        afficherTableau(adherents);
    } else if (choix == 5) {
        System.err.println("Au revoir");
        break;
    }
    System.out.println("-------------------------------------------------------------");
   }
  }

  public static void afficherTableau(String[][] adherents) {
    for (int i = 0; i < adherents.length; i++) {
        System.out.println("Nom de l'adherent : " + adherents[i][2] + ", Date d'adhesion: " + adherents[i][1] + "/" + adherents[i][0]);
    }
  }

  public static String[][] ajouteradherents(String[][] adherents, int mois, int anneeEnCours, Scanner scanner) {
    System.out.println("Ajouter un nouvel adhérent");
    String nouvelleLigneInput = scanner.nextLine();  
    String[] elementsNouvelleLigne = nouvelleLigneInput.split(" ");
    
    while (elementsNouvelleLigne.length != 2) {
        System.out.println("Donner seulement le nom et le prénom (exemple: Jean Dupond) : ");
        nouvelleLigneInput = scanner.nextLine();  
        elementsNouvelleLigne = nouvelleLigneInput.split(" ");
    }
    
    boolean renouvellement = false;
    for (int i = 0; i < adherents.length; i++) {
        if (adherents[i][2].equals(nouvelleLigneInput)) {
            renouvellement = true;
            adherents[i][0] = String.valueOf(anneeEnCours);
            adherents[i][1] = String.valueOf(mois);
        }
    }
    
    if (!renouvellement) {
        String[][] nouveauTableau = new String[adherents.length + 1][adherents[0].length];
        for (int i = 0; i < adherents.length; i++) {
            for (int j = 0; j < adherents[i].length; j++) {
                nouveauTableau[i][j] = adherents[i][j];
            }
        }
        
        nouveauTableau[adherents.length][0] = String.valueOf(anneeEnCours);
        nouveauTableau[adherents.length][1] = String.valueOf(mois);
        nouveauTableau[adherents.length][2] = nouvelleLigneInput;
        
        return nouveauTableau;
    }

    return adherents;
  }

  public static void afficheradherentsajour(String[][] adherents, int anneeEnCours) {
    System.out.println("Les adhérents à jour sont: ");
    for (int i = 0; i < adherents.length; i++) {
        if (String.valueOf(anneeEnCours).equals(adherents[i][0])) {
            System.out.println("Nom de l'adherent : " + adherents[i][2] + ", Date d'adhesion: " + adherents[i][1] + "/" + adherents[i][0]);
        } else if (String.valueOf(anneeEnCours - 1).equals(adherents[i][0]) && adherents[i][1].equals("12")) {
            System.out.println("Nom de l'adherent : " + adherents[i][2] + ", Date d'adhesion: " + adherents[i][1] + "/" + adherents[i][0]);
        }
    }
  }

  public static void afficherAdherentsEchue(String[][] adherents, int anneeEnCours) {
    System.out.println("Les adhérents échus: ");
    for (int i = 0; i < adherents.length; i++) {
        if ((String.valueOf(anneeEnCours - 1).equals(adherents[i][0]) && Integer.valueOf(adherents[i][1]) < 12)) {
            System.out.println("Nom de l'adherent : " + adherents[i][2] + ", Date d'adhesion: " + adherents[i][1] + "/" + adherents[i][0]);
        } else if (anneeEnCours > Integer.valueOf(adherents[i][0])) {
            System.out.println("Nom de l'adherent : " + adherents[i][2] + ", Date d'adhesion: " + adherents[i][1] + "/" + adherents[i][0]);
        }
    }
  }
 
}

