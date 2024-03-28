package character.controllers;

import character.entities.Ability;
import character.entities.Class_;
import character.entities.Weapon;
import character.repositories.interfaces.IAbilityRepository;

import java.util.List;

public class AbilityController {
    private final IAbilityRepository repo;
    public AbilityController(IAbilityRepository repo){
        this.repo = repo;
    }

    public String createAbility(String name, String description){
        Ability ability = new Ability(name, description);

        boolean created = repo.createAbility(ability);

        return (created) ? "Ability was created!" : "Ability creation was failed!";
    }

    public Ability getAbility(int id){
        return repo.getAbility(id);
    }
    public List<Ability> getAllAbility(){ return repo.getAllAbility(); }

    public String changeAbility(int id, String name, String description){
        boolean done = repo.changeAbility(id, name, description);
        return (done) ? "Ability was changed!" : "Ability was were failed!";
    }

    public String deleteAbility(int id){
        boolean done = repo.deleteAbility(id);
        return (done) ? "Ability was deleted!" : "Ability removal was failed!";
    }
}
