import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class TheGame
{
    protected Scanner sc = new Scanner(System.in);


// char stats
    String charTyp = "";
    String charName = "";
    int strength = 0;
    int dexterity = 0;
    int magic = 0;
    int armor = 10;
    int charHp = 0;
    int money = 0;
    int id = 1;

    // DB settings
    String createDb = "CREATE DATABASE IF NOT EXISTS char_s";
    String createTable = "CREATE TABLE IF NOT EXISTS characters" +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " charTyp VARCHAR(255), " +
                        " charName VARCHAR(255), " +
                        " charStrength INT(255), " +
                        " charDexterity INT(255), " +
                        " charMagic INT(255), " +
                        " charAmor INT(255), " +
                        " charHp INT(255), " +
                        " charMoney INT(255), " +
                        " PRIMARY KEY ( id ))";
    String updateTable = "INSERT INTO characters VALUES (" + id + ",'" + charTyp + "', '" + charName + "', " +
                                                            strength + ", " + dexterity + ", " + magic + ", " +
                                                            armor + ", " + charHp + ", " + money + ");";
// villager stats
    String littleGirl = "little girl";
    String oldMan = "old man";
    String oldWoman = "old woman";
    public boolean quest_01 = false;

    private void sleep(int sleepTimeInSec) throws InterruptedException
    {
        for (int i = 0; i < sleepTimeInSec; i++)
        {
            Thread.sleep(1000);
        }
    }

    public void theGame() throws Exception
    {
        System.out.println(Layout.startScreen);
        sleep(2);
        startGame();
    }
    private void startGame() throws Exception
    {
        System.out.println(Layout.newLoadExit);
        String startDecision = sc.nextLine();
        switch(startDecision)
        {
            case "1" ->
            {
                System.out.println(Layout.createCharacter);
                sleep(1);
                createCharTyp();
            }
            case "2" ->
            {
                System.out.println(Layout.selectSafe);
                sleep(1);
                startGame();
            }
            case "Q", "q", "Quit", "quit" ->
            {
                System.out.println(Layout.exitGame);
                sleep(1);
                System.exit(0);
            }
            default ->
            {
                System.out.println(Layout.wrongInput);
                sleep(2);
                startGame();
            }
        }
    }
    private void createCharTyp() throws Exception {
        System.out.println(Layout.chooseCharScreen);
        charTyp = sc.nextLine();
        switch (charTyp)
        {
            case "1", "Barbarian" ->
            {
                charTyp = "Barbarian";
                setCharStatus();
                createCharName();
            }
            case "2", "Mage" ->
            {
                charTyp = "Mage";
                setCharStatus();
                createCharName();
            }
            case "b", "B", "back" ->
            {
                startGame();
            }
            default ->
            {
                System.out.println(Layout.wrongInput);
                sleep(2);
                startGame();
            }
        }
    }

    private void createCharName() throws Exception
    {
        System.out.println(Layout.chooseCharNameScreen);
        charName = sc.nextLine();
        if (!Objects.equals(charName, ""))
        {

            if (Run.dbModules.createDatabase(createDb))
            {
                giveStatusChar();
                Run.dbModules.connectDB();
                Run.dbModules.sendCommand(createTable);
                Run.dbModules.sendCommand(updateTable);
                id++;
                Run.dbModules.closeDB();
                //introVillage();
            }
            else
            {
                System.out.println("again");
                createCharName();
            }
        }
        else
        {
            System.out.println(Layout.wrongInput);
            sleep(2);
            startGame();
        }
    }
    private void setCharStatus() throws Exception {
        switch (charTyp) {
            case "Barbarian" ->
            {
                strength = 20;
                dexterity = 10;
                magic = 5;
                charHp = 20;
            }
            case "Mage" ->
            {
                strength = 5;
                dexterity = 10;
                magic = 20;
                charHp = 10;
            }
            default ->
            {
                System.out.println(Layout.wrongInput);
                sleep(2);
                startGame();
            }
        }
    }
    private void giveStatusChar() throws InterruptedException
    {
        System.out.println("char Typ:\t" + charTyp);
        System.out.println("char Name:\t" + charName + "\n");
        System.out.println("char Strength:\t" + strength);
        System.out.println("char Dexterity:\t" + dexterity);
        System.out.println("char Magic:\t\t" + magic);
        System.out.println("char Amor:\t\t" + armor);
        System.out.println("char Hp:\t\t" + charHp + "\n");
        System.out.println("char Money:\t\t" + money + " Cenni");
        sleep(5);
    }
    private void introVillage() throws InterruptedException
    {
        System.out.println(Layout.startScreen);
        sleep(5);
        System.out.println(Layout.firstStartscreen);
        sleep(5);
        System.out.println(Layout.villagersScreen);
        sleep(5);
        threeVillage();
    }
    private void threeVillage() throws InterruptedException
    {
        System.out.println(Layout.screenTop + Layout.talkTo + "\n\t\t[1]" + littleGirl + "\n\t\t[2]" + oldMan + "\n\t\t[3]" + oldWoman + Layout.screenBottom);
        String chooseTalkVillager = sc.nextLine();
        switch (chooseTalkVillager)
        {
            case "1" ->
            {
                villager(littleGirl);
            }
            case "2" ->
            {
                villager(oldMan);
            }
            case "3" ->
            {
                villager(oldWoman);
            }
            case "Q", "q", "Quit", "quit" ->
            {
                giveStatusChar();
                sleep(2);
                System.out.println(Layout.exitGame);
                sleep(1);
                System.exit(0);
            }
            default ->
            {
                System.out.println(Layout.wrongInput);
                sleep(2);
                threeVillage();
            }
        }
    }
    boolean welcomeLittleGirl;
    boolean welcomeOldMan;
    boolean welcomeOldWoman;
    private void villager(String villager) throws InterruptedException
    {
        if (Objects.equals(villager, "little girl"))
        {
            System.out.println(Layout.screenTop + "\t" + littleGirl + ":\n" + Layout.screenHelloZoe);
            sleep(3);
            littleGirl = "Zoe";
            welcomeLittleGirl = true;
            villager(littleGirl);
        }
        else if (Objects.equals(villager, "old man"))
        {
            System.out.println(Layout.screenTop + "\t" + oldMan + ":\n" + Layout.screenHelloAchim);
            sleep(3);
            oldMan = "Achim";
            welcomeOldMan = true;
            villager(oldMan);
        }
        else if (Objects.equals(villager, "old woman"))
        {
            System.out.println(Layout.screenTop + "\t" + oldWoman + ":\n" + Layout.screenHelloSibille);
            sleep(3);
            oldWoman = "Sibille";
            welcomeOldWoman = true;
            villager(oldWoman);
        }
        else
        {
            fourDecisions(villager);
        }
    }
    private void fourDecisions(String villager) throws InterruptedException
    {
        System.out.println(Layout.screenTop + "\t" + villager + ":\n" + Layout.villagerMenu);
        String decision = sc.nextLine();
        switch (decision)
        {
            case "1" -> {}
            case "2" -> {}
            case "3" -> {}
            case "b", "B", "back" ->
            {
                threeVillage();
            }
            default ->
            {
                System.out.println(Layout.wrongInput);
                sleep(2);
                fourDecisions(villager);
            }
        }
    }

}
