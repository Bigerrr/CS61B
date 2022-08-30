public class NBody {
    public static double readRadius(String file) {
        In in = new In(file);
        int count = in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int count = in.readInt();
        double Radius = in.readDouble();
        Planet[] allPlanets = new Planet[count];
        for(int i = 0; i < count; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        in.close();
        return allPlanets;
    }

    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("ERROR!");
            return;
        }
        double T = Double.parseDouble(args[0]), dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        double time = 0;
        while(time < T) {
            double[] xForces = new double[allPlanets.length], yForces = new double[allPlanets.length];
            for(int i = 0; i < allPlanets.length; i++) {
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForces[i], yForces[i]);
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
            System.out.println(time + " " + T);
        }

        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }
    }
}
