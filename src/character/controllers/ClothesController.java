package character.controllers;

import character.entities.Ability;
import character.entities.Class_;
import character.entities.Clothes;
import character.entities.Weapon;
import character.repositories.interfaces.IClothesRepository;

import java.util.List;

public class ClothesController {
    private final IClothesRepository repo;
    public ClothesController(IClothesRepository repo){ this.repo = repo; }

    public String createClothes(String name, String description, Class_ class_, double armor){
        Clothes clothes = new Clothes(name, description, class_, armor);

        boolean created = repo.createClothes(clothes);

        return (created) ? "Clothes was created!" : "Clothes creation was failed!";
    }

    public Clothes getClothes(int id){
        return repo.getClothes(id);
    }

    public List<Clothes> getAllClothes() { return repo.getAllClothes(); }

    public String changeClothes(int id, String name, String description, Class_ class_, double armor){
        boolean done = repo.changeClothes(id, name, description, class_, armor);
        return (done) ? "Clothes were changed!" : "Clothes change were failed!";
    }

    public String deleteClothes(int id){
        boolean done = repo.deleteClothes(id);
        return (done) ? "Clothes were deleted!" : "Clothes removal were failed!";
    }
}
