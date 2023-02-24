
public class Lampadaire {

	private int id;
	private boolean state;
	//private double[] coordinates;

	public Lampadaire(int id, boolean state){
		this.id = id; 
		this.state = state; 
		//this.coordinates = coordinates; 
	}

	public int getId(){
		return this.id; 
	}

	public boolean getState() {
		return this.state; 
	}

	public void setState(boolean state){
		this.state = state; 
	}

}