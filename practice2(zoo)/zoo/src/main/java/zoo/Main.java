package zoo;

public class Main {

    public static void main(String[] argv) {

        String fileFormat = null;
        String filePath = null;

        for (String arg : argv) {
            String[] args = arg.split("=");
            switch (args[0]) {
                case "-configtype":
                    fileFormat = args[1];
                    break;
                case "-configfile":
                    filePath = args[1];
                    break;
                default:
                    throw new IllegalArgumentException("wrong argument format '");
            }
        }

        // Create zoo
        Zoo zoo = new Zoo();
        // Add animals to the zoo
        zoo.addAnimals(fileFormat, filePath);

        // Create user action trigger
        ActionTrigger trigger = new ActionTrigger(zoo);

        AnimalType herbivore = AnimalType.HERBIVORE;
        AnimalType carnivore = AnimalType.CARNIVORE;

        // All of the following actions are up to users choice
        zoo.printAllStates();
        trigger.setMorning();
        zoo.printAllStates();

        trigger.visit(herbivore);
        zoo.printAllStates();
//        trigger.visit(carnivore);
        trigger.feedAnimals(herbivore);
        zoo.printAllStates();

        trigger.setNight();
        zoo.printAllStates();

        trigger.setMorning();
        zoo.printAllStates();

        trigger.setThunder();
        zoo.printAllStates();
        trigger.feedAnimals(carnivore);
        zoo.printAllStates();

        trigger.feedAnimals(herbivore);
        zoo.printAllStates();
        trigger.setNight();
        zoo.printAllStates();

        trigger.setMorning();
        zoo.printAllStates();

        trigger.setRain();
        zoo.printAllStates();

        trigger.drinkAnimals(carnivore);
        zoo.printAllStates();
    }
}