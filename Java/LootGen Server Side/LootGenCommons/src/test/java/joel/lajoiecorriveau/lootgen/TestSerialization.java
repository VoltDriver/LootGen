package joel.lajoiecorriveau.lootgen;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import joel.lajoiecorriveau.lootgen.models.Item;
import joel.lajoiecorriveau.lootgen.models.ItemPool;
import joel.lajoiecorriveau.lootgen.models.Item_Chance;
import joel.lajoiecorriveau.lootgen.models.Rarity;
import joel.lajoiecorriveau.lootgen.service.Service;
import junit.framework.TestCase;

/**
 * Created by 1387434 on 2015-11-09.
 */
public class TestSerialization extends TestCase {
    Service service;
    Gson gson = new Gson();

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        service = Service.get();
        service.DeleteAllItemPools();
        service.DeleteAllItems();
        service.DeleteAllUsers();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        service.DeleteAllItemPools();
        service.DeleteAllItems();
        service.DeleteAllUsers();
        service = null;
    }

    public void testSerializeItem()
    {
        Item item = new Item("ObjetTest1", Rarity.C, "Test");
        Item item2 = new Item("ObjetTest2", Rarity.A, "Test");
        Item item3 = new Item("ObjetTest3", Rarity.SSS, "Test");

        String itemS = gson.toJson(item);
        String item2S = gson.toJson(item2);
        String item3S = gson.toJson(item3);

        Item copyItem = gson.fromJson(itemS, Item.class);
        Item copyItem2S = gson.fromJson(item2S, Item.class);
        Item copyItem3S = gson.fromJson(item3S, Item.class);

        assertEquals(true, copyItem.getName().equals(item.getName()));
        assertEquals(true, copyItem.getCategory().equals(item.getCategory()));
        assertEquals(true, copyItem.getRarity().equals(item.getRarity()));

        assertEquals(true, copyItem2S.getName().equals(item2.getName()));
        assertEquals(true, copyItem2S.getCategory().equals(item2.getCategory()));
        assertEquals(true, copyItem2S.getRarity().equals(item2.getRarity()));

        assertEquals(true, copyItem3S.getName().equals(item3.getName()));
        assertEquals(true, copyItem3S.getCategory().equals(item3.getCategory()));
        assertEquals(true, copyItem3S.getRarity().equals(item3.getRarity()));
    }

//    public void testSerializeItemPool()
//    {
//        Item item = new Item("ObjetTest1", Rarity.C, "Test");
//        Item item2 = new Item("ObjetTest2", Rarity.A, "Test");
//        Item item3 = new Item("ObjetTest3", Rarity.SSS, "Test");
//
//        Item_Chance chance1 = new Item_Chance()
//
//        ItemPool pool = new ItemPool();
//    }
}
