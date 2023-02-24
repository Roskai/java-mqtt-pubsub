import java.io.Serializable;
import java.util.concurrent.TimeUnit; 
import java.util.Random;


public class EnvironmentCaptor implements Serializable{
    private static final long serialVersionUID = 1L;

    private int brightness = 5;
    private int hour ; 
    private boolean presence ;


    public EnvironmentCaptor(int hour){
        Random random = new Random();
        this.hour = hour; 
        createBrightness();
        presence = random.nextBoolean();
    }




    public void createBrightness() {
        if (this.hour  >= 22 || this.hour  <= 7) {
            this.brightness = 5;
        } else if (this.hour  >= 8 && this.hour  <= 18) {
            this.brightness = 100;
        } else if (this.hour  >= 19 && this.hour  <= 21) {
            this.brightness = 15;
        }
    }

    public int getBrightness() { return this.brightness; }

    public String presenceToString () {
        return getPresence() ? "true" : "false";
    }

    public String toString() { return "Luminosité : "+this.brightness+". Heure : "+this.hour+". Il y a quelqu'un : "+presenceToString();}

    public int getHour() { return this.hour; }

    public boolean getPresence() {return this.presence;}



}
