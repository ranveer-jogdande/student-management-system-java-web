import java.io.*;
import java.util.*;

public class StudentDatabase {

    static String myfile = "students.csv";

    public static void addStudent(Student s) {
        try {
            FileWriter fw = new FileWriter(myfile, true);
            fw.write(s.toString() + "\n");
            fw.close();
        } catch(Exception e){
            System.out.println("error while writing: " + e);
        }
    }
public static List<Student> getAll() {
    List<Student> l = new ArrayList<>();
    try {
        BufferedReader br = new BufferedReader(new FileReader(myfile));
        String line;
        while((line = br.readLine()) != null){
            line = line.trim();
            if(line.length() == 0) continue; // skip empty lines

            // split by comma or tab (and ignore extra spaces)
            String[] arr = line.split("\\s*,\\s*|\\t+");
            if(arr.length < 3) continue; // skip malformed lines

            String name = arr[0].trim();
            int roll = 0;
            try { roll = Integer.parseInt(arr[1].trim()); } catch(Exception e){ continue; }
            String grade = arr[2].trim();

            l.add(new Student(name, roll, grade));
        }
        br.close();
    } catch(Exception e){
        System.out.println("cant read file: " + e);
    }
    return l;
}

    public static void deleteStudent(int roll) {
    List<Student> list = getAll();
    try {
        FileWriter fw = new FileWriter(myfile, false);
        for(Student s : list){
            if(s.roll != roll){
                fw.write(s.toString() + "\n");
            }
        }
        fw.close();
    } catch(Exception e){
        System.out.println("error deleting: " + e);
    }
}

}
