public class Planet {
    private static final double G = 6.67e-11;
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double xx = Math.abs(p.xxPos - xxPos);
        double yy = Math.abs(p.yyPos - yyPos);
        return Math.sqrt(xx * xx + yy * yy);
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return G * mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double force = calcForceExertedBy(p), xx = p.xxPos - xxPos, r = calcDistance(p);
        return force * xx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double force = calcForceExertedBy(p), yy = p.yyPos - yyPos, r = calcDistance(p);
        return force * yy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double forceX = 0;
        for(Planet p : allPlanets) {
            if(this.equals(p)) {
                continue;
            }
            forceX += calcForceExertedByX(p);
        }
        return forceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double forceY = 0;
        for(Planet p : allPlanets) {
            if(this.equals(p)) {
                continue;
            }
            forceY += calcForceExertedByY(p);
        }
        return forceY;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass, ay = fy / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos,"images/" + imgFileName);
    }
}