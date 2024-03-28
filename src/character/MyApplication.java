package character;

import character.controllers.*;
import character.entities.*;
import character.entities.Character;
import character.entities.abstracts.Item;

import java.util.List;
import java.util.Scanner;

public class MyApplication {
    private final CharacterController characterController;
    private final Class_Controller classController;
    private final AbilityController abilityController;
    private final ClothesController clothesController;
    private final WeaponController weaponController;
    private final Scanner scanner;

    public MyApplication(CharacterController characterController, Class_Controller classController,
                         AbilityController abilityController, ClothesController clothesController,
                         WeaponController weaponController){
        this.characterController = characterController;
        this.classController = classController;
        this.abilityController = abilityController;
        this.clothesController = clothesController;
        this.weaponController = weaponController;
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        System.out.println("Welcome to our application!");
        while (true){
            System.out.println("""
                \nSelect a table:
                1: character
                2: clothes
                3: weapon
                4: class
                5: ability
                _______________
                0: exit\n""");
            outer: while (true){
                System.out.print("Input the number from 0 to 5: ");
                int tbIndex = scanner.nextInt();
                switch (tbIndex){
                    case 1:
                        separationLine();
                        characterEdit();
                        break outer;
                    case 2:
                        separationLine();
                        clothesEdit();
                        break outer;
                    case 3:
                        separationLine();
                        weaponEdit();
                        break outer;
                    case 4:
                        separationLine();
                        classEdit();
                        break outer;
                    case 5:
                        separationLine();
                        abilityEdit();
                        break outer;
                    case 0:
                        System.exit(0);
                        break outer;
                    default:
                        System.out.println("the input must be between 0 and 5.");
                        break;
                }
            }
        }
    }

    public void characterEdit(){
        outer2: while (true){
            System.out.println("""
                Select an action for table 'character':
                1: get a record
                2: create a record
                3: change a record
                4: delete a record
                ------------------
                9: previous
                0: exit\n""");
            outer: while (true){
                System.out.print("Input the number 1, 2, 3, 4, 9, or 0: ");
                int index = scanner.nextInt();
                int id, temp; String name, history, message;
                Class_ class_; Ability ability; Clothes clothes; Weapon weapon;
                switch (index){
                    case 1:
                        if (!characterController.getAllCharacter().isEmpty()){
                            System.out.println(toTable(characterController.getAllCharacter()));
                            System.out.print("Input id of the character: ");
                            id = scanner.nextInt();
                            Character character = characterController.getCharacter(id);
                            if (character == null){
                                System.out.println("The character was not found.");
                            } else {
                                System.out.println("\n\n\n" + character.getStat());
                            }
                        } else {
                            System.out.println("There is no character in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 2:
                        if (!characterController.getAllCharacter().isEmpty()){
                            System.out.println(toTable(characterController.getAllCharacter()));
                        }
                        System.out.print("Input name of the character: ");
                        scanner.nextLine();
                        name = scanner.nextLine();

                        System.out.print("Input history of the character: ");
                        history = scanner.nextLine();

                        separationLine();
                        System.out.println(toTable(classController.getAllClass_()));
                        System.out.print("Select class of the character by id: ");
                        temp = scanner.nextInt();
                        class_ = classController.getClass_(temp);

                        separationLine();
                        System.out.println(toTable(abilityController.getAllAbility()));
                        System.out.print("Select ability of the character by id: ");
                        temp = scanner.nextInt();
                        ability = abilityController.getAbility(temp);

                        separationLine();
                        System.out.println(toTable(clothesController.getAllClothes(), class_));
                        System.out.print("Select clothes of the character by id: ");
                        temp = scanner.nextInt();
                        clothes = clothesController.getClothes(temp);

                        separationLine();
                        System.out.println(toTable(weaponController.getAllWeapon(), class_));
                        System.out.print("Select weapon of the character by id: ");
                        temp = scanner.nextInt();
                        weapon = weaponController.getWeapon(temp);

                        message = characterController.createCharacter(name, history, class_,
                                ability, clothes, weapon);
                        System.out.println(message);
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 3:
                        if (!characterController.getAllCharacter().isEmpty()){
                            System.out.println(toTable(characterController.getAllCharacter()));
                            System.out.print("Input id of the character: ");
                            id = scanner.nextInt();

                            System.out.print("Input new name or press 0 to skip: ");
                            scanner.nextLine();
                            name = scanner.nextLine();
                            name = (name.charAt(0) == '0' && name.length() == 1) ? null : name;

                            System.out.print("Input new history or press 0 to skip: ");
                            history = scanner.nextLine();
                            history = (history.charAt(0) == '0' && history.length() == 1) ? null : history;

                            separationLine();
                            System.out.println(toTable(classController.getAllClass_()));
                            System.out.print("Select class by id or press 0 to skip: ");
                            temp = scanner.nextInt();
                            class_ = (temp == 0) ? null : classController.getClass_(temp);
                            Class_ itemClass = null;
                            if (class_ == null) {
                                itemClass = characterController.getCharacter(id).getClass_();
                            }

                            separationLine();
                            System.out.println(toTable(abilityController.getAllAbility()));
                            System.out.print("Select ability by id or press 0 to skip: ");
                            temp = scanner.nextInt();
                            ability = (temp == 0) ? null : abilityController.getAbility(temp);

                            separationLine();
                            System.out.println(toTable(clothesController.getAllClothes(), (class_ == null)?itemClass:class_));
                            System.out.print("Select clothes by id or press 0 to skip: ");
                            temp = scanner.nextInt();
                            clothes = (temp == 0) ? null : clothesController.getClothes(temp);

                            separationLine();
                            System.out.println(toTable(weaponController.getAllWeapon(), (class_ == null)?itemClass:class_));
                            System.out.print("Select weapon of the character by id: ");
                            temp = scanner.nextInt();
                            weapon = (temp == 0) ? null : weaponController.getWeapon(temp);

                            if (class_==null&&ability==null&&clothes==null&&weapon==null&&name==null&&history==null){
                                System.out.println("Character was not changed.");
                                System.out.print("Input any letter to continue: ");
                                scanner.next();
                                break outer;
                            }

                            message = characterController.changeCharacter(id, name, history, class_, ability, clothes, weapon);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no character in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 4:
                        if (!characterController.getAllCharacter().isEmpty()){
                            System.out.println(toTable(characterController.getAllCharacter()));
                            System.out.print("Input id of the character: ");
                            id = scanner.nextInt();
                            message = characterController.deleteCharacter(id);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no character in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 9: break outer2;
                    case 0: System.exit(0);
                    default:
                        System.out.println("The input must be 1, 2, 3, 4, 9 or 0.");
                }
            }
        }
    }

    public void clothesEdit(){
        outer2: while (true){
            System.out.println("""
                Select an action for table 'clothes':
                1: get a record
                2: create a record
                3: change a record
                4: delete a record
                ------------------
                9: previous
                0: exit\n""");
            outer: while (true){
                System.out.print("Input the number 1, 2, 3, 4, 9, or 0: ");
                int index = scanner.nextInt();
                int id, temp;
                double armor;
                String name, description, message;
                Class_ class_;
                switch (index){
                    case 1:
                        if (!clothesController.getAllClothes().isEmpty()){
                            System.out.println(toTable(clothesController.getAllClothes()));
                            System.out.print("Input id of the clothes: ");
                            id = scanner.nextInt();
                            Clothes clothes = clothesController.getClothes(id);
                            if (clothes == null){
                                System.out.println("The clothes was not found.");
                            } else {
                                clothes.describe();
                            }
                        } else {
                            System.out.println("There is no clothes in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 2:
                        if (!clothesController.getAllClothes().isEmpty()){
                            System.out.println(toTable(clothesController.getAllClothes()));
                        }
                        System.out.print("Input name of the clothes: ");
                        scanner.nextLine();
                        name = scanner.nextLine();

                        System.out.print("Input description of the clothes: ");
                        description = scanner.nextLine();

                        System.out.print("Input armor of the clothes: ");
                        armor = scanner.nextDouble();

                        separationLine();
                        System.out.println(toTable(classController.getAllClass_()));
                        System.out.print("Select class of the clothes by id: ");
                        temp = scanner.nextInt();
                        class_ = classController.getClass_(temp);

                        message = clothesController.createClothes(name, description, class_, armor);
                        System.out.println(message);
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 3:
                        if (!clothesController.getAllClothes().isEmpty()){
                            System.out.println(toTable(clothesController.getAllClothes()));
                            System.out.print("Input id of the clothes: ");
                            id = scanner.nextInt();

                            System.out.print("Input new name or press 0 to skip: ");
                            scanner.nextLine();
                            name = scanner.nextLine();
                            name = (name.charAt(0) == '0' && name.length() == 1) ? null : name;

                            System.out.print("Input new description or press 0 to skip: ");
                            description = scanner.nextLine();
                            description = (description.charAt(0) == '0' && description.length() == 1) ? null : description;

                            System.out.print("Input new armor or press 0 to skip: ");
                            armor = scanner.nextDouble();

                            separationLine();
                            System.out.println(toTable(classController.getAllClass_()));
                            System.out.print("Select class by id or press 0 to skip: ");
                            temp = scanner.nextInt();
                            class_ = (temp == 0) ? null : classController.getClass_(temp);

                            if (class_==null&&name==null&&description==null&&armor==0){
                                System.out.println("Clothes was not changed.");
                                System.out.print("Input any letter to continue: ");
                                scanner.next();
                                break outer;
                            }

                            message = clothesController.changeClothes(id, name, description, class_, armor);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no clothes in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 4:
                        if (!clothesController.getAllClothes().isEmpty()){
                            System.out.println(toTable(clothesController.getAllClothes()));
                            System.out.print("Input id of the clothes: ");
                            id = scanner.nextInt();
                            message = clothesController.deleteClothes(id);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no clothes in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 9: break outer2;
                    case 0: System.exit(0);
                    default:
                        System.out.println("The input must be 1, 2, 3, 4, 9 or 0.");
                }
            }
        }
    }

    public void weaponEdit(){
        outer2: while (true){
            System.out.println("""
                Select an action for table 'weapon':
                1: get a record
                2: create a record
                3: change a record
                4: delete a record
                ------------------
                9: previous
                0: exit\n""");
            outer: while (true){
                System.out.print("Input the number 1, 2, 3, 4, 9, or 0: ");
                int index = scanner.nextInt();
                int id, temp;
                double dps;
                String name, description, message;
                Class_ class_;
                switch (index){
                    case 1:
                        if (!weaponController.getAllWeapon().isEmpty()){
                            System.out.println(toTable(weaponController.getAllWeapon()));
                            System.out.print("Input id of the weapon: ");
                            id = scanner.nextInt();
                            Weapon weapon = weaponController.getWeapon(id);
                            if (weapon == null){
                                System.out.println("The weapon was not found.");
                            } else {
                                weapon.describe();
                            }
                        } else {
                            System.out.println("There is no weapon in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 2:
                        if (!weaponController.getAllWeapon().isEmpty()){
                            System.out.println(toTable(weaponController.getAllWeapon()));
                        }

                        System.out.print("Input name of the weapon: ");
                        scanner.nextLine();
                        name = scanner.nextLine();

                        System.out.print("Input description of the weapon: ");
                        description = scanner.nextLine();

                        System.out.print("Input dps of the weapon: ");
                        dps = scanner.nextDouble();

                        separationLine();
                        System.out.println(toTable(classController.getAllClass_()));
                        System.out.print("Select class of the weapon by id: ");
                        temp = scanner.nextInt();
                        class_ = classController.getClass_(temp);
                        message = weaponController.createWeapon(name, description, class_, dps);
                        System.out.println(message);
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 3:
                        if (!weaponController.getAllWeapon().isEmpty()){
                            System.out.println(toTable(weaponController.getAllWeapon()));
                            System.out.print("Input id of the weapon: ");
                            id = scanner.nextInt();

                            System.out.print("Input new name or press 0 to skip: ");
                            scanner.nextLine();
                            name = scanner.nextLine();
                            name = (name.charAt(0) == '0' && name.length() == 1) ? null : name;

                            System.out.print("Input new description or press 0 to skip: ");
                            description = scanner.nextLine();
                            description = (description.charAt(0) == '0' && description.length() == 1) ? null : description;

                            System.out.print("Input new dps or press 0 to skip: ");
                            dps = scanner.nextDouble();

                            separationLine();
                            System.out.println(toTable(classController.getAllClass_()));
                            System.out.print("Select class by id or press 0 to skip: ");
                            temp = scanner.nextInt();
                            class_ = (temp == 0) ? null : classController.getClass_(temp);

                            if (class_==null&&name==null&&description==null&&dps==0){
                                System.out.println("Weapon was not changed.");
                                System.out.print("Input any letter to continue: ");
                                scanner.next();
                                break outer;
                            }

                            message = weaponController.changeWeapon(id, name, description, class_, dps);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no weapon in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 4:
                        if (!weaponController.getAllWeapon().isEmpty()){
                            System.out.println(toTable(weaponController.getAllWeapon()));
                            System.out.print("Input id of the weapon: ");
                            id = scanner.nextInt();
                            message = weaponController.deleteWeapon(id);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no weapon in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 9: break outer2;
                    case 0: System.exit(0);
                    default:
                        System.out.println("The input must be 1, 2, 3, 4, 9 or 0.");
                }
            }
        }
    }

    public void classEdit(){
        outer2: while (true){
            System.out.println("""
                Select an action for table 'class_':
                1: get a record
                2: create a record
                3: change a record
                4: delete a record
                ------------------
                9: previous
                0: exit\n""");
            outer: while (true){
                System.out.print("Input the number 1, 2, 3, 4, 9, or 0: ");
                int index = scanner.nextInt();
                int id;
                double health;
                String name, description, message;
                switch (index){
                    case 1:
                        if (!classController.getAllClass_().isEmpty()){
                            System.out.println(toTable(classController.getAllClass_()));
                            System.out.print("Input id of the class_: ");
                            id = scanner.nextInt();
                            Class_ class_ = classController.getClass_(id);
                            if (class_ == null){
                                System.out.println("The class_ was not found.");
                            } else {
                                class_.describe();
                            }
                        } else {
                            System.out.println("There is no class_ in the database!");
                        }

                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 2:
                        if (!classController.getAllClass_().isEmpty()){
                            System.out.println(toTable(classController.getAllClass_()));
                        }
                        System.out.print("Input name of the class_: ");
                        scanner.nextLine();
                        name = scanner.nextLine();

                        System.out.print("Input description of the class_: ");
                        description = scanner.nextLine();

                        System.out.print("Input health of the class_: ");
                        health = scanner.nextDouble();

                        message = classController.createClass_(name, description, health);
                        System.out.println(message);
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 3:
                        if (!classController.getAllClass_().isEmpty()){
                            System.out.println(toTable(classController.getAllClass_()));
                            System.out.print("Input id of the class_: ");
                            id = scanner.nextInt();

                            System.out.print("Input new name or press 0 to skip: ");
                            scanner.nextLine();
                            name = scanner.nextLine();
                            name = (name.charAt(0) == '0' && name.length() == 1) ? null : name;

                            System.out.print("Input new description or press 0 to skip: ");
                            description = scanner.nextLine();
                            description = (description.charAt(0) == '0' && description.length() == 1) ? null : description;

                            System.out.print("Input new health or press 0 to skip: ");
                            health = scanner.nextDouble();

                            if (name==null&&description==null&&health==0){
                                System.out.println("Class_ was not changed.");
                                System.out.print("Input any letter to continue: ");
                                scanner.next();
                                break outer;
                            }

                            message = classController.changeClass_(id, name, description, health);
                            System.out.println(message);
                        } else {
                            System.out.print("There is no class_ in the database!");
                        }

                        System.out.println("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 4:
                        if (!classController.getAllClass_().isEmpty()){
                            System.out.println(toTable(classController.getAllClass_()));
                            System.out.print("Input id of the class_: ");
                            id = scanner.nextInt();
                            message = classController.deleteClass_(id);
                            System.out.println(message);
                        } else {
                            System.out.print("There is no class_ in the database!");
                        }

                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 9: break outer2;
                    case 0: System.exit(0);
                    default:
                        System.out.println("The input must be 1, 2, 3, 4, 9 or 0.");
                }
            }
        }
    }

    public void abilityEdit(){
        outer2: while (true){
            System.out.println("""
                Select an action for table 'ability':
                1: get a record
                2: create a record
                3: change a record
                4: delete a record
                ------------------
                9: previous
                0: exit\n""");
            outer: while (true){
                System.out.print("Input the number 1, 2, 3, 4, 9, or 0: ");
                int index = scanner.nextInt();
                int id;
                String name, description, message;
                switch (index){
                    case 1:
                        if (!abilityController.getAllAbility().isEmpty()){
                            System.out.println(toTable(abilityController.getAllAbility()));
                            System.out.print("Input id of the ability: ");
                            id = scanner.nextInt();
                            Ability ability = abilityController.getAbility(id);
                            if (ability == null){
                                System.out.println("The weapon was not found.");
                            } else {
                                ability.describe();
                            }
                        } else {
                            System.out.println("There is no ability in the database!");
                        }
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 2:

                        if (!abilityController.getAllAbility().isEmpty()) {
                            System.out.println(toTable(abilityController.getAllAbility()));
                        }
                        System.out.print("Input name of the ability: ");
                        scanner.nextLine();
                        name = scanner.nextLine();

                        System.out.print("Input description of the weapon: ");
                        description = scanner.nextLine();

                        message = abilityController.createAbility(name, description);
                        System.out.println(message);
                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 3:
                        if (!abilityController.getAllAbility().isEmpty()){
                            System.out.println(toTable(abilityController.getAllAbility()));
                            System.out.print("Input id of the ability: ");
                            id = scanner.nextInt();

                            System.out.print("Input new name or press 0 to skip: ");
                            scanner.nextLine();
                            name = scanner.nextLine();
                            name = (name.charAt(0) == '0' && name.length() == 1) ? null : name;

                            System.out.print("Input new description or press 0 to skip: ");
                            description = scanner.nextLine();
                            description = (description.charAt(0) == '0' && description.length() == 1) ? null : description;

                            if (name==null&&description==null){
                                System.out.println("Ability was not changed.");
                                System.out.print("Input any letter to continue: ");
                                scanner.next();
                                break outer;
                            }

                            message = abilityController.changeAbility(id, name, description);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no ability in the database!");
                        }

                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 4:
                        if (!abilityController.getAllAbility().isEmpty()){
                            System.out.println(toTable(abilityController.getAllAbility()));
                            System.out.print("Input id of the ability: ");
                            id = scanner.nextInt();
                            message = abilityController.deleteAbility(id);
                            System.out.println(message);
                        } else {
                            System.out.println("There is no ability in the database!");
                        }

                        System.out.print("Input any letter to continue: ");
                        scanner.next();
                        break outer;
                    case 9: break outer2;
                    case 0: System.exit(0);
                    default:
                        System.out.println("The input must be 1, 2, 3, 4, 9 or 0.");
                }
            }
        }
    }
    public  void separationLine(){System.out.print("\n\n________________________________________\n");}

    public <T> String toTable(List<T> objects){
        String result = "\n";
        if (objects.get(0) instanceof Character){
            for (Object object : objects){
                Character character = (Character) object;
                result = result.concat("id: " + character.getId() + "\t name: " + character.getName() + "\n");
            }
        } else if (objects.get(0) instanceof Class_){
            for (Object object : objects){
                Class_ class_ = (Class_) object;
                result = result.concat("id: " + class_.getId() + "\t name: " + class_.getName() + "\n");
            }
        } else if (objects.get(0) instanceof Ability) {
            for (Object object : objects){
                Ability class_ = (Ability) object;
                result = result.concat("id: " + class_.getId() + "\t name: " + class_.getName() + "\n");
            }
        } else if (objects.get(0) instanceof Clothes) {
            for (Object object : objects){
                Clothes clothes = (Clothes) object;
                result = result.concat("id: " + clothes.getId() + "\t name: " + clothes.getName() + "\n");
            }
        } else if (objects.get(0) instanceof Weapon){
            for (Object object : objects){
                Weapon weapon = (Weapon) object;
                result = result.concat("id: " + weapon.getId() + "\t name: " + weapon.getName() + "\n");
            }
        }
        return result;
    }
    public <T extends Item> String toTable(List<T> items, Class_ class_){
        String result = "\n";
        for (Item item : items){
            if (item.getClass_().getId() == class_.getId()){
                result = result.concat("id: " + item.getId() + "\t name: " + item.getName() + "\n");
            }
        }
        return result;
    }
}
