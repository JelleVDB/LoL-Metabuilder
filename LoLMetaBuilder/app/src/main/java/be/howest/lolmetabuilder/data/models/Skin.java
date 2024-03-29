package be.howest.lolmetabuilder.data.models;

/**
 * Created by manuel on 11/28/14.
 */
public class Skin {
    private int id = 0,
        championID = 0,
        num = 0;
    private String name = "",
            image = "";

    public Skin(
            int id, String name, String image,
            int championID, int num
    ) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.championID = championID;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public int getId() {
        return id;
    }

    public int getChampionID() {
        return championID;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
