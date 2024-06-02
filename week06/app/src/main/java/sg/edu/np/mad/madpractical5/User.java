package sg.edu.np.mad.madpractical5;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String description;
    public int id;
    public boolean followed;
    public User(String name, String description, int id, boolean followed){
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;

    }
    public String getName() {
        return name;
    }
    public int getId(){
        return id;
    }
    public String getDescription() {
        return description;
    }
    public boolean getFollowed() {
        return followed;
    }


}
