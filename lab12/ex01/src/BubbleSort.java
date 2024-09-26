import java.util.Comparator;
import java.util.List;

public class BubbleSort implements SortingAlgorithm{
    
    @Override
    public void sort(List<Phone> phones, Comparator<Phone> comparator) {
        int n = phones.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(phones.get(j), phones.get(j + 1)) > 0) {
                    Phone temp = phones.get(j);
                    phones.set(j, phones.get(j + 1));
                    phones.set(j + 1, temp);
                }
            }
        }
    }
}
