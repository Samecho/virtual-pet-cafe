package ui;

import model.Cafe;
import model.Pet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
// Represents the pet cafe application's user interface
public class CafeApp {
    private static final String JSON_STORE = "./data/cafe.json";
    private Cafe cafe;
    private Scanner input;
    private JsonWriter jsonWriter; 
    private JsonReader jsonReader;

    // EFFECTS: runs the cafe application
    public CafeApp() {
        
        cafe = new Cafe();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE); 
        jsonReader = new JsonReader(JSON_STORE); 
        runCafe();
    }

    // MODIFIES: this
    // EFFECTS: processes user input until they quit the application
    private void runCafe() {
        boolean keepGoing = true;
        String command;

        askToLoad();

        while (keepGoing) {
            displayMenu();
            command = input.next().toLowerCase();

            if (command.equals("q")) {
                askToSave();
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }

    // EFFECTS: displays the main menu of options to the user
    private void displayMenu() {
        System.out.println("Select from:");
        System.out.println("a: Adopt new pet");
        System.out.println("v: View pets");
        System.out.println("i: Interact with a pet");
        System.out.println("r: Remove a pet");
        System.out.println("s: View cafe statistics");
        System.out.println("sa: Save cafe to file"); 
        System.out.println("lo: Load cafe from file");
        System.out.println("q: Quit");
    }

    // MODIFIES: this
    // EFFECTS: processes the user's command
    @SuppressWarnings("methodlength")
    private void processCommand(String command) {
        switch (command) {
            case "a":
                doAdoptPet();
                break;
            case "v":
                doViewPets();
                break;
            case "i":
                doInteractWithPet();
                break;
            case "r":
                doRemovePet();
                break;
            case "s":
                doShowStats();
                break;
            case "sa": 
                saveCafe();
                break;
            case "lo": 
                loadCafe();
                break;
            default:
                System.out.println("Hmm, it seems that's not a valid command.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user for pet details and adds it to the cafe
    private void doAdoptPet() {
        System.out.print("Enter pet's name: ");
        String name = input.next();
        System.out.print("Enter pet's species: ");
        String species = input.next();

        Pet newPet = new Pet(name, species);
        cafe.adoptPet(newPet);
        System.out.println(name + " the " + species + " has been adopted into the cafe.");
    }

    // EFFECTS: displays all pets in the cafe with their status
    private void doViewPets() {
        List<Pet> pets = cafe.getPets();
        if (pets.isEmpty()) {
            System.out.println("The cafe is currently empty.");
            return;
        }
        System.out.println("Pets in the cafe:");
        for (Pet p : pets) {
            String status = "Health: " + p.getHealth() + ", Happiness: " + p.getHappiness()
                    + ", Hunger: " + p.getHunger() + ", Stamina: " + p.getStamina();
            System.out.println(p.getName() + " (" + p.getSpecie() + "): " + status);
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the entire process of selecting and interacting with a pet
    private void doInteractWithPet() {
        Pet selectedPet = selectPet();
        if (selectedPet == null) {
            return;
        }

        displayInteractionMenu(selectedPet);
        String command = input.next();
        handleInteractionCommand(selectedPet, command);
    }

    // EFFECTS: displays the interaction menu for a given pet
    private void displayInteractionMenu(Pet p) {
        System.out.println("Interacting with " + p.getName() + ":");
        System.out.println("f: Feed pet");
        System.out.println("p: Play with pet");
        System.out.println("s: Let pet sleep");
        System.out.println("m: Give medicine");
    }

    // MODIFIES: selectedPet
    // EFFECTS: performs an action on the selected pet based on user command
    private void handleInteractionCommand(Pet selectedPet, String command) {
        switch (command.toLowerCase()) {
            case "f":
                selectedPet.feed();
                System.out.println(selectedPet.getName() + " was fed.");
                break;
            case "p":
                selectedPet.play();
                System.out.println(selectedPet.getName() + " was played with.");
                break;
            case "s":
                selectedPet.sleep();
                System.out.println(selectedPet.getName() + " was put to sleep.");
                break;
            case "m":
                selectedPet.eatPill();
                System.out.println(selectedPet.getName() + "'s illness was treated.");
                break;
            default:
                System.out.println("Hmm, it seems that's not a valid action.");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: handles the process of selecting and removing a pet from the cafe
    private void doRemovePet() {
        Pet selectedPet = selectPet();
        if (selectedPet != null) {
            cafe.removePet(selectedPet);
            System.out.println(selectedPet.getName() + " has been removed from the cafe.");
        }
    }

    // EFFECTS: displays key statistics about the cafe
    private void doShowStats() {
        System.out.println("Cafe Statistics:");
        System.out.println("Current number of pets: " + cafe.getPetCount());
        System.out.println("Total pets adopted: " + cafe.getAdoptedCount());

        List<Pet> lowHealthPets = cafe.getPetsWithLowHealth();
        System.out.println("Pets with low health: " + lowHealthPets.size());
        if (!lowHealthPets.isEmpty()) {
            for (Pet p : lowHealthPets) {
                System.out.println(p.getName() + " (Health: " + p.getHealth() + ")");
            }
        }
    }

    // EFFECTS: displays a list of pets and returns the pet selected by the user,
    //          or null if no pet is selected or the list is empty
    private Pet selectPet() {
        List<Pet> pets = cafe.getPets();
        if (pets.isEmpty()) {
            System.out.println("There are no pets to select.");
            return null;
        }

        System.out.println("Select a pet by number:");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println((i + 1) + ": " + pets.get(i).getName());
        }

        try {
            int selection = input.nextInt();
            input.nextLine(); 

            if (selection > 0 && selection <= pets.size()) {
                return pets.get(selection - 1);
            } else {
                System.out.println("Hmm, it seems that number is not on the list.");
                return null;
            }
        } catch (InputMismatchException e) {
            input.nextLine();
            System.out.println("Hmm, it seems that wasn't a number.");
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: Ask user if last data should be loaded
    private void askToLoad() {
        System.out.println("Hello, do you wanna load previous Cafe? (y/n)");
        String command = input.next();
        if (command.equals("y")) {
            loadCafe();
        }
    }

    // MODIFIES: this
    // EFFECTS: Ask user to save current Cafe 
    private void askToSave() {
        System.out.println("Hello, do you want to save your Cafe? (y/n)");
        String command = input.next();
        if (command.equals("y")) {
            saveCafe();
        }
    }

    // EFFECTS: Save stats of Cafe to Json file
    private void saveCafe() {
        try {
            jsonWriter.open();
            jsonWriter.write(cafe);
            jsonWriter.close();
            System.out.println("Saved succefully to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot be saved to " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Load Cafe's stats from Json file
    private void loadCafe() {
        try {
            cafe = jsonReader.read();
            System.out.println("Loaded succefully from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Cannot load from: " + JSON_STORE);
        }
    }
}