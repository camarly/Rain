package Project3;

import java.util.Comparator;

public class SortbyTemperature implements Comparator<City> {

    @Override
    public int compare(City c1, City c2) {

        return Double.compare(c1.getTemp(),c2.getTemp());
    }
}
