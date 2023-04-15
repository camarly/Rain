package Project3;

import java.util.Comparator;

public class SortbyTemperature implements Comparator<City> {

    @Override
    public int compare(City c1, City c2) {

        if (c1.getTemp() > c2.getTemp())
            return 1;
        else {
            if (c2.getTemp() > c1.getTemp())
                return -1;
            else
                return 0;
        }
    }
}
