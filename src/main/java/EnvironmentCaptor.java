import java.io.Serializable;
import java.util.concurrent.TimeUnit; 

public class EnvironmentCaptor implements Serializable{
    private static final long serialVersionUID = 1L;

    private int brightness = 5;

    public EnvironmentCaptor(int hour){
        createBrightness(hour);
    }

    public void createBrightness(int hour) {
            if (hour >= 7 && hour <= 20){
                this.brightness = 100 ;
            }else if(hour > 20 && hour < 7){
                this.brightness = 15;
            }else if(hour >= 22 && hour <= 6){
                this.brightness = 5 ;
            }
            try{
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e){
                System.out.println("erreur sleep");
            }
        
    }

    public int getBrightness() { return this.brightness; }

    public String toString() { return "Luminosité : "+this.brightness;}


}
