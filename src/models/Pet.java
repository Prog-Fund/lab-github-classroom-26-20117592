package models;

public abstract class Pet {
    private int id;
private String name;
private int age;
private Owner owner;
private boolean[] daysAttending;
private static int nextId = 1000;

    public Pet(String name, int age, Owner owner, int id){
      setName(name);
      setOwner(owner);
      setAge(age);
      this.daysAttending = new boolean[6];
      if (name.length()<=30)
          this.name=name;
      else this.name=name.substring(0,30);
      if (id>=1000)
          this.id=id;
      else
        this.id=nextId++;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 1000) {
            this.id = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length()<=30)
            this.name = name;
        else
            this.name=name.substring(0,30);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean[] getDaysAttending() {
        return daysAttending;
    }

    public void setDaysAttending(boolean[] daysAttending) {
        this.daysAttending = daysAttending;
    }
    public void checkIn(int dayIndex){
        if (dayIndex >= 0 && dayIndex <= 5){
            daysAttending[dayIndex] = true;
        }
    }
    public void checkOut(int dayIndex){
        if (dayIndex >= 0 && dayIndex <= 5){
            daysAttending[dayIndex] = true;
        }
    }
    public int numOfDaysInKennel() {
        int count = 0;
        for (boolean day : daysAttending) {
            if (day) {
                count++;
            }
        }
        return count;
    }
public abstract double calculateWeeklyFee();

    @Override
    public String toString(){
        return "Pet ID: " + id + ", Name: " + name + "Age: " + age + "Owner: " + owner + "Days in Kennel: " + numOfDaysInKennel();
    }
}
