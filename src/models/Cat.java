package models;

public class Cat extends Mammal {
    private boolean indoorCat;
    private String favouriteToy;

    public Cat( String name, int age, Owner owner, int id, char sex, boolean vaccinated, double weight, boolean neutered, boolean indoorCat, String favouriteToy){
        super(name, age, owner, id, sex, vaccinated, weight, neutered);

        this.indoorCat = indoorCat;
        this.favouriteToy = favouriteToy;
    }
    public boolean isIndoorCat(){
        return indoorCat;
    }
    public void setIndoorCat(boolean indoorCat){
        this.indoorCat=indoorCat;
    }
    public String getFavouriteToy(){
        return favouriteToy;
    }
    public void setFavouriteToy(String favouriteToy){
        this.favouriteToy = favouriteToy;
    }
    @Override
    public double calculateWeeklyFee(){
        double fee = 20 * 7;
        if (indoorCat){
            fee += 5 * 7;
        }
        return fee;
    }

    @Override
    public String toString() {
       return super.toString() + ", Indoor Cat: " + indoorCat + ", Favourite Toy: " + favouriteToy;
    }
}
