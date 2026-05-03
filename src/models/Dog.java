package models;

public class Dog extends Mammal {
    private static final float NONDANGEROUS_DAILY_RATE = 30f;
    private boolean dangerousBreed;
    private static final float DANGEROUS_DAILY_RATE = 40f;
    private String breed;

    public Dog(String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered, String breed, boolean dangerousBreed){
        super(name, age, owner, id, sex, vaccinated, weight, neutered);
        this.breed = breed;
        this.dangerousBreed = dangerousBreed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean isDangerousBreed() {
        return dangerousBreed;
    }

    public void setDangerousBreed(boolean dangerousBreed) {
        this.dangerousBreed = dangerousBreed;
    }

    @Override
   public double calculateWeeklyFee(){
        if (dangerousBreed){
            return DANGEROUS_DAILY_RATE * numOfDaysInKennel();
        }else {
            return NONDANGEROUS_DAILY_RATE * numOfDaysInKennel();
        }
    }
    @Override
    public boolean equals(Object o){
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Dog dog = (Dog) o;
        return super.equals(dog)
                && this.dangerousBreed == dog.dangerousBreed
                && this.breed.equalsIgnoreCase(dog.breed);
    }

    @Override
    public String toString(){
        return super.toString() + ", Breed: " + breed + ", Dangerous Breed: " + dangerousBreed;
    }
}

