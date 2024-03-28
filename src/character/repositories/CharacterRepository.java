package character.repositories;

import character.entities.*;
import character.entities.Character;
import character.repositories.interfaces.ICharacterRepository;
import character.data.interfaces.IDB;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CharacterRepository implements ICharacterRepository {
    private final IDB db;
    public CharacterRepository(IDB db){
        this.db = db;
    }

    @Override
    public boolean createCharacter(Character character){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO character (name, history, power, health, armor, dps, " +
                    "class_id, ability_id, clothes_id, weapon_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, character.getName());
            st.setString(2, character.getHistory());
            st.setDouble(3, character.getPower());
            st.setDouble(4, character.getHealth());
            st.setDouble(5, character.getArmor());
            st.setDouble(6, character.getDps());
            st.setInt(7, character.getClass_().getId());
            st.setInt(8, character.getAbility().getId());
            st.setInt(9, character.getClothes().getId());
            st.setInt(10, character.getWeapon().getId());

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
    public Character getCharacter(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "Select id, name, history, class_id, ability_id, " +
                    "clothes_id, weapon_id FROM character  WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String sql2 = "Select id, name, description, health FROM class_ WHERE id = ?";
                PreparedStatement st2 = con.prepareStatement(sql2);
                st2.setInt(1, rs.getInt("class_id"));
                ResultSet rs2 = st2.executeQuery();
                Class_ class_ = null;
                if (rs2.next()){
                    class_ = new Class_(
                            rs2.getInt("id"),
                            rs2.getString("name"),
                            rs2.getString("description"),
                            rs2.getDouble("health"));
                }

                String sql3 = "Select id, name, description FROM ability WHERE id = ?";
                PreparedStatement st3 = con.prepareStatement(sql3);
                st3.setInt(1, rs.getInt("ability_id"));
                ResultSet rs3 = st3.executeQuery();
                Ability ability = null;
                if (rs3.next()){
                    ability = new Ability(
                            rs3.getInt("id"),
                            rs3.getString("name"),
                            rs3.getString("description"));
                }

                String sql4 = "Select id, name, description, armor FROM clothes WHERE id = ?";
                PreparedStatement st4 = con.prepareStatement(sql4);
                st4.setInt(1, rs.getInt("clothes_id"));
                ResultSet rs4 = st4.executeQuery();
                Clothes clothes = null;
                if (rs4.next()){
                    clothes = new Clothes(
                            rs4.getInt("id"),
                            rs4.getString("name"),
                            rs4.getString("description"),
                            class_,
                            rs4.getDouble("armor"));
                }

                String sql5 = "Select id, name, description, dps FROM weapon WHERE id = ?";
                PreparedStatement st5 = con.prepareStatement(sql5);
                st5.setInt(1, rs.getInt("weapon_id"));
                ResultSet rs5 = st5.executeQuery();
                Weapon weapon = null;
                if (rs5.next()){
                    weapon = new Weapon(
                            rs5.getInt("id"),
                            rs5.getString("name"),
                            rs5.getString("description"),
                            class_,
                            rs5.getDouble("dps"));
                }

                Character character = new Character(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("history"),
                        class_, ability, clothes, weapon);
                return character;
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
    public boolean changeCharacter(int id, String name, String history, Class_ class_,
                                   Ability ability, Clothes clothes, Weapon weapon){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "UPDATE character SET ";
            boolean temp = false;
            if (name != null){
                sql = sql.concat("name = " + "\'" + name + "\'");
                temp = true; }
            if (history != null){
                if (temp){ sql = sql.concat(", history = " + "\'" + history + "\'");
                } else { sql = sql.concat("history = " + "\'" + history + "\'"); }
                temp = true;
            }
            if (class_ != null){
                if(temp){ sql = sql.concat(", class_id = " + class_.getId());
                } else { sql = sql.concat("class_id = " + class_.getId()); }
                temp = true;
            }
            if (ability != null){
                if(temp){ sql = sql.concat(", ability_id = " + ability.getId()); }
                else { sql = sql.concat("ability_id = " + ability.getId()); }
                temp = true;
            }
            if (clothes != null){
                if(temp){ sql = sql.concat(", clothes_id = " + clothes.getId()); }
                else { sql = sql.concat("clothes_id = " + clothes.getId()); }
                temp = true;
            }
            if (weapon != null){
                if(temp){ sql = sql.concat(", weapon_id = " + weapon.getId()); }
                else { sql = sql.concat("weapon_id = " + weapon.getId()); }
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
    public boolean deleteCharacter(int id){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "DELETE FROM character WHERE id = " + id;
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
    public List<Character> getAllCharacter(){
        Connection con = null;
        try {
            con = db.getConnection();
            Statement st = con.createStatement();

            String sql = "Select id, name, history, class_id, ability_id, clothes_id, weapon_id FROM character";

            ResultSet rs = st.executeQuery(sql);
            List<Character> characters = new LinkedList<>();
            while (rs.next()){
                int class_id = rs.getInt("class_id");
                String sql2 = "Select id, name, description, health FROM class_ WHERE id = ?";
                PreparedStatement st2 = con.prepareStatement(sql2);
                st2.setInt(1, class_id);
                ResultSet rs2 = st2.executeQuery();
                Class_ class_ = null;
                if (rs2.next()){
                    class_ = new Class_(
                            rs2.getInt("id"),
                            rs2.getString("name"),
                            rs2.getString("description"),
                            rs2.getDouble("health"));
                }

                int ability_id = rs.getInt("ability_id");
                String sql3 = "Select id, name, description FROM ability WHERE id = ?";
                PreparedStatement st3 = con.prepareStatement(sql3);
                st3.setInt(1, ability_id);
                ResultSet rs3 = st3.executeQuery();
                Ability ability = null;
                if (rs3.next()){
                    ability = new Ability(
                            rs3.getInt("id"),
                            rs3.getString("name"),
                            rs3.getString("description"));
                }

                int clothes_id = rs.getInt("clothes_id");
                String sql4 = "Select id, name, description, armor FROM clothes WHERE id = ?";
                PreparedStatement st4 = con.prepareStatement(sql4);
                st4.setInt(1, clothes_id);
                ResultSet rs4 = st4.executeQuery();
                Clothes clothes = null;
                if (rs4.next()){
                    clothes = new Clothes(
                            rs4.getInt("id"),
                            rs4.getString("name"),
                            rs4.getString("description"),
                            class_,
                            rs4.getDouble("armor"));
                }

                int weapon_id = rs.getInt("weapon_id");
                String sql5 = "Select id, name, description, dps FROM weapon WHERE id = ?";
                PreparedStatement st5 = con.prepareStatement(sql5);
                st5.setInt(1, weapon_id);
                ResultSet rs5 = st5.executeQuery();
                Weapon weapon = null;
                if (rs5.next()){
                    weapon = new Weapon(
                            rs5.getInt("id"),
                            rs5.getString("name"),
                            rs5.getString("description"),
                            class_,
                            rs5.getDouble("dps"));
                }

                Character character = new Character(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("history"),
                        class_, ability, clothes, weapon);
                characters.add(character);
            }
            return characters;
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