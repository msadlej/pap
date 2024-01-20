package main.java.org.papz20.services;
import main.java.org.papz20.model.Database;
import main.java.org.papz20.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ChangeUserInfoService {
    private final Database database;

    public ChangeUserInfoService() {
        this.database = new Database();
    }

    public boolean ChangeUserPassword(String username, String new_password){
        int user_id = database.getUsernameId(username);
        try{
            User target_user = database.fetchUser(user_id);
            target_user.setPassword(new_password);
            database.updateUserData(target_user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean ChangeUserUsername(String old_username, String new_username){
        int user_id = database.getUsernameId(old_username);
        try{
            User target_user = database.fetchUser(user_id);
            target_user.setUsername(new_username);
            database.updateUserData(target_user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean ChangeUserFirstName(String username, String new_first_name){
        int user_id = database.getUsernameId(username);
        try{
            User target_user = database.fetchUser(user_id);
            target_user.setFirstName(new_first_name);
            database.updateUserData(target_user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean ChangeUserLastName(String username, String new_last_name){
        int user_id = database.getUsernameId(username);
        try{
            User target_user = database.fetchUser(user_id);
            target_user.setLastName(new_last_name);
            database.updateUserData(target_user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean ChangeUserEmail(String username, String new_email){
        int user_id = database.getUsernameId(username);
        try{
            User target_user = database.fetchUser(user_id);
            target_user.setEmail(new_email);
            database.updateUserData(target_user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
