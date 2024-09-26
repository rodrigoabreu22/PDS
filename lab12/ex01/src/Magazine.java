import java.util.List;
import java.util.Comparator;

public class Magazine {
    private SortingAlgorithm sorting_type;

    public Magazine(SortingAlgorithm sorting_type) {
        this.sorting_type = sorting_type;
    }

    public void setSortingType(SortingAlgorithm sorting_type) {
        this.sorting_type = sorting_type;
    }

    public void sort(List<Phone> phone_list, Comparator<Phone> comparator) {
        sorting_type.sort(phone_list, comparator);
    }
}
