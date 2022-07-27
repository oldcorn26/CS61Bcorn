import java.util.Scanner;

public class NBody {
    private static String Background = "images/starfield.jpg";
    public static double readRadius(String s){
        In in = new In(s);
        int num = in.readInt();
        double rad = in.readDouble();
        return rad;
    }

    public static Planet[] readPlanets(String s){
        In in = new In(s);
        int num = in.readInt();
        double rad = in.readDouble();
        Planet[] p = new Planet[num];
        for (int i = 0 ; i < num ; i ++){
            p[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return p;
    }

    public static void main(String[] args) {
        /*scan the input*/
        Scanner in = new Scanner(System.in);
        double T = Double.parseDouble(in.next());
        double dt = Double.parseDouble(in.next());
        String fileName = in.next();

        /*read the name and radius*/
        double radius = NBody.readRadius(fileName);
        Planet[] planets = NBody.readPlanets(fileName);

        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        StdDraw.enableDoubleBuffering();

        for (double time = 0 ; time < T ; time += dt){

            /*calc force*/
            for (int i = 0 ; i < planets.length ; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /*update status*/
            for (int i = 0 ; i < planets.length ; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            /*draw a universe*/
            StdDraw.setScale(-radius, radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, Background);

            /*draw every planet*/
            for(Planet p : planets){
                p.draw();
            }

            /*show the picture and pause for a while*/
            StdDraw.show();
            StdDraw.pause(10);
        }

        /*print the status*/
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
