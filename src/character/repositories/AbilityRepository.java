package character.repositories;

import character.data.interfaces.IDB;
import character.entities.*;
import character.repositories.interfaces.IAbilityRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AbilityRepository implements IAbilityRepository {
    private final IDB db;
    public AbilityRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean createAbility(Ability ability){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO ability (name, description) VALUES (?, ?);";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, ability.getName());
            st.setString(2, ability.getDescription());

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
    public Ability getAbility(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "Select id, name, description FROM ability WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Ability ability = new Ability(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"));
                return ability;
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
    public boolean changeAbility(int id, String name, String description){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE ability SET ";
            boolean temp = false;
            if (name != null){
                sql = sql.concat("name = " + "\'" + name + "\'");
                temp = true; }
            if (description != null){
                if (temp){ sql = sql.concat(", description = " + "\'" + description + "\'");
                } else { sql = sql.concat("description = " + "\'" + description + "\'"); }
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
    public boolean deleteAbility(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM ability WHERE id = " + id;
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
    public List<Ability> getAllAbility(){
        Connection con = null;
        try {
            con = db.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("Select *  FROM ability");
            List<Ability> abilities = new LinkedList<>();
            while (rs.next()){
                Ability ability = new Ability(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"));
                abilities.add(ability);
            }
            return abilities;
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
