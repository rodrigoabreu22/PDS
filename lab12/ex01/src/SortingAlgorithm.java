import java.util.List;
import java.util.Comparator;

public interface SortingAlgorithm {

    public void sort(List<Phone> phone, Comparator<Phone> comparator);
}