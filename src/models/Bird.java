package models;

import java.util.Objects;

public abstract class Bird extends Pet {
    private double wingSpan;
    private boolean canFly;

    public Bird(String name,int age, Owner owner, int id, double wingSpan, boolean canFly){
        super(name, age, owner, id);
        setWingSpan(wingSpan);
        this.canFly = canFly;
    }
    public double getWingSpan(){
        return wingSpan;
    }
    public void setWingSpan(double wingSpan){
        if (wingSpan >= 3 && wingSpan <=400){
            this.wingSpan = wingSpan;
        }
        else {
            this.wingSpan = 3;
        }
    }

    public boolean isCanFly() {
        return canFly;
    }
    public void setCanFly(boolean canFly){
        this.canFly = canFly;
    }
    @Override
    public String toString(){
        return super.toString() + ", Wing Span: " + wingSpan + ", Can Fly: " + canFly;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bird bird = (Bird) o;
        return Double.compare(wingSpan, bird.wingSpan) == 0 && canFly == bird.canFly;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wingSpan, canFly);
    }
}
