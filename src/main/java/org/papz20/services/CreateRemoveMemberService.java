package main.java.org.papz20.services;

import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.User;


public class CreateRemoveMemberService {
    private final Database database;

    public CreateRemoveMemberService() { this.database = Database.getInstance(); }

    public boolean addUser(User new_user){
        int user_id = new_user.getId();
        String username = new_user.getUsername();
        String password = new_user.getPassword();
        String first_name = new_user.getFirstName();
        String last_name = new_user.getLastName();
        String email = new_user.getEmail();
        String role = new_user.getRole();
        try {
            database.addUser(user_id, username,password, first_name, last_name, email, role);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addMember(String username, String password, String first_name, String last_name, String email){
        try{
            int user_id = database.getNextId("users");
            database.addUser(user_id, username, password, first_name, last_name, email, "member");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAdmin(String username, String password, String first_name, String last_name, String email){
        try{
            int user_id = database.getNextId("users");
            database.addUser(user_id, username, password, first_name, last_name, email, "admin");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeMember(int user_id){
        try{
            database.removeUser(user_id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeMember(String username){
        try{
            int user_id = database.getUsernameId(username);
            database.removeUser(user_id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
