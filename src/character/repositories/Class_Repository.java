package character.repositories;

import character.data.interfaces.IDB;
import character.entities.Ability;
import character.entities.Class_;
import character.repositories.interfaces.IClass_Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Class_Repository implements IClass_Repository {
    private final IDB db;
    public Class_Repository(IDB db){
        this.db = db;
    }

    @Override
    public boolean createClass_(Class_ class_){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO class_ (name, description, health) VALUES (?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, class_.getName());
            st.setString(2, class_.getDescription());
            st.setDouble(3, class_.getHealth());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Class_ getClass_(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "Select id, name, description, health FROM class_ WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Class_ class_ = new Class_(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("health"));
                return class_;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean changeClass_(int id, String name, String description, double health){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE class_ SET ";
            boolean temp = false;
            if (name != null){
                sql = sql.concat("name = " + "\'" + name + "\'");
                temp = true; }
            if (description != null){
                if (temp){ sql = sql.concat(", description = " + "\'" + description + "\'");
                } else { sql = sql.concat("description = " + "\'" + description + "\'"); }
                temp = true;
            }
            if (health != 0){
                if (temp){ sql = sql.concat(", health = " + health);
                } else { sql = sql.concat("health = " + health); }
            }

            sql = sql.concat(" WHERE id = " + id);
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteClass_(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM class_ WHERE id = " + id;
            Statement st = con.createStatement();
            st.executeUpdate(sql);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Class_> getAllClass_(){
        Connection con = null;
        try {
            con = db.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("Select *  FROM class_");
            List<Class_> class_s = new LinkedList<>();
            while (rs.next()){
                Class_ class_ = new Class_(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("health"));
                class_s.add(class_);
            }
            return class_s;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
