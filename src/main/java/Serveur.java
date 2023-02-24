public class Serveur {

    public static boolean changeLampadaireState(int hour, int brightness, boolean presence) {

        boolean state; 

        if ((hour >= 20 || hour <7) && presence  ){
            state = true; 
        }else {
            state = false; 
        }
        return state; 
    }


}