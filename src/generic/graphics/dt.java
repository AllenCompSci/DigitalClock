package generic.graphics;

/**
 * Created by taylor hudson on 3/30/2017.
 */
public class dt {
    int dd, mm, yyyy;
    public dt(int m, int d, int y){
        dd = d;
        mm = m;
        yyyy = y;
    }
    public int addYears(int t){
        return yyyy + t;
    }

    @Override
    public String toString() {
        return mm + " " + dd + " " + String.valueOf(yyyy);
    }
}
