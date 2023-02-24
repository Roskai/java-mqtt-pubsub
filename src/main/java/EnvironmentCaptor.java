import java.io.Serializable;
import java.util.concurrent.TimeUnit; 

public class EnvironmentCaptor implements Serializable{
    private static final long serialVersionUID = 1L;

    private int brightness = 5;
    private int hour ; 

    public EnvironmentCaptor(int hour){
        this.hour = hour; 
        createBrightness();
    }

    public void createBrightness() {
            if (this.hour >= 7 && this.hour <= 20){
                this.brightness = 100;
            } else if(this.hour >= 21 || this.hour < 7) {
                this.brightness = 15;
            } else if(this.hour >= 7 && this.hour < 22) {
                this.brightness = 5;
            }
        
    }

    public int getBrightness() { return this.brightness; }

    public String toString() { return "Luminosité : "+this.brightness+". Heure : "+this.hour;}

    public int getHour() { return this.hour; }


}
