package character.entities;

public class Class_{
    private int id;
    private String name;
    private String description;
    private double health;

    public Class_(String name, String description, double health){
        setName(name);
        setDescription(description);
        setHealth(health);
    }
    public Class_(int id, String name, String description, double health){
        this(name, description, health);
        setId(id);
    }

    public void describe(){
        System.out.printf("\nId: %d\nName: %s\nHealth: %.0f\nDescription: %s\n\n",
                getId(), getName(), getHealth(), getDescription());
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setHealth(double health) { this.health = health; }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getHealth() { return health; }

    @Override
    public String toString(){ return "class(" + getName() + ")"; }
}
