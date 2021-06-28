import java.util.*;

public class User
{

    public String name_of_user;
    public String major_of_user;
    public String user_name_of_user;
    public String password_of_user;
    public char user_type;

    public User(String name, String user_major, String user_name, String password, char type)
    {
        name_of_user = name;
        major_of_user = user_major;
        user_name_of_user = user_name;
        password_of_user= password;
        user_type = type;
    }
}