import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class MergeSort implements SortingAlgorithm{
    @Override
    public void sort(List<Phone> phones, Comparator<Phone> comparator) {
        if (phones.size() > 1) {
            int mid = phones.size() / 2;
            List<Phone> left = new ArrayList<>(phones.subList(0, mid));
            List<Phone> right = new ArrayList<>(phones.subList(mid, phones.size()));  
            sort(left, comparator);
            sort(right, comparator);    
            merge(phones, left, right, comparator);
        }
    }

    public void merge(List<Phone> phones, List<Phone> left, List<Phone> right, Comparator<Phone> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) < 0) {
                phones.set(k++, left.get(i++));
            } else {
                phones.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            phones.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            phones.set(k++, right.get(j++));
        }
    }
}
