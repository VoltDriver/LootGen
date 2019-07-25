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
public class TestStockageItemPool extends TestCase {
    RepoItemPools repoProd;
    RepoUsers repoUsers;
    RepoItems repoItems;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoProd = RepoItemPools.get();
        repoItems = RepoItems.get();
        repoUsers = RepoUsers.get();
        //repoProd = new RepositoryProduitsFichiers();
        repoProd.deleteAll();
        repoItems.deleteAll();
        repoUsers.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoProd.deleteAll();
        repoUsers.deleteAll();
        repoItems.deleteAll();
        repoProd = null;
        repoItems = null;
        repoUsers = null;
    }

    public void testSaveAndGetAll(){
        Item_Chance i1 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest1 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i1);

        ItemPool p = new ItemPool("TestPool1", lstItemTest1);

        repoProd.save(p);

        assertEquals(repoProd.getAll().size(), 1);
    }

    public void testMultipleSaveAndGetAll()
    {
        Item_Chance i1 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest1 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i1);

        ItemPool p = new ItemPool("TestPool1", lstItemTest1);

        Item_Chance i2 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest2 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i2);

        ItemPool p2 = new ItemPool("TestPool1", lstItemTest2);

        Item_Chance i3 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest3 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i3);

        ItemPool p3 = new ItemPool("TestPool1", lstItemTest3);

        repoProd.saveMany(p,p2,p3);

        assertEquals(repoProd.getAll().size(), 3);
    }

    public void testMultipleSaveAndGetAll2()
    {
        Item_Chance i1 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest1 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i1);

        ItemPool p = new ItemPool("TestPool1", lstItemTest1);

        Item_Chance i2 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest2 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i2);

        ItemPool p2 = new ItemPool("TestPool1", lstItemTest2);

        Item_Chance i3 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest3 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i3);

        ItemPool p3 = new ItemPool("TestPool1", lstItemTest3);

        List<ItemPool> prodlist = new ArrayList<ItemPool>();

        prodlist.add(p);
        prodlist.add(p2);
        prodlist.add(p3);

        repoProd.saveMany(prodlist);

        assertEquals(repoProd.getAll().size(), 3);
    }

    public void testGetByID()
    {
        Item_Chance i1 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest1 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i1);

        ItemPool p = new ItemPool("TestPool1", lstItemTest1);

        Long idDuProd = repoProd.save(p);

        ItemPool p2 = repoProd.getById(idDuProd);

        assertTrue(p.getName().equals(p2.getName()));
    }

    public void testDeleteOne()
    {
        Item_Chance i1 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest1 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i1);

        ItemPool p = new ItemPool("TestPool1", lstItemTest1);

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(p);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        Item_Chance i1 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest1 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i1);

        ItemPool p = new ItemPool("TestPool1", lstItemTest1);

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(idDuProd);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        Item_Chance i1 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest1 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i1);

        ItemPool p = new ItemPool("TestPool1", lstItemTest1);

        Item_Chance i2 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest2 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i2);

        ItemPool p2 = new ItemPool("TestPool1", lstItemTest2);

        Item_Chance i3 = new Item_Chance(1L, 10.5);

        List<Item_Chance> lstItemTest3 = new ArrayList<Item_Chance>();
        lstItemTest1.add(i3);

        ItemPool p3 = new ItemPool("TestPool1", lstItemTest3);

        repoProd.saveMany(p,p2,p3);

        repoProd.deleteAll();

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testGetByUser()
    {
        User u = new User("Bob", "password", "test@gmail.com");

        Item i = new Item("TestItem", Rarity.D, "TestCat");
        Item i2 = new Item("TestItem2", Rarity.B, "TestCat2");
        Item i3 = new Item("TestItem3", Rarity.C, "TestCat3");

        List<Item_Chance> lst = new ArrayList<Item_Chance>();

        Long id = repoItems.save(i);
        Long id2 = repoItems.save(i2);
        Long id3 = repoItems.save(i3);

        lst.add(new Item_Chance(id, 33.0));
        lst.add(new Item_Chance(id2, 33.0));
        lst.add(new Item_Chance(id3, 33.0));

        ItemPool pool = new ItemPool("TestPool", lst);

        Long idPool = repoProd.save(pool);

        u.getCustomPools().add(idPool);

        Long idUser = repoUsers.save(u);

        User u2 = repoUsers.getById(idUser);

        List<ItemPool> lstpools = repoProd.getItemPoolsByUser(u2);

        assertEquals(lstpools.size(), 1);
        assertEquals(lstpools.get(0).getItems().size(), 3);
    }
}
