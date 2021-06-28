import java.util.*;

public class Faculty extends User
{
    private ArrayList<Course> courses_undertaken_by_faculty = new ArrayList<Course>();
    
    public Faculty(String name, String user_major, String user_name, String password)
    {
        super(name, user_major, user_name, password, 'F');
    }

    public ArrayList<Course> getCourses()
    {
        return courses_undertaken_by_faculty;
    }
}