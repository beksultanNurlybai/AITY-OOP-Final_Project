package character;

import character.controllers.*;
import character.repositories.*;
import character.repositories.interfaces.*;
import character.data.PostgresDB;
import character.data.interfaces.IDB;

public class Main {
    public static void main(String[] args){

        IDB db = new PostgresDB();
        ICharacterRepository characterRepo = new CharacterRepository(db);
        IClass_Repository classRepo = new Class_Repository(db);
        IAbilityRepository abilityRepo = new AbilityRepository(db);
        IClothesRepository clothesRepo = new ClothesRepository(db);
        IWeaponRepository weaponRepo = new WeaponRepository(db);

        CharacterController characterController = new CharacterController(characterRepo);
        Class_Controller classController = new Class_Controller(classRepo);
        AbilityController abilityController = new AbilityController(abilityRepo);
        ClothesController clothesController = new ClothesController(clothesRepo);
        WeaponController weaponController = new WeaponController(weaponRepo);

        MyApplication myApp = new MyApplication(characterController, classController,
                abilityController, clothesController, weaponController);

        myApp.start();
    }
}
