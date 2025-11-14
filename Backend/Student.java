public class Student {
    public String name;
    public int roll;
    public String grade;

    public Student(String n, int r, String g){
        name = n;
        roll = r;
        grade = g;
    }

    public String toString(){
        return name + "," + roll + "," + grade;
    }
}
