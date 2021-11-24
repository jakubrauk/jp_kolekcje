package tb.soft;

import java.util.Objects;

public class MyPerson extends Person implements Comparable<MyPerson>{
    public MyPerson(String first_name, String last_name) throws PersonException {
        super(first_name, last_name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
            MyPerson person = (MyPerson) obj;
            if (person.getFirstName().equals(getFirstName())) {
                if (person.getLastName().equals(getLastName())) {
                    return person.getBirthYear() == getBirthYear();
                }
            }
        }
        return false;
    }

    @Override
    public int compareTo(MyPerson myPerson) {
        return myPerson.getBirthYear();
    }
}
