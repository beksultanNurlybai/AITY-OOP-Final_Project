package character.entities;

public class Ability {
    private int id;
    private String name;
    private String description;

    public Ability(String name, String description){
        setName(name);
        setDescription(description);
    }
    public Ability(int id, String name, String description){
        this(name, description);
        setId(id);
    }

    public void apply(){
        System.out.println(getDescription());
    }

    public void describe(){
        System.out.printf("\nId: %d\nName: %s\nDescription: %s\n\n",
                getId(), getName(), getDescription());
    }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }

    @Override
    public String toString(){ return "ability(" + getName() + ")"; }
}
