package character.repositories.interfaces;

import character.entities.Ability;
import java.util.List;

public interface IAbilityRepository {
    boolean createAbility(Ability ability);
    Ability getAbility(int id);
    boolean changeAbility(int id, String name, String description);
    boolean deleteAbility(int id);
    List<Ability> getAllAbility();
}
