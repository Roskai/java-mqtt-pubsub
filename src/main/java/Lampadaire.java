package entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lampadaire {

	private int id;
	private boolean state;
	private double[] coordinates;

}