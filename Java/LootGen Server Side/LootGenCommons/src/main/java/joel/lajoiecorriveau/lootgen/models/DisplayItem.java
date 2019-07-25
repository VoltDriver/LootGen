//package joel.lajoiecorriveau.lootgen.models;
package joel.lajoiecorriveau.lootgen.models;
/**
 * Created by Joel on 10/26/2015.
 */
public class DisplayItem {
    public Long id;

    public String ItemName;

    public Object Data;

    public DisplayItem()
    {

    }
    public DisplayItem(Long pId, String pItemName, Object pData)
    {
        id = pId;
        ItemName = pItemName;
        Data = pData;
    }

    @Override
    public String toString() {
        return ItemName;
    }
}
