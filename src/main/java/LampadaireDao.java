
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class LampadaireDao {

	private static List<Lampadaire> lampadaires = Stream.of(
		new Lampadaire(0, false),
		new Lampadaire(1, false), 
        new Lampadaire(2, false), 
        new Lampadaire(3, false)
	).collect(Collectors.toList());


	public Lampadaire get(int id) {
		return lampadaires.stream().filter(lampadaire -> lampadaire.getId() == id).findAny().orElse(null);
	}


	public void setAllState(boolean state){
		List<Lampadaire> lesLampadaires = getAll(); 
		for(Lampadaire unLampadaire : lesLampadaires){
			unLampadaire.setState(state); 
		}
	}
/* 
	public Lampadaire getByCoordinate(double[] coordinates) {
		return Lampadaire.stream().filter(lampadaire -> lampadaire.getCoordinate().equals(coordinates)).findAny().orElse(null);
	}
*/
	public List<Lampadaire> getAll() {
		return Collections.unmodifiableList(lampadaires);
	}

	public void create(Lampadaire entity) {
		lampadaires.add(entity);
	}

	public void delete(Lampadaire entity) {
		lampadaires.remove((int) entity.getId());
	}

	

}