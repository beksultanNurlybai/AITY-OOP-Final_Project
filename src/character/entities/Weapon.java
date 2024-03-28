package character.entities;

import character.entities.abstracts.Item;
import character.entities.interfaces.IWeapon;

public class Weapon extends Item implements IWeapon {
    private double dps;

    public void setDps(double dps) { this.dps = dps; }
    public double getDps() { return dps; }

    public Weapon(String name, String description, Class_ class_, double dps){
        setName(name);
        setDescription(description);
        setClass_(class_);
        setDps(dps);
    }
    public Weapon(int id, String name, String description, Class_ class_, double dps){
        this(name, description, class_, dps);
        setId(id);
    }

    @Override
    public void describe(){
        System.out.printf("\nId: %d\nName: %s\nClass: %s\nDPS: %.0f\nDescription: %s\n\n",
                getId(), getName(), getClass_().getName(), getDps(), getDescription());
    }
    @Override
    public void hit(){
        System.out.printf("%s made %.0f damage.\n", getName(), getDps());
    }
    @Override
    public void splash(){ System.out.printf("%s made %.0f dealt damage.\n", getName(), getDps()/2); }

    @Override
    public String toString(){ return "weapon(" + getName() + ")"; }
}
