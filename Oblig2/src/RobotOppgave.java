import java.util.Scanner;
// Klasse for å representere omgivelsene
class Verden {
    boolean regner;
    int dag; // 1 er Mandag, 2 er Tirsdag, ..., 7 for Søndag, 8 er Mandag...

    // endre metoden for å returnere true hvis det er søndag (hint: Modulo %)
    public boolean erSondag() {
        if (dag % 7 == 0){
            return true;
        }
        return false;
    }

    public boolean erMandag() {
        if (dag % 8 == 0){
            return true;
        }
        return false;
    }
}
class Robot {
    // gi robotten 4 attributter: navn, batteriNivaa, avstandTilParken og botType
    String name;
    Double batteryLevel;
    int distanceToPark;
    String botType;

    // ENdre metoden slik at den retunerer en tekst streng som forklarer statusen til roboten
    // eksempel: Dette er bot Dancatron 4000 av type B-Bot.
    // Denne enheten har 80.0 batterikapasitet igjen og bor 1500 meter fra parken.
    public String giStatus(){
        return "Dette er en bot " + name + " av type " + botType + ". Denne enheten har " + batteryLevel + " batterikapasitet igjen og bor " + distanceToPark + " meter fra parken";
    }

    // Metode for å sjekke om roboten kan gå til parken basert på omgivelsene
    public boolean gaaTilParken(Verden verden) {
        System.out.println(name + " sjekker om det er mulig å gå til parken...");

        // Sjekk om det regner
        if (verden.regner){
            System.out.println("Roboten kan ikke gå ut siden det regner.");
            return false;
        }

        // Sjekk om det er søndag. Kan bare gå i parken på søndager.
        if(verden.erSondag()){
            System.out.println("Det er søndag så den kan gå i parken.");
        }else{
            System.out.println("Du er ikke søndag idag, du kan ikke gå i parken.");
        }

        // Sjekk batterinivå. En bot trenger 1% batterinivå per 100 meter for å gå til parken.
        if(batteryLevel * 100 < distanceToPark){
            System.out.println(name + " har ikke nok strøm for å komme seg til parken.");
        }else{
            System.out.println(name + " har nok strøm for å komme seg til parken." );
        }
        return true;
    }

    // lag en metode som sjekker om boten kan være med på danseklubben
    // bottypen må være av type B-Bot (hint .equals)
    // En bot trenger minimum 50% batterikapasitet for å være med
    // Det kan ikke være mandag. Da er danseklubben stengt.

    public boolean gaaTilDanseklubb(Verden verden, Robot bot) {
        if (verden.erMandag()) {
            System.out.println("Det er mandag idag, desverre er danseklubben stengt.");
        } else {
            if (bot.botType.equals("B-Bot")) {
                System.out.println("Du er av riktig type robott ;)");
                if (bot.batteryLevel >= 50) {
                    System.out.println("Ser ut som at du har nok strøm for å komme deg inni klubben også.");
                } else {
                    System.out.println("Batterikapasitetet ditt må minimum være 50%, du ligger på " + batteryLevel + ". Gå hjem og lad degselv.");
                }
            } else {
                System.out.println("Du er ike av riktig type robott >:(");
            }
        }
        return true;
    }
}

public class RobotOppgave {
    public static void main(String[] args) {
        // Oppretter en Verden-objekt (omgivelsene)
        Verden dagensVerden = new Verden();
        dagensVerden.regner = false;
        dagensVerden.dag = 8;

        Robot Spark = new Robot();
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hva heter robott nr.1? ");
        Spark.name = myObj.nextLine();
        Spark.batteryLevel = 85.0;
        Spark.distanceToPark = 2000;
        Spark.botType = "A-Bot";

        Robot Shark = new Robot();
        System.out.println("Hva heter robott nr.2? ");
        Shark.name = myObj.nextLine();
        Shark.batteryLevel = 25.0;
        Shark.distanceToPark = 3340;
        Shark.botType = "B-Bot";
        // Oppretter to Robot-objekter
        // med navn, f.eks. Dancatron 4000 og Spark-E
        // med botType "B-Bot" og "Toaster"
        // med forskjellig batterinivå
        // med forskjellig avstand til parken
        // print ut statusen til begge robottene

        // Sjekker om robotene kan gå til danseklubben
        // Sjekker om robotene kan gå til parken
        String status = Spark.giStatus();
        System.out.println(status);
        Spark.gaaTilParken(dagensVerden);
        Spark.gaaTilDanseklubb(dagensVerden, Spark);

        System.out.println("--------------------");

        String statusTwo = Shark.giStatus();
        System.out.println(statusTwo);
        Shark.gaaTilParken(dagensVerden);
        Shark.gaaTilDanseklubb(dagensVerden, Shark);
    }
}