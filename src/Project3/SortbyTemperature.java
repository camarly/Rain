package Project3;

import java.util.Comparator;

public class SortbyTemperature implements Comparator<City> {

    @Override
    public int compare(City c1, City c2) {

        return ((int) c1.getTemp()-(int)c2.getTemp());
    }
}
