package business;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dao.LampadaireDao;
import entities.Lampadaire;

@Singleton
public class LampadaireBusiness {

	@Inject
	private LampadaireDao lampadaireDao;

	public List<Lampadaire> getAllLampadaires() {
		return lampadaireDao.getAll();
	}

	public Lampadaire get(int id) {
		return lampadaireDao.get(id);
	}

	public void add(Lampadaire lampadaire) {
		lampadaireDao.create(lampadaire);
	}

	public void delete(int id) {
		lampadaireDao.delete(get(id));
	}

	public Lampadaire search(Double[] coordinates) {
		return lampadaireDao.getByCoordinate(coordinates);
	}

}