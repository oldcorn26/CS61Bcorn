public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p){
        double dis = this.calcDistance(p);
        double f = (G * this.mass * p.mass) / (dis * dis);
        return f;
    }

    public double calcForceExertedByX(Planet p){
        double dis = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        double fx = this.calcForceExertedBy(p) * dx /dis;
        return fx;
    }

    public double calcForceExertedByY(Planet p){
        double dis = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        double fy = this.calcForceExertedBy(p) * dy /dis;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] p){
        double sum = 0;
        for (Planet temp : p){
            if (!this.equals(temp)){
                sum += this.calcForceExertedByX(temp);
            }
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] p){
        double sum = 0;
        for (Planet temp : p){
            if (!this.equals(temp)){
                sum += this.calcForceExertedByY(temp);
            }
        }
        return sum;
    }

    public void update(double dt, double fx, double fy){
        this.xxVel = this.xxVel + dt * fx / this.mass;
        this.yyVel = this.yyVel + dt * fy / this.mass;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        String add = "images/";
        StringBuffer addf = new StringBuffer(this.imgFileName);
        addf.insert(0, add);
        StdDraw.picture(this.xxPos, this.yyPos, addf.toString());
    }
}
