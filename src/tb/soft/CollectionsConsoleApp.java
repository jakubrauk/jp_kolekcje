package tb.soft;

import java.util.*;

public class CollectionsConsoleApp extends PersonConsoleApp {

    private static final String MENU =
            " ========================= M E N U =========================\n" +
                    "1 - Wprowadź ręcznie dane osób w celu uzupełnienia kolekcji.\n" +
                    "2 - Wybierz rodzaj kolekcji\n" +
                    "3 - Pokaż aktualną liczbę osób.\n" +
                    "0 - Zakończ program.\n";

    private static final String CHOOSE_COLLECTION =
            "1 - HashSet\n" +
                    "2 - TreeSet\n" +
                    "3 - ArrayList\n" +
                    "4 - LinkedList\n" +
                    "5 - HashMap\n" +
                    "6 - TreeMap\n";

    private static final ConsoleUserDialog UI = new ConsoleUserDialog();

    private final MyPersonInput myPersonInput = new MyPersonInput();
    private AbstractCollection<MyPerson> chosenCollection = null;
    private AbstractMap<MyPerson, Integer> chosenMap = null;



    public static void main(String[] args) {
        CollectionsConsoleApp application = new CollectionsConsoleApp();
        application.runMainLoop();
    }


    @Override
    public void runMainLoop() {
        while (true) {
            UI.clearConsole();
            switch (UI.enterInt(MENU + "==>>")) {
                case 1:
                    MyPerson newPerson = createNewPerson();
                    showPerson(newPerson);
                    myPersonInput.loadPerson(newPerson);
                    break;
                case 2:
                    chooseCollection();
                    break;
                case 3:
                    printPeopleNumber();
                    break;
                case 0:
                    System.exit(0);  // end program
            }
        }
    }

    private void chooseCollection() {
        setChosenNull();
        UI.clearConsole();

        List<MyPerson> listOfPeople = myPersonInput.getListOfPeople();
        int numberOfPeople = myPersonInput.getNumberOfPeople();
        MyPerson personToRemove = myPersonInput.getRandomPerson();

        UI.printMessage("Wybierz kolekcję:\n");
        switch (UI.enterInt(CHOOSE_COLLECTION + "==>>")) {
            case 1:
                UI.printMessage("Wybrałeś HashSet");
                chosenCollection = new HashSet<>();
                break;
            case 2:
                UI.printMessage("Wybrałeś TreeSet");
                chosenCollection = new TreeSet<>();
                break;
            case 3:
                UI.printMessage("Wybrałeś ArrayList");
                chosenCollection = new ArrayList<>();
                break;
            case 4:
                UI.printMessage("Wybrałeś LinkedList");
                chosenCollection = new LinkedList<>();
                break;
            case 5:
                UI.printMessage("Wybrałeś HashMap");
                chosenMap = new HashMap<>();
                break;
            case 6:
                UI.printMessage("Wybrałeś TreeMap");
                chosenMap = new TreeMap<>();
                break;
        }

        showCollectionOperations(listOfPeople, numberOfPeople, personToRemove);
        showMapOperations(listOfPeople, numberOfPeople, personToRemove);
    }

    private void addPeopleToCollection(List<MyPerson> peopleList, int peopleNumber) {
        for (int i = 0; i < peopleNumber; i++) {
            chosenCollection.add(peopleList.get(i));
        }
    }

    private void addPeopleToMap(List<MyPerson> peopleList, int peopleNumber) {
        for (int i = 0; i < peopleNumber; i++) {
            chosenMap.put(peopleList.get(i), 1);
        }
    }

    private void printCollection() {
        for (MyPerson myPerson : chosenCollection) {
            showPerson(myPerson);
        }
    }

    private void printMap() {
        for (Map.Entry<MyPerson, Integer> entry : chosenMap.entrySet()) {
            showPerson(entry.getKey());
        }
    }

    private void showCollectionOperations(List<MyPerson> peopleList, int numberOfPeople, MyPerson toRemove) {
        if (chosenCollection != null) {
            UI.printMessage(chosenCollection.getClass().getName());
            long addingStartTime = System.nanoTime();
            addPeopleToCollection(peopleList, numberOfPeople);
            UI.printMessage("Czas dodawania osób do kolekcji: " +
                    (System.nanoTime() - addingStartTime));

            long displayingStartTime = System.nanoTime();
            printCollection();
            UI.printMessage("Czas wyświetlania osób w kolekcji: " +
                    (System.nanoTime() - displayingStartTime));

            long removingStartTime = System.nanoTime();
            chosenCollection.remove(toRemove);
            UI.printInfoMessage("Czas usuwania losowej osoby z kolekcji: " +
                    (System.nanoTime() - removingStartTime));
        }
    }

    private void showMapOperations(List<MyPerson> peopleList, int numberOfPeople, MyPerson toRemove) {
        if (chosenMap != null) {
            addPeopleToMap(peopleList, numberOfPeople);
            printMap();
            chosenMap.remove(toRemove);
        }
    }

    private void setChosenNull() {
        chosenCollection = null;
        chosenMap = null;
    }

    static MyPerson createNewPerson() {
        String first_name = UI.enterString("Podaj imię: ");
        String last_name = UI.enterString("Podaj nazwisko: ");
        String birth_year = UI.enterString("Podaj rok ur.: ");
        UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
        String job_name = UI.enterString("Podaj stanowisko: ");
        MyPerson person;
        try {
            // Utworzenie nowego obiektu klasy MyPerson oraz
            // ustawienie wartości wszystkich atrybutów.
            person = new MyPerson(first_name, last_name);
            person.setBirthYear(birth_year);
            person.setJob(job_name);
        } catch (PersonException e) {
            // Tu są wychwytywane wyjątki zgłaszane przez metody klasy MyPerson,
            // gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
            // poszczególnych atrybutów.
            // Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
            UI.printErrorMessage(e.getMessage());
            return null;
        }
        return person;
    }

    private void printPeopleNumber() {
        UI.printInfoMessage("Aktualna liczba osób: " + myPersonInput.getNumberOfPeople());
    }
}