import synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT_BASIC = 440.0;

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    private static GuitarString[] stringArray;

    private static double getKeyConcert(int index) {
        return CONCERT_BASIC * Math.pow(2, 1.0 * (index - 24) / 12);
    }

    public static void main(String[] args) {
        stringArray = new GuitarString[37];
        for(int i = 0; i < 37; i++) {
            stringArray[i] = new GuitarString(getKeyConcert(i));
        }

        while(true) {
            if(StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if(index == -1) {
                    System.out.println("ERROR KEY!!!");
                    continue;
                }
                stringArray[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for(GuitarString gs: stringArray) {
                sample += gs.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for(GuitarString gs: stringArray) {
                gs.tic();
            }
        }
    }
}
