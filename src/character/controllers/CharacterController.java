package character.controllers;

import character.entities.*;
import character.entities.Character;
import character.repositories.interfaces.ICharacterRepository;

import java.util.List;

public class CharacterController {
    private final ICharacterRepository repo;
    public CharacterController(ICharacterRepository repo){
        this.repo = repo;
    }

    public String createCharacter(String name, String history, Class_ class_, Ability ability, Clothes clothes, Weapon weapon){
        Character character = new Character(name, history, class_, ability, clothes, weapon);

        boolean created = repo.createCharacter(character);

        return (created) ? "Character was created!" : "Character creation was failed!";
    }

    public Character getCharacter(int id){ return repo.getCharacter(id); }
    public List<Character> getAllCharacter(){ return repo.getAllCharacter(); }

    public String changeCharacter(int id, String name, String history, Class_ class_,
                                  Ability ability, Clothes clothes, Weapon weapon){
        boolean done = repo.changeCharacter(id, name, history, class_, ability, clothes, weapon);
        return (done) ? "Character was changed!" : "Character change was failed!";
    }

    public String deleteCharacter(int id){
        boolean done = repo.deleteCharacter(id);
        return (done) ? "Character was deleted!" : "Character removal was failed!";
    }
}

