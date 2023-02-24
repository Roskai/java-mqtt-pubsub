import java.io.Serializable;
import java.util.concurrent.TimeUnit; 

public class EnvironmentCaptor implements Serializable{
    private static final long serialVersionUID = 1L;

    private int brightness = 5;
    private int hour ; 

    public EnvironmentCaptor(int hour){
        this.hour = hour; 
    }

    public void createBrightness() {
            if (this.hour >= 7 && this.hour <= 20){
                this.brightness = 100 ;
            }else if(this.hour > 20 && this.hour < 7){
                this.brightness = 15;
            }else if(this.hour >= 22 && this.hour <= 6){
                this.brightness = 5 ;
            }
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                System.out.println("erreur sleep");
            }
        
    }

    public int getBrightness() { return this.brightness; }

    public String toString() { return "Luminosité : "+this.brightness;}

    public int getHour() { return this.hour; }


}
