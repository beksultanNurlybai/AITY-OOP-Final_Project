package character.repositories.interfaces;

import character.entities.Class_;
import character.entities.Weapon;

import java.util.List;

public interface IWeaponRepository {
    boolean createWeapon(Weapon weapon);
    Weapon getWeapon(int id);
    boolean changeWeapon(int id, String name, String description, Class_ class_, double dps);
    boolean deleteWeapon(int id);
    List<Weapon> getAllWeapon();
}
