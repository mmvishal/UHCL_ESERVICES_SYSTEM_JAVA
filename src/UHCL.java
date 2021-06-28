import java.util.*;
import java.io.*;


public class UHCL
{
    public static ArrayList<Course> allCourses = new ArrayList<Course>();
    public static ArrayList<User> allUsers = new ArrayList<User>();
    public static ArrayList<Faculty> allFaculty = new ArrayList<Faculty>();
    public static ArrayList<Student> allStudents = new ArrayList<Student>();

    public static void main(String args[])
    {
        Course isam5638 = new Course("ISAM 5638", "Dr. Lin", "MIS");
        Course isam5735 = new Course("ISAM 5735", "Dr. Lin", "MIS");
        Course isam5331 = new Course("ISAM 5331", "Dr. Saleem", "MIS");
        Course acct6200 = new Course("ACCT 6200", "Dr. Lehman", "Accounting");

        allCourses.add(isam5638);
        allCourses.add(isam5735);
        allCourses.add(isam5331);
        allCourses.add(acct6200);

        Faculty lin = new Faculty("Dr. Lin", "MIS", "lin", "1234");
        Faculty saleem = new Faculty("Dr. Saleem","MIS","saleem","1234");
        Faculty lehman = new Faculty("Dr. Lehman","Accounting","lehman","1234");

        allUsers.add(lin);
        allUsers.add(saleem);
        allUsers.add(lehman);

        allFaculty.add(lin);
        allFaculty.add(saleem);
        allFaculty.add(lehman);

        Student jean = new Student("Jean", "MIS", "jean", "4321");
        Student jack = new Student("Jack", "Accounting", "jack", "4321");
        Student mike = new Student("Mike", "MIS", "mike", "4321");
        Student joan = new Student("joan", "MIS", "joan", "4321");
       
        allUsers.add(jean);
        allUsers.add(jack);
        allUsers.add(mike);
        allUsers.add(joan);

        allStudents.add(jean);
        allStudents.add(jack);
        allStudents.add(mike);
        allStudents.add(joan);

        lin.getCourses().add(isam5638);
        lin.getCourses().add(isam5735);
        saleem.getCourses().add(isam5331);
        lehman.getCourses().add(acct6200);

        Faculty logged_in_faculty = null;
        Student logged_in_student = null;
        User logged_in_user = null;

        Course course;
        Course selected_course;

        char choice = ' ';
        char eService_choice;
        String course_choice;
        char blackboard_choice;
        String blackboard_course_choice;
        char notes_choice = ' ';


        String login_id;
        String password;



        boolean login_success = false;

        Scanner s = new Scanner(System.in);

        while(choice != 'x')
        {
            System.out.print("Go Hawks!\n");
            System.out.print("1 : e-Service\n");
            System.out.print("2 : Blackboard\n");
            System.out.print("x : Leave\n");

            choice = s.next().charAt(0);

            //e-service
            if(choice == '1')
            {
                System.out.print("Please enter your login ID : \n");
                login_id = s.next();
                System.out.print("Please enter your password : \n");
                password = s.next();

                for(int i = 0; i < allUsers.size() && login_success == false ; i++)
                {
                    if(allUsers.get(i).user_name_of_user.equals(login_id) && allUsers.get(i).password_of_user.equals(password) )
                    {
                        login_success = true;
                        logged_in_user = allUsers.get(i);
                        if(allUsers.get(i).user_type == 'F')
                        {
                            for(int j = 0; j < allFaculty.size(); j++)
                                if (allFaculty.get(j).name_of_user.equals(allUsers.get(i).name_of_user))
                                    logged_in_faculty = allFaculty.get(j);
                        }
                        else
                        {
                            for(int j = 0; j < allStudents.size(); j++)
                                if (allStudents.get(j).name_of_user.equals(allUsers.get(i).name_of_user))
                                    logged_in_student = allStudents.get(j);
                        }

                    }
                    else
                        login_success = false;
                }

                if(login_success)
                {
                    if(logged_in_user.user_type == 'F')
                    {
                        while(login_success)
                        {
                            System.out.println("Welcome to UHCL eService : ");
                            System.out.print("v : view my courses\n");
                            System.out.print("x : Logout\n");

                            eService_choice = s.next().charAt(0);

                            if(eService_choice == 'v')
                            {
                                for(int i = 0; i < allCourses.size(); i++)
                                {
                                    course = allCourses.get(i);
                                    if(course.facutly_name.equals(logged_in_faculty.name_of_user))
                                    {
                                        System.out.println(course.course_name);
                                        System.out.println("Students enrolled : ");
                                        course.print_enrolled_students();
                                    }
                                }
                            }
                            else if(eService_choice == 'x')
                            {
                                login_success = false;
                            }
                        }
                    }
                    else if(logged_in_user.user_type == 'S')
                    {
                        while(login_success)
                        {
                            System.out.println("Welcome to UHCL eService :");
                            System.out.print("v : view my courses\n");
                            System.out.print("r : register a course\n");
                            System.out.print("x : Logout\n");

                            eService_choice = s.next().charAt(0);

                            if(eService_choice == 'v')
                                logged_in_student.print_registered_courses();
                            else if(eService_choice == 'r')
                            {
                                int i;
                                ArrayList<Integer> courses_available = new ArrayList<Integer>();
                                courses_available = logged_in_student.courses_available_for_registration(allCourses);
                                if(courses_available.size() != 0)
                                {
                                    System.out.println("Welcome to register for a new course!");
                                    System.out.println("These are the courses available to you : ");
                                    for(i = 0; i < courses_available.size(); i++)
                                    {
                                        System.out.print(i+1);
                                        System.out.print(": " + allCourses.get(courses_available.get(i)).course_name + "\n");
                                    }
                                    System.out.println("\nOr any other key to exit");

                                    course_choice = s.next();

                                    if(Integer.parseInt(course_choice) >= 1 && Integer.parseInt(course_choice) <= courses_available.size())
                                    {
                                        logged_in_student.register_the_course(allCourses.get(courses_available.get( (Integer.parseInt(course_choice) - 1) ) ) );
                                        allCourses.get(courses_available.get( (Integer.parseInt(course_choice) - 1) ) ).enroll_student(logged_in_user.name_of_user);
                                        System.out.println("The course is added to your schedule");
                                    }

                                }
                                else
                                {
                                    System.out.println("You have no courses for further Registration");
                                }
                            }
                            else if(eService_choice == 'x')
                            {
                                login_success = false;
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Alert: Incorrect Login ID or Password! \n");
                }
            }

            //BlackBoard
            else if(choice == '2')
            {
                System.out.print("Please enter your login ID : \n");
                login_id = s.next();
                System.out.print("Please enter your password : \n");
                password = s.next();

                for(int i = 0; i < allUsers.size() && login_success == false ; i++)
                {
                    if(allUsers.get(i).user_name_of_user.equals(login_id) && allUsers.get(i).password_of_user.equals(password) )
                    {
                        login_success = true;
                        logged_in_user = allUsers.get(i);
                        if(allUsers.get(i).user_type == 'F')
                        {
                            for(int j = 0; j < allFaculty.size(); j++)
                                if (allFaculty.get(j).name_of_user.equals(allUsers.get(i).name_of_user))
                                    logged_in_faculty = allFaculty.get(j);
                        }
                        else
                        {
                            for(int j = 0; j < allStudents.size(); j++)
                                if (allStudents.get(j).name_of_user.equals(allUsers.get(i).name_of_user))
                                    logged_in_student = allStudents.get(j);
                        }

                    }
                    
                    else
                    {
                        login_success = false;
                    }
                }    


                if(login_success)
                {
                    if(logged_in_user.user_type == 'F')
                    {
                        while(login_success)
                        {
                            System.out.println("Welcome to UHCL Blackboard!");
                            System.out.println("Select your course:");

                            ArrayList<Integer> index = new ArrayList<Integer>();

                            int count = 0;
                            for(int i = 0; i < allCourses.size(); i++)
                            {
                                course = allCourses.get(i);
                                if(course.facutly_name.equals(logged_in_faculty.name_of_user))
                                {
                                    count++;
                                    System.out.print(count);
                                    System.out.print(":" + course.course_name + "\n");
                                    index.add(i);
                                }
                            }
                            System.out.println("x: leave Blackboard");
                            
                            blackboard_course_choice = s.next();


                            if(blackboard_course_choice.equals("x"))
                            {
                                login_success = false;
                            }     
                            else if(blackboard_course_choice.matches("\\d+"))
                            {
                                if(Integer.parseInt(blackboard_course_choice) >=1 && Integer.parseInt(blackboard_course_choice) <= count)
                                {
                                    selected_course = allCourses.get(index.get(Integer.parseInt(blackboard_course_choice)-1));
                                    notes_choice = ' '; 
                                    while(notes_choice != 'x')
                                    {
                                        System.out.println("Please select your options:");
                                        System.out.print("v : view my course notes\n");
                                        System.out.print("p : post new course note\n");
                                        System.out.print("x : leave the course\n");

                                        notes_choice = s.next().charAt(0);

                                        if(notes_choice == 'v')
                                        {
                                            selected_course.view_notes();
                                        }
                                        else if(notes_choice == 'p')
                                        {
                                            selected_course.post_notes();
                                        }
                                    }
                                } 
                            }
                        }
                    }

                    else if(logged_in_user.user_type == 'S')
                    {
                        while(login_success)
                        {
                            System.out.println("Welcome to UHCL Blackboard!");
                            if(logged_in_student.registered_courses.size() == 0)
                            {
                                System.out.println("You have no Registered courses");
                                System.out.println("Register for courses and come back later!");
                                System.out.println("Logging you off! have a nice day");
                                login_success = false;

                            }
                            else
                            {
                                System.out.println("Select your course:");

                                ArrayList<Integer> index = new ArrayList<Integer>();

                                int count = 0;
                                for(int i = 0; i < logged_in_student.registered_courses.size(); i++)
                                {
                                    course = logged_in_student.registered_courses.get(i);
                                    if(course.course_major.equals(logged_in_student.major_of_user))
                                    {
                                        count++;
                                        System.out.print(count);
                                        System.out.print(":" + course.course_name + "\n");
                                        index.add(i);
                                    }
                                }
                                System.out.println("x: leave Blackboard");
                                
                                blackboard_course_choice = s.next();

                                if(blackboard_course_choice.equals("x"))
                                {
                                    login_success = false;
                                }     
                                else if(blackboard_course_choice.matches("\\d+"))
                                {
                                    if(Integer.parseInt(blackboard_course_choice) >=1 && Integer.parseInt(blackboard_course_choice) <= count)
                                    {
                                        selected_course = allCourses.get(index.get(Integer.parseInt(blackboard_course_choice)-1));
                                        notes_choice = ' '; 
                                        while(notes_choice != 'x')
                                        {
                                            System.out.println("Please select your options:");
                                            System.out.print("v : view course notes\n");
                                            System.out.print("x : leave the course\n");

                                            notes_choice = s.next().charAt(0);

                                            if(notes_choice == 'v')
                                            {
                                                selected_course.view_notes();
                                            }
                                        }
                                    } 
                                }
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Alert: Incorrect Login ID or Password! \n");
                }
            }
        }

    }
}