package character.entities.abstracts;

import character.entities.Class_;

public abstract class Item {
    private int id;
    private String name;
    private String description;
    private Class_ class_;

    public abstract void describe();

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setClass_(Class_ class_) { this.class_ = class_; }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Class_ getClass_() { return class_; }
}
