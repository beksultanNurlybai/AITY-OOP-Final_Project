package character.entities;

import character.entities.abstracts.Item;
import character.entities.interfaces.IClothes;

public class Clothes extends Item implements IClothes {
    private double armor;

    public void setArmor(double armor) { this.armor = armor; }
    public double getArmor() { return armor; }

    public Clothes(String name, String description, Class_ class_, double armor){
        setName(name);
        setDescription(description);
        setClass_(class_);
        setArmor(armor);
    }
    public Clothes(int id, String name, String description, Class_ class_, double armor){
        this(name, description, class_, armor);
        setId(id);
    }

    @Override
    public void describe(){
        System.out.printf("\nId: %d\nName: %s\nClass: %s\nArmor: %.0f\nDescription: %s\n\n",
                getId(), getName(), getClass_().getName(), getArmor(), getDescription());
    }
    @Override
    public void takeDamage(){ System.out.printf("%s absorbed %.0f% of damage.\n", getName(), getArmor()/10); }
    @Override
    public void dodge(){ System.out.println("You dodged the attack."); }

    @Override
    public String toString(){ return "clothes(" + getName() + ")"; }
}
