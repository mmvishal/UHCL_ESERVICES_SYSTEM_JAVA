import java.util.*;


public class Student extends User
{
    public ArrayList<Course> registered_courses = new ArrayList<Course>();

    public Student(String name, String user_major, String user_name, String password)
    {
        super(name, user_major, user_name, password, 'S');
    }


    public void print_registered_courses()
    {
        int registered_courses_length = registered_courses.size();
        if(registered_courses_length == 0)
            System.out.print("You do not have any course registered\n");
        else
        {
            for(int i = 0; i < registered_courses_length; i++)
                System.out.print(registered_courses.get(i).course_name + ", Instructor : " + registered_courses.get(i).facutly_name + "\n");
			   
        }
    }

    public boolean not_registered(String name_of_course_for_registration_check)
    {
        int registered_courses_length = registered_courses.size();
        for(int i = 0; i < registered_courses_length; i++)
            if(registered_courses.get(i).course_name.equals(name_of_course_for_registration_check))
                return false;
        return true;
    }

    public ArrayList<Integer> courses_available_for_registration(ArrayList<Course> all_courses)
    {
        int all_courses_length = all_courses.size();
        ArrayList<Integer> available_courses = new ArrayList<Integer>();
        for(int i=0; i < all_courses_length; i++)
        {
            if(all_courses.get(i).course_major.equals(major_of_user))
            {
                if(not_registered(all_courses.get(i).course_name))
                    available_courses.add(i);
            }
        }
        return available_courses;
    }

    public void register_the_course(Course course_to_be_registered)
    {
        registered_courses.add(course_to_be_registered);
    }
}