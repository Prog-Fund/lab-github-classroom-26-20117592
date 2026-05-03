package main;

import controllers.PetsDayCareAPI;
import models.*;
import utils.ScannerInput;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyles.index;

public class Driver {

    private final PetsDayCareAPI petAPI = new PetsDayCareAPI();

    public static void main(String[] args) {
        new Driver();
    }

    public Driver() {
        runMenu();
    }

    private int mainMenu() {
        return ScannerInput.readNextInt("""
                   -----Pet Day Care-----
                   -----------------------
                   1) Pets CRUD MENU
                   2) Reports MENU   
                
                   -----------------------
                   10) Save all
                   11) Load All
                   -----------------------
                   0) Exit
                ==>>  """);
    }

    private void runMenu() {
        int option = mainMenu();

        while (option != 0) {

            switch (option) {
                case 1 -> runCRUDMenu();
                case 2 -> viewPets();

                case 10 -> save();
                case 11 -> load();
                default -> System.out.println("Invalid option entered: " + option);
            }

            //pause the program so that the user can read what we just printed to the terminal window
            ScannerInput.readNextLine("\nPress enter key to continue...");

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting...bye");
        System.exit(0);
    }

    private void runCRUDMenu() {
        int option = ScannerInput.readNextInt("""
                -----Pets CRUD Menu-----
                | 1) Add a new Pet      |
                | 2) Delete a Pet       |
                | 3) List all Pets      |
                | 4) Update Pet Info    |
                | 0) Return to main     |
                ------------------------
                ==>> """);

        while (option != 0) {
            switch (option) {
                case 1 -> addPet();
                case 2 -> deletePet();
                case 3 -> System.out.println(petAPI.listPets());
                case 4 -> updatePet();
                default -> System.out.println("Invalid option entered: " + option);
            }

            option = ScannerInput.readNextInt("""
                    -----Pets CRUD Menu-----
                    | 1) Add a new Pet      |
                    | 2) Delete a Pet       |
                    | 3) List all Pets      |
                    | 4) Update Pet Info    |
                    | 0) Return to main     |
                    ------------------------
                    ==>> """);
        }
    }

    //------------------------------------------------------------------------------------------
    //  Option 1 - Add Posts - the user is asked if it is a message or a photo post
    //             and the required details are then gathered before adding the specific object
    //------------------------------------------------------------------------------------------
    private void addPet() {

        boolean isAdded = false;

        int option = ScannerInput.readNextInt("""
                ---------------------------
                |   1) Add a Dog |
                |   2) Add a Cat   |
                |   3) Add a Parrot  |
                ---------------------------
                ==>> """);

        switch (option) {
            case 1 -> {
                isAdded = addDog();
            }
            case 2 -> {
                isAdded = addCat();
            }

            case 3 -> {
                isAdded = addParrot();
            }

            default -> System.out.println("Invalid option entered: " + option);
        }

        if (isAdded) {
            System.out.println("Pet Added Successfully");
        } else {
            System.out.println("No Pet Added");
        }
    }

    private boolean addDog() {
        String name = ScannerInput.readNextLine("Enter the Dog Name: ");
        int age = ScannerInput.readNextInt("Enter Age: ");
        int id = ScannerInput.readNextInt("Enter ID: ");
        char sex = ScannerInput.readNextChar("Enter Sex (M/F): ");
        boolean vaccinated = ScannerInput.readNextLine("Is the dog vaccinated? (y/n): ").equalsIgnoreCase("y");
        double weight = ScannerInput.readNextDouble("Enter Weight: ");
        boolean neutered = ScannerInput.readNextLine("Is the dog neutered? (y/n): ").equalsIgnoreCase("y");
        String breed = ScannerInput.readNextLine("Enter Breed: ");
        boolean dangerousBreed = ScannerInput.readNextLine("Is it a dangerous Breed? (y/n): ").equalsIgnoreCase("y");
        return petAPI.addPet(new Dog(name, age, null, id, sex, vaccinated, weight, neutered, breed, dangerousBreed));
    }



    private boolean addCat() {
        String name = ScannerInput.readNextLine("Enter the Cat Name: ");
        int age = ScannerInput.readNextInt("Enter Age: ");
        int id = ScannerInput.readNextInt("Enter ID: ");
        char sex = ScannerInput.readNextChar("Enter Sex (M/F): ");
        boolean vaccinated = ScannerInput.readNextLine("Is the Cat Vaccinated? (y/n): ").equalsIgnoreCase("y");
        double weight = ScannerInput.readNextDouble("Enter Weight: ");
        boolean neutered = ScannerInput.readNextLine("Is the cat neutered? (y/n): ").equalsIgnoreCase("y");
        boolean isIndoor = ScannerInput.readNextLine("Is it an Indoor Cat? (y/n): ").equalsIgnoreCase("y");
        String favouriteToy = ScannerInput.readNextLine("Enter Favourite Toy: ");
        return petAPI.addPet(new Cat(name, age, null, id, sex, vaccinated, weight, neutered, isIndoor, favouriteToy));
    }


    private boolean addParrot() {
        String name = ScannerInput.readNextLine("Enter the Parrot Name: ");
        int age = ScannerInput.readNextInt("Enter Age: ");
        int id = ScannerInput.readNextInt("Enter ID: ");
        double wingSpan = ScannerInput.readNextDouble("Enter Wing Span: ");
        boolean canFly = ScannerInput.readNextLine("Can it Fly? (y/n): ").equalsIgnoreCase("y");
        String vocabularySize = ScannerInput.readNextLine("Enter Vocabulary Size (number of words): ");
        return petAPI.addPet(new Parrot(name, age, null, id, wingSpan, canFly, vocabularySize));
    }


    //------------------------------------------------------------------------------------------
//  Option 2 - Update Posts - if posts exist, the user is asked if it is a message or a photo post
//             and the required details are then gathered before adding the specific object
//------------------------------------------------------------------------------------------
    private void updatePet() {

        if (petAPI.numberOfPets() > 0) {
            boolean isUpdated = false;

            int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) Update a Dog |
                    |   2) Update a Cat  |
                    |   3) Update an Parrot  |
                    ---------------------------
                    ==>> """);

            switch (option) {
                case 1 -> isUpdated = updateDog();
                case 2 -> isUpdated = updateCat();
                case 3 -> isUpdated = updateParrot();
                default -> System.out.println("Invalid option entered: " + option);
            }

            if (isUpdated) {
                System.out.println("Pet Updated Successfully");
            } else {
                System.out.println("No Pet Updated");
            }
        } else {
            System.out.println("No pets added yet");
        }
    }

    private boolean updateDog() {
        System.out.println(petAPI.listDogs());
        if (petAPI.numberOfDogs() > 0) {
            int index = ScannerInput.readNextInt("Enter index of Dog to Update: ");
            String breed = ScannerInput.readNextLine("Enter new Breed: ");
            boolean isDangerous = ScannerInput.readNextLine("Is it a dangerous breed? (y/n): ").equalsIgnoreCase("y");


            return petAPI.updateDog(index, breed, isDangerous);
        }
        return false;
    }

    private boolean updateCat() {
        System.out.println(petAPI.listCats());
        if (petAPI.numberOfCats() > 0) {
            int index = ScannerInput.readNextInt("Enter index of Cat to Update: ");
            String favouriteToy = ScannerInput.readNextLine("Enter new Favourite Toy: ");
            boolean isIndoor = ScannerInput.readNextLine("Is it indoor? (y/n): ").equalsIgnoreCase("y");

            return petAPI.updateCat(index, favouriteToy, isIndoor);
        }
        return false;
    }

    private boolean updateParrot() {
        System.out.println(petAPI.listParrots());
        if (petAPI.numberOfParrots() > 0) {
            int index = ScannerInput.readNextInt("Enter index of Parrot to Update: ");
            String vocabSize = ScannerInput.readNextLine("Enter new Vocabulary Size: ");


            return petAPI.updateParrot(index, vocabSize);
        }
        return false;
    }


    //------------------------------------------------------------------------------------------
//  Option 3 - Delete Posts - if posts exist, print all posts and ask the user to input the index
//             of the post they wish to delete.
//------------------------------------------------------------------------------------------
    private void deletePet() {
        showPets();
        if (petAPI.numberOfPets() > 0) {
            //only ask the user to choose the message post to delete if posts exist
            int indexToDelete = ScannerInput.readNextInt("Enter the index of the pet to delete ==> ");
            //pass the index of the message post to NewsFeed for deleting and check for success.
            Pet petToDelete = petAPI.deletePet(indexToDelete);
            if (petToDelete != null) {
                System.out.println("Delete Successful! Deleted pet: " + petToDelete.toString());
            } else {
                System.out.println("Delete NOT Successful");
            }
        }
    }

//---------------------------------------------------------------------
//  Option 4 - List Posts
//---------------------------------------------------------------------

    //The user is asked if they want to view all posts, or just the messages or photos ones.
    private void viewPets() {
        if (petAPI.numberOfPets() > 0) {
            int option = ScannerInput.readNextInt("""
                    ---------------------------
                    |   1) View ALL Pets    |
                    |   2) View All Dogs |
                    |   3) View All Cats   |
                    |   4) View All Parrots   |
                    ---------------------------
                    ==>>  """);

            switch (option) {
                case 1 -> showPets();
                case 2 -> showDogs();
                case 3 -> showCats();
                case 4 -> showParrots();
                default -> System.out.println("Invalid option entered: " + option);
            }
        } else {
            System.out.println("Option Invalid - No pets stored");
        }
    }

    //print all the posts in newsfeed i.e. array list.
    private void showPets() {
        System.out.println("List of All Pets are:");
        System.out.println(petAPI.listPets());
    }

    //print the message posts in newsfeed i.e. array list.
    private void showDogs() {
        System.out.println("List of Dogs are:");
        System.out.println(petAPI.listDogs());
    }

    //print the photo posts in newsfeed i.e. array list.
    private void showCats() {
        System.out.println("List of Cats are:");
        System.out.println(petAPI.listCats());
    }

    //print the photo posts in newsfeed i.e. array list.
    private void showParrots() {
        System.out.println("List of Parrots are:");
        System.out.println(petAPI.listParrots());
    }
/*
//------------------------------------------------------------------------------------------
//  Option 5 - Like / Unlike Posts - the user is asked if it is a message or a photo post
//             and the required details are then gathered before adding the specific object
//------------------------------------------------------------------------------------------
private void likeUnlikePosts() {

    int likeOption = ScannerInput.readNextInt("""
            ---------------------------
            | Do you want to...       |
            |   1) Like A post        |
            |   2) Unlike a post      |
            ---------------------------
            ==>> """);

    switch (likeOption) {
        case 1 -> {
            showMessagePosts();
            showPhotoPosts();
            int index = ScannerInput.readNextInt("Enter the index of the post ==> ");
            newsFeed.likeAPost(index);
            System.out.println(newsFeed.findPost(index).display());
        }
        case 2 -> {
            showMessagePosts();
            showPhotoPosts();
            int index = ScannerInput.readNextInt("Enter the index of the post ==> ");
            newsFeed.unLikeAPost(index);
            System.out.println(newsFeed.findPost(index).display());
        }
        default -> System.out.println("Invalid option entered: " + likeOption);
    }

}*/


//---------------------------------------------------------------------
//  Options 6 and 7 - Save and Load Posts
//---------------------------------------------------------------------

    //save all the posts in the newsFeed to a file on the hard disk
    private void save() {
        try {
            petAPI.save();
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e);
        }
    }

    //load all the posts into the newsFeed from a file on the hard disk
    private void load() {
        try {
            petAPI.load();
        } catch (Exception e) {
            System.err.println("Error reading from file: " + e);
        }
    }

}
