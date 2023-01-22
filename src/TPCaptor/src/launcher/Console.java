package launcher;

import model.*;

import java.util.HashMap;

public class Console {

    public static void main(String[] args) throws Exception {

        // Captor with GenCPU
        Captor captor1 = new CaptorBasic("France", new GenCPU());
        System.out.println(captor1.getId() + ": " + captor1.getName() + ", temperature : " + captor1.getTemperature());

        // Captor with GenRandom
        Captor captor2 = new CaptorBasic("Italie", new GenBoundedRandom(-10, 60));
        System.out.println(captor2.getId() + ": " + captor2.getName() + ", temperature : " + captor2.getTemperature());


        // CaptorArea
        Captor captorZone = new CaptorArea("Europe");
        captorZone.addCaptor(captor1, 20.0);
        captorZone.addCaptor(captor2, 40.0);
        System.out.println(captorZone.getId() + ": " + captorZone.getName() + ", temperature : " + captorZone.getTemperature());


    }

}
