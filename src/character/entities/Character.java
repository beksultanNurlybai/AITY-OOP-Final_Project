package character.entities;

import character.entities.abstracts.Item;
import character.entities.exceptions.Class_DifferenceException;

public class Character {
    private int id;
    private String name;
    private String history;

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHistory(String history) { this.history = history; }

    private double health = 100;
    private double armor = 0;
    private double dps = 10;
    private double power = 250;

    private void setHealth(Class_ class_) { this.health = 100 + class_.getHealth(); }
    private void setArmor(Clothes clothes) { this.armor = clothes.getArmor(); }
    private void setDps(Weapon weapon) { this.dps = weapon.getDps(); }
    private void setPower(){ this.power = getArmor() + getHealth()*2 + getDps()*5; }

    private Class_ class_;
    private Ability ability;
    private Clothes clothes;
    private Weapon weapon;

    public Character(String name, String history){
        setName(name);
        setHistory(history);
    }

    public Character(int id, String name, String history){
        this(name, history);
        setId(id);
    }
    public Character(String name, String history, Class_ class_, Ability ability, Clothes clothes, Weapon weapon){
        this(name, history);
        this.class_ = class_;
        this.ability = ability;
        this.clothes = clothes;
        this.weapon = weapon;

        setHealth(class_);
        setArmor(clothes);
        setDps(weapon);
        setPower();
    }
    public Character(int id, String name, String history, Class_ class_, Ability ability, Clothes clothes, Weapon weapon){
        this(name, history, class_, ability, clothes, weapon);
        setId(id);
    }

    public void setClass_(Class_ class_) {
        if (class_ != this.class_){
            setHealth(class_);
            this.armor = 0;
            this.dps = 10;
            setPower();

            this.class_ = class_;
            this.clothes = null;
            this.weapon = null;
        }
    }
    public void setAbility(Ability ability) { this.ability = ability; }

    private boolean checkItem(Item item){
        try {
            if (this.getClass_() == null) setClass_(item.getClass_());
            if (item.getClass_() != this.getClass_())
                throw new Class_DifferenceException("The %s has different class_!".formatted(item));
            return true;
        } catch (Class_DifferenceException diffEx) {
            diffEx.printStackTrace();
            System.exit(-1);
        }
        return false;
    }
    public void setClothes(Clothes clothes) {
        if (checkItem(clothes)){
            setArmor(clothes);
            setPower();
            this.clothes = clothes;
        }
    }
    public void setWeapon(Weapon weapon) {
        if (checkItem(weapon)){
            setDps(weapon);
            setPower();
            this.weapon = weapon;
        }
    }

    public void removeClass_(){
        if (getClass_() != null){
            this.health = 100;
            this.armor = 0;
            this.dps = 10;
            this.power = 250;

            this.class_ = null;
            this.clothes = null;
            this.weapon = null;
        }
    }
    public void removeAbility(){ if (getAbility() != null){ this.ability = null; } }
    public void removeClothes(){
        if (getClothes() != null){
            this.armor = 0;
            setPower();
            this.clothes = null;
        }
    }
    public void removeWeapon(){
        if (getWeapon() != null){
            this.dps = 10;
            setPower();
            this.weapon = null;
        }
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getHistory() { return history; }
    public double getHealth() { return health; }
    public double getArmor() { return armor; }
    public double getDps() { return dps; }
    public double getPower() { return power; }
    public Class_ getClass_() { return class_; }
    public Ability getAbility() { return ability; }
    public Clothes getClothes() { return clothes; }
    public Weapon getWeapon() { return weapon; }

    public boolean fight(Character enemy){
        return this.power >= enemy.power;
    }

    public String getStat(){
        return ("Id: %d\nName: %s\nHealth: %.0f\nPower: %.0f\nArmor: %.0f\nDPS: %.0f\n" +
                "Class: %s\nAbility: %s\nClothes: %s\nWeapon: %s\nHistory: %s\n").formatted(
                getId(), getName(), getHealth(), getPower(), getArmor(), getDps(),
                (getClass_()==null)?"null":getClass_().getName(),
                (getAbility()==null)?"null":getAbility().getName(),
                (getClothes()==null)?"null":getClothes().getName(),
                (getWeapon()==null)?"null":getWeapon().getName(), getHistory());
    }
}
