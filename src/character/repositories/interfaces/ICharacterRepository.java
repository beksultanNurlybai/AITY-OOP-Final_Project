package character.repositories.interfaces;

import character.entities.*;
import character.entities.Character;

import java.util.List;

public interface ICharacterRepository {
    boolean createCharacter(Character character);
    Character getCharacter(int id);

    boolean changeCharacter(int id, String name, String history, Class_ class_,
                            Ability ability, Clothes clothes, Weapon weapon);
    boolean deleteCharacter(int id);
    List<Character> getAllCharacter();
}
