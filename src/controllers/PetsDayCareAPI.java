package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PetsDayCareAPI {

    private ArrayList<Pet> pets;

    public PetsDayCareAPI() {
        pets = new ArrayList<Pet>();
    }

    public boolean addPet(Pet pet) {
        return pets.add(pet);
    }

    public String listPets() {
        String str = "";

        for(Pet pet: pets) {
            str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
        }

        if (str.isEmpty()){
            return "No Pets";
        }
        else {
            return str;
        }
    }

    public String listParrots() {
        String str = "";

        for(Pet pet: pets) {
            if (pet instanceof Parrot) {
                str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
            }
        }

        if (str.isEmpty()){
            return "No Parrots";
        }
        else {
            return str;
        }
    }

    public String listDogs() {
        String str = "";

        for(Pet pet: pets) {
            if (pet instanceof Dog) {
                str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
            }
        }

        if (str.isEmpty()){
            return "No Dogs";
        }
        else {
            return str;
        }
    }

    public String listCats() {
        String str = "";

        for(Pet pet: pets) {
            if (pet instanceof Cat) {
                str += pets.indexOf(pet) + ": " + pet.toString() + "\n";
            }
        }

        if (str.isEmpty()){
            return "No Event Posts";
        }
        else {
            return str;
        }
    }

    public Pet deletePet(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return pets.remove(indexToDelete);
        }
        return null;
    }

    public boolean updateDog(int indexToUpdate, String breed, boolean dangerousBreed) {
        //find the object by the index number
        Pet foundPet = findPet(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof Dog)) {
            Dog foundDog = (Dog) foundPet;
            foundDog.setBreed(breed);
            foundDog.setDangerousBreed(dangerousBreed);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public boolean updateParrot(int indexToUpdate, String vocabularySize) {
        //find the object by the index number
        Pet foundPet = findPet(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof Parrot)){
            ((Parrot) foundPet).setVocabularySize(vocabularySize);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public boolean updateCat(int indexToUpdate, String favouriteToy, boolean indoorCat) {
        //find the object by the index number
        Pet foundPet = findPet(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof Cat)){
            ((Cat) foundPet).setFavouriteToy(favouriteToy);
            ((Cat) foundPet).setIndoorCat(indoorCat);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public Pet findPet(int index) {
        if (isValidIndex(index)) {
            return pets.get(index);
        }
        return null;
    }

    public int numberOfPets() {
        return pets.size();
    }

    public int numberOfDogs() {
        int number = 0;
        for (Pet pet: pets){
            if (pet instanceof Dog){
                number++;
            }
        }
        return number;
    }

    public int numberOfParrots() {
        int number = 0;
        for (Pet pet: pets){
            if (pet instanceof Parrot){
                number++;
            }
        }
        return number;
    }

    public int numberOfCats() {
        int number = 0;
        for (Pet pet: pets){
            if (pet instanceof Cat){
                number++;
            }
        }
        return number;
    }

  /** public void likeAPost(int index) {
        Post post = null;
        if (isValidIndex(index)) {
            post = posts.get(index);
            if ((post instanceof LikedPost)){
                ((LikedPost) post).likeAPost();
            }
        }
    }

    public void unLikeAPost(int index) {
        Post post = null;
        if (isValidIndex(index)) {
            post = posts.get(index);
            if ((post instanceof LikedPost)){
                ((LikedPost) post).unlikeAPost();
            }
        }
    }
    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception  An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { Dog.class, Cat.class, Parrot.class, Mammal.class, Pet.class, Owner.class, Bird.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("pets.xml"));
        pets = (ArrayList<Pet>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception  An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("pets.xml"));
        out.writeObject(pets);
        out.close();

    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < pets.size());
    }

    public boolean isValidDogIndex(int index) {
        if (isValidIndex(index)) {
            return (pets.get(index)) instanceof Dog;
        }
        return false;
    }

    public boolean isValidParrotIndex(int index) {
        if (isValidIndex(index)) {
            return (pets.get(index)) instanceof Parrot;
        }
        return false;
    }

    public boolean isValidCatIndex(int index) {
        if (isValidIndex(index)) {
            return (pets.get(index)) instanceof Cat;
        }
        return false;
    }
//
     //
}
