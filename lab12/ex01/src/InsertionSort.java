import java.util.Comparator;
import java.util.List;

public class InsertionSort implements SortingAlgorithm{

    @Override
    public void sort(List<Phone> phones, Comparator<Phone> comparator) {
        int n = phones.size();
        for (int i = 1; i < n; ++i) {
            Phone key = phones.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(phones.get(j), key) > 0) {
                phones.set(j + 1, phones.get(j));
                j = j - 1;
            }
            phones.set(j + 1, key);
        }
    }

}
