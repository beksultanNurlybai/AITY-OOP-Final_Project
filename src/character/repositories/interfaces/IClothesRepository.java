package character.repositories.interfaces;

import character.entities.Class_;
import character.entities.Clothes;
import java.util.List;


public interface IClothesRepository {
    boolean createClothes(Clothes clothes);
    Clothes getClothes(int id);

    boolean changeClothes(int id, String name, String description, Class_ class_, double armor);
    boolean deleteClothes(int id);
    List<Clothes> getAllClothes();
}
