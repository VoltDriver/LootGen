package joel.lajoiecorriveau.lootgen;

import java.util.ArrayList;
import java.util.List;

import joel.lajoiecorriveau.lootgen.models.Item;
import joel.lajoiecorriveau.lootgen.models.ItemPool;
import joel.lajoiecorriveau.lootgen.models.Item_Chance;
import joel.lajoiecorriveau.lootgen.models.Rarity;
import joel.lajoiecorriveau.lootgen.models.User;
import joel.lajoiecorriveau.lootgen.models.repo.RepoItemPools;
import joel.lajoiecorriveau.lootgen.models.repo.RepoItems;
import joel.lajoiecorriveau.lootgen.models.repo.RepoUsers;
import junit.framework.TestCase;

/**
 * Created by Joel on 9/9/2015.
 */
public class TestStockageItem extends TestCase {


    RepoItems repoProd;
    RepoUsers repoUsers;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoProd = RepoItems.get();
        repoUsers = RepoUsers.get();
        //repoProd = new RepositoryProduitsFichiers();
        repoProd.deleteAll();
        repoUsers.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoProd.deleteAll();
        repoUsers.deleteAll();
        repoProd = null;
        repoUsers = null;
    }

    public void testSaveAndGetAll(){

        Item p = new Item("ItemTest1", Rarity.A, "Test1");

        repoProd.save(p);

        assertEquals(repoProd.getAll().size(), 1);
    }

    public void testMultipleSaveAndGetAll()
    {
        Item p = new Item("ItemTest1", Rarity.A, "Test1");
        Item p2 = new Item("ItemTest2", Rarity.B, "Test1");
        Item p3 = new Item("ItemTest3", Rarity.C, "Test1");

        repoProd.saveMany(p,p2,p3);

        assertEquals(repoProd.getAll().size(), 3);
    }

    public void testMultipleSaveAndGetAll2()
    {
        Item p = new Item("ItemTest1", Rarity.A, "Test1");
        Item p2 = new Item("ItemTest2", Rarity.B, "Test1");
        Item p3 = new Item("ItemTest3", Rarity.C, "Test1");

        List<Item> prodlist = new ArrayList<Item>();

        prodlist.add(p);
        prodlist.add(p2);
        prodlist.add(p3);

        repoProd.saveMany(prodlist);

        assertEquals(repoProd.getAll().size(), 3);
    }

    public void testGetByID()
    {
        Item p = new Item("ItemTest1", Rarity.A, "Test1");

        Long idDuProd = repoProd.save(p);

        Item p2 = repoProd.getById(idDuProd);

        assertTrue(p.getName().equals(p2.getName()));
    }

    public void testDeleteOne()
    {
        Item p = new Item("ItemTest1", Rarity.A, "Test1");

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(p);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        Item p = new Item("ItemTest1", Rarity.A, "Test1");

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(idDuProd);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        Item p = new Item("ItemTest1", Rarity.A, "Test1");
        Item p2 = new Item("ItemTest2", Rarity.B, "Test1");
        Item p3 = new Item("ItemTest3", Rarity.C, "Test1");

        repoProd.saveMany(p,p2,p3);

        repoProd.deleteAll();

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testGetItemsByUser()
    {
        User u = new User("Bob", "password", "test@gmail.com");

        Item i = new Item("TestItem", Rarity.D, "TestCat");
        Item i2 = new Item("TestItem2", Rarity.B, "TestCat2");
        Item i3 = new Item("TestItem3", Rarity.C, "TestCat3");

        Long id = repoProd.save(i);
        Long id2 = repoProd.save(i2);
        Long id3 = repoProd.save(i3);

        u.getCustomItems().add(id);
        u.getCustomItems().add(id2);
        u.getCustomItems().add(id3);

        Long idUser = repoUsers.save(u);

        User u2 = repoUsers.getById(idUser);

        List<Item> lstItems = repoProd.getItemsByUser(u2);

        assertEquals(lstItems.size(), 3);
        assertEquals(lstItems.get(0).getID(), id);
    }
}
