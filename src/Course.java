import java.util.*;

public class Course
{
    public String  course_name;
    public String  facutly_name;
    public String  course_major;
    public ArrayList<String> enrolled_students_names = new ArrayList<String>();
    public ArrayList<String> notes = new ArrayList<String>();
    public ArrayList<String> time = new ArrayList<String>();
    public String status;

    public Course(String course, String facutly, String major)
    {
        course_name = course;
        facutly_name = facutly;
        course_major = major;
        status = "OPEN";
    }
    
    public void print_enrolled_students()
    {
        for(int i = 0; i < enrolled_students_names.size(); i++)
            System.out.println(enrolled_students_names.get(i));
    }

    public void enroll_student(String student_name)
    {
        enrolled_students_names.add(student_name);
    }

    public void view_notes()
    {
        System.out.println("Course Notes of " + this.course_name);
        if(notes.size() == 0)
        {
            System.out.println("None");
        }
        else
        {
            for(int i = 0; i < notes.size(); i++)  
            {
                System.out.println(time.get(i) + ": " +notes.get(i));

            }
        }
    }

    public void post_notes()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your new note:");
        notes.add(s.nextLine());
        time.add(DateAndTime.DateTime());
        System.out.println("Your note is added to the course. Your students can see it now");
    }

}