package character.repositories.interfaces;

import character.entities.Class_;

import java.util.List;

public interface IClass_Repository {
    boolean createClass_(Class_ ability);
    Class_ getClass_(int id);
    boolean changeClass_(int id, String name, String description, double health);
    boolean deleteClass_(int id);
    List<Class_> getAllClass_();
}
