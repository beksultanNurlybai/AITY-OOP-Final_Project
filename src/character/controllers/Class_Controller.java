package character.controllers;

import character.entities.Class_;
import character.repositories.interfaces.IClass_Repository;

import java.util.List;

public class Class_Controller {
    private final IClass_Repository repo;
    public Class_Controller(IClass_Repository repo){
        this.repo = repo;
    }

    public String createClass_(String name, String description, double health){
        Class_ class_ = new Class_(name, description, health);

        boolean created = repo.createClass_(class_);

        return (created) ? "Class_ was created!" : "Class_ creation was failed!";
    }

    public Class_ getClass_(int id){
        return repo.getClass_(id);
    }
    public List<Class_> getAllClass_(){ return repo.getAllClass_(); }

    public String changeClass_(int id, String name, String description, double health){
        boolean done = repo.changeClass_(id, name, description, health);
        return (done) ? "Class_ was changed!" : "Class_ was were failed!";
    }

    public String deleteClass_(int id){
        boolean done = repo.deleteClass_(id);
        return (done) ? "Class_ was deleted!" : "Class_ removal was failed!";
    }
}
