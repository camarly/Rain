package Project3;

import java.util.Comparator;

public class SortByHumidity implements Comparator<City> {

    @Override
    public int compare(City c1, City c2) {

        return c1.getHumidity()-c2.getHumidity();
    }
}
