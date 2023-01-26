import java.util.Comparator;

public class ComparatorName implements Comparator<Books> {

	@Override
	public int compare(Books book1, Books book2) {
		// TODO Auto-generated method stub
		if(book1.getTitle().compareTo(book2.getTitle()) > 0) {
			return 1;
		}
		return -1;
	}

}
