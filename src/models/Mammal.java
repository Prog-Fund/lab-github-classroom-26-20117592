package models;

public abstract class Mammal extends Pet {
    private char sex;
    private boolean neutered;
    private double weight;
    private boolean vaccinated;


    public Mammal(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered) {
        super(name, age, owner, id);

        setSex(sex);
        this.neutered = neutered;
        setWeight(weight);
        this.vaccinated = vaccinated;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        if(sex=='M' || sex == 'F' || sex == 'U') {
            this.sex = sex;
        }else {
            this.sex = 'U';
        }
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight >= 2 && weight <= 200) {
            this.weight = weight;
        } else {
            this.weight = 2;
        }
    }

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }
    @Override
    public String toString() {
        return super.toString() + ", Sex: " + sex + ", Vaccinated: " + vaccinated + ", neutered: " + neutered + ", Weight: " + weight;
    }
}
