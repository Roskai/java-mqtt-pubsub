public class Serveur {

    public static boolean changeLampadaireState(int hour, int brightness) {

        boolean state; 

        if (hour >= 20 && hour <7 || brightness <= 10){
            state = true; 
        }else {
            state = false; 
        }
        return state; 
    }


}