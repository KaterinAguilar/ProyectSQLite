package apps.IrvinTheSenpai.proyectsqlite;

/* Modelo de la clase para RecyclerView*/
public class Photograh {
    //Variables
    String id, name, image;

    //Constructor

    public Photograh(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;

    }

    //Getter y Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
