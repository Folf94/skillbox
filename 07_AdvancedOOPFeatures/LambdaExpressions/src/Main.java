import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) throws ParseException {
        ArrayList<Employee> staff = loadStaffFromFile();

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date startDate = formatter.parse("01.01.2017");
        Date endDate = formatter.parse("31.12.2017");

        Stream<Employee> stream = staff.stream();
        stream.filter(employee -> employee.getWorkStart().after(startDate)).filter(employee ->
                employee.getWorkStart().before(endDate)).max(Comparator.comparing(Employee::getSalary)).ifPresent(System.out::println);

        /*
        staff.sort((e1, e2) -> {
            int numberSorting = e1.getSalary().compareTo(e2.getSalary());
            if(numberSorting != 0){
                return numberSorting;

            }
            return e1.getName().compareTo(e2.getName());
        });


        staff.sort((o1, o2) -> {
            if (o1.getSalary().compareTo(o2.getSalary()) == 0) {
                o1.getName().compareTo(o2.getName());
                return o1.getName().compareTo(o2.getName());
            }
            return o1.getSalary().compareTo(o2.getSalary());
        });




        for (Employee employee : staff) {
            System.out.println(employee);
        }
         */

    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(fragments[0], Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}