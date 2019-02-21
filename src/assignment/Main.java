package assignment;

public class Main {

    public static void main(String[] args) {
        new NamePrinter().printNames();
    }
}

class NamePrinter {
    /**
     * Prints the names of the group members separated by "-".
     */ 
    public void printNames() {
        String separator = ",";

        String[] names = {
                "Shannen Barrameda",
                "Matthew Kunjummen",
                "Aneesh Saripalli",
                "Name 4",
                "Name 5"};

        System.out.println(String.join(separator, names));
    }
}
