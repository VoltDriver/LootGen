//package joel.lajoiecorriveau.lootgen.models;
package joel.lajoiecorriveau.lootgen.models;
import java.util.List;

/**
 * Created by Joel on 9/8/2015.
 */
public class ItemPool {

    private String m_name;
    private Long ID;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String pName) {
        this.m_name = pName;
    }

    private List<Item_Chance> m_lstItems;

    public List<Item_Chance> getItems() {
        return m_lstItems;
    }

    public void setID(List<Item_Chance> pLstItems) {
        this.m_lstItems = pLstItems;
    }

    public ItemPool(String pName, List<Item_Chance> pItems)
    {
        m_name = pName;
        m_lstItems = pItems;
    }

    public String ToString()
    {
        String longStringOfItems = "";

        for (int i = 0; i < m_lstItems.size(); i++)
        {
            if(i != m_lstItems.size() - 1)
            {
                longStringOfItems += Long.toString(m_lstItems.get(i).ItemIndex) + "***" + Double.toString(m_lstItems.get(i).dropPercent) + "*,*";
            }
            else
            {
                longStringOfItems += Long.toString(m_lstItems.get(i).ItemIndex) + "***" + Double.toString(m_lstItems.get(i).dropPercent);
            }
        }

        return m_lstItems + "*|*" +  longStringOfItems;
    }
}
