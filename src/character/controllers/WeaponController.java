package character.controllers;

import character.entities.Class_;
import character.entities.Weapon;
import character.repositories.interfaces.IWeaponRepository;

import java.util.List;

public class WeaponController {
    private final IWeaponRepository repo;
    public WeaponController(IWeaponRepository repo){ this.repo = repo; }

    public String createWeapon(String name, String description, Class_ class_, double dps){
        Weapon weapon = new Weapon(name, description, class_, dps);

        boolean created = repo.createWeapon(weapon);

        return (created) ? "Weapon was created!" : "Weapon creation was failed!";
    }

    public Weapon getWeapon(int id){ return repo.getWeapon(id); }

    public String changeWeapon(int id, String name, String description, Class_ class_, double dps){
        boolean done = repo.changeWeapon(id, name, description, class_, dps);
        return (done) ? "Weapon was changed!" : "Weapon was were failed!";
    }

    public String deleteWeapon(int id){
        boolean done = repo.deleteWeapon(id);
        return (done) ? "Weapon was deleted!" : "Weapon removal was failed!";
    }

    public List<Weapon> getAllWeapon() {
        /*String result = "\n";
        if ((class_ == null)){
            for (Weapon weapon : repo.getAllWeapon()){
                result = result.concat("id: " + weapon.getId() + "\t name: " + weapon.getName() + "\n");
            }
        } else {
            for (Weapon weapon : repo.getAllWeapon()){
                if (weapon.getClass_().getId() == class_.getId()){
                    result = result.concat("id: " + weapon.getId() + "\t name: " + weapon.getName() + "\n");
                }
            }
        }
        return result;*/
        return repo.getAllWeapon();
    }
}
