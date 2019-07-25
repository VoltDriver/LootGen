//package joel.lajoiecorriveau.lootgen.models;
package joel.lajoiecorriveau.lootgen.models;
/**
 * Created by Joel on 9/8/2015.
 */
public class Item_Chance {
    public Long ID;
    public Long ItemIndex;
    public Double dropPercent;

    public Item_Chance(Long pItemIndex, double pDropPercent)
    {
        ItemIndex = pItemIndex;
        dropPercent = pDropPercent;
    }


    public String ToString()
    {
        return Long.toString(ItemIndex) + "*|*" + Double.toString(dropPercent);
    }
}
