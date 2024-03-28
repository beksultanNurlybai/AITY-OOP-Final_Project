package character.repositories;

import character.data.interfaces.IDB;
import character.entities.Class_;
import character.entities.Clothes;
import character.repositories.interfaces.IClass_Repository;
import character.repositories.interfaces.IClothesRepository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ClothesRepository implements IClothesRepository {
    private final IDB db;
    public ClothesRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean createClothes(Clothes clothes){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO clothes (name, description, armor, class_id) VALUES (?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, clothes.getName());
            st.setString(2, clothes.getDescription());
            st.setDouble(3, clothes.getArmor());
            st.setInt(4, clothes.getClass_().getId());

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
    public Clothes getClothes(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "Select id, name, description, armor, class_id FROM clothes WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
                Clothes clothes = new Clothes(
                        id,
                        rs.getString("name"),
                        rs.getString("description"),
                        class_,
                        rs.getDouble("armor"));
                return clothes;
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
    public boolean changeClothes(int id, String name, String description, Class_ class_, double armor){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE clothes SET ";
            boolean temp = false;
            if (name != null){
                sql = sql.concat("name = " + "\'" + name + "\'");
                temp = true; }
            if (description != null){
                if (temp){ sql = sql.concat(", description = " + "\'" + description + "\'");
                } else { sql = sql.concat("description = " + "\'" + description + "\'"); }
                temp = true;
            }
            if (armor != 0){
                if (temp){ sql = sql.concat(", armor = " + armor);
                } else { sql = sql.concat("armor = " + armor); }
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
    public boolean deleteClothes(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM clothes WHERE id = " + id;
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
    public List<Clothes> getAllClothes(){
        Connection con = null;
        try {
            con = db.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("Select *  FROM clothes");
            List<Clothes> clothesS = new LinkedList<>();

            IClass_Repository classRepo = new Class_Repository(db);
            List<Class_> class_s = classRepo.getAllClass_();

            while (rs.next()){
                Class_ class_ = null;
                for (int i=0; i< class_s.size(); i++){
                    if (class_s.get(i).getId() == rs.getInt("class_id")){
                        class_ = class_s.get(i);
                    }
                }
                Clothes clothes = new Clothes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        class_,
                        rs.getDouble("armor"));
                clothesS.add(clothes);
            }
            return clothesS;
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
