package tb.soft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyPersonInput {

    private final List<MyPerson> listOfPeople = new ArrayList<>();

    public void loadPerson(MyPerson person) {
        listOfPeople.add(person);
    }

    public int getNumberOfPeople() {
        return listOfPeople.size();
    }

    public List<MyPerson> getListOfPeople() {
        return listOfPeople;
    }

    public MyPerson getRandomPerson() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(getNumberOfPeople());
        return getListOfPeople().get(randomIndex);
    }
}
