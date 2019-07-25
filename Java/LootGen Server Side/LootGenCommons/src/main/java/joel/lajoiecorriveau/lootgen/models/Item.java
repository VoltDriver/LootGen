//package joel.lajoiecorriveau.lootgen.models;
package joel.lajoiecorriveau.lootgen.models;
/**
 * Created by Joel on 9/8/2015.
 */
public class Item {
    private Long ID;
    private String Name;
    private String Category;
    private Rarity Rarity;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public Rarity getRarity() {
        return Rarity;
    }

    public void setRarity(Rarity rarity) {
        Rarity = rarity;
    }

    public Item(String pName, Rarity pRarity, String pCategory)
    {
        Name = pName;
        Rarity = pRarity;
        Category = pCategory;
    }

    public String ToString()
    {

        return Name + "*|*" + this.getRarity().toString() + "*|*" + Category;
    }
}
