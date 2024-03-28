package character.repositories;

import character.data.interfaces.IDB;
import character.entities.Class_;
import character.entities.Clothes;
import character.entities.Weapon;
import character.repositories.interfaces.IClass_Repository;
import character.repositories.interfaces.IWeaponRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class WeaponRepository implements IWeaponRepository {
    private final IDB db;
    public WeaponRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean createWeapon(Weapon weapon){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO weapon (name, description, dps, class_id) VALUES (?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, weapon.getName());
            st.setString(2, weapon.getDescription());
            st.setDouble(3, weapon.getDps());
            st.setInt(4, weapon.getClass_().getId());

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
    public Weapon getWeapon(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "Select id, name, description, dps, class_id FROM weapon WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                String sql2 = "Select id, name, description, health FROM class_ WHERE id = ?";
                PreparedStatement st2 = con.prepareStatement(sql2);
                st2.setInt(1, rs.getInt("class_id"));
                ResultSet rs2 = st2.executeQuery();
                Class_ class_ = null;
                if (rs2.next()) {
                    class_ = new Class_(
                            rs2.getInt("id"),
                            rs2.getString("name"),
                            rs2.getString("description"),
                            rs2.getDouble("health"));
                }

                Weapon weapon = new Weapon(
                        id,
                        rs.getString("name"),
                        rs.getString("description"),
                        class_,
                        rs.getDouble("dps"));
                return weapon;
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
    public boolean changeWeapon(int id, String name, String description, Class_ class_, double dps){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE weapon SET ";
            boolean temp = false;
            if (name != null){
                sql = sql.concat("name = " + "\'" + name + "\'");
                temp = true; }
            if (description != null){
                if (temp){ sql = sql.concat(", description = " + "\'" + description + "\'");
                } else { sql = sql.concat("description = " + "\'" + description + "\'"); }
                temp = true;
            }
            if (dps != 0){
                if (temp){ sql = sql.concat(", dps = " + dps);
                } else { sql = sql.concat("dps = " + dps); }
                temp = true;
            }
            if (class_ != null){
                if(temp){ sql = sql.concat(", class_id = " + class_.getId());
                } else { sql = sql.concat("class_id = " + class_.getId()); }
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
    public boolean deleteWeapon(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM weapon WHERE id = " + id;
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
    public List<Weapon> getAllWeapon(){
        Connection con = null;
        try {
            con = db.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("Select *  FROM weapon");
            List<Weapon> weapons = new LinkedList<>();

            IClass_Repository classRepo = new Class_Repository(db);
            List<Class_> class_s = classRepo.getAllClass_();

            while (rs.next()){
                Class_ class_ = null;
                for (int i=0; i< class_s.size(); i++){
                    if (class_s.get(i).getId() == rs.getInt("class_id")){
                        class_ = class_s.get(i);
                    }
                }
                Weapon weapon = new Weapon(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        class_,
                        rs.getDouble("dps"));
                weapons.add(weapon);
            }
            return weapons;
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
