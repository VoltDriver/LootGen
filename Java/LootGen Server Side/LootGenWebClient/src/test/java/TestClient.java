import joel.lajoiecorriveau.lootgen.models.*;
import joel.lajoiecorriveau.lootgen.models.exception.*;
import joel.lajoiecorriveau.lootgen.service.Service;
import joel.lajoiecorriveau.lootgen.transfer.GenLootData;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 11/24/2015.
 */
public class TestClient extends TestCase{

    String base = "http://localhost:7026/rest/";

    WebClient wcs;

    public TestClient() throws URISyntaxException
    {
        wcs = new WebClient(base);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        wcs.deleteAllItemPools();
        wcs.deleteAllItems();
        wcs.deleteAllUsers();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        wcs.deleteAllItemPools();
        wcs.deleteAllItems();
        wcs.deleteAllUsers();
    }

    public void testSignUpFail() throws IOException {
        try {
            wcs.signUp("Derek2","pass", "pass", "");
            Assert.fail();
        } catch (BadEmail e) {
            /* OK */
        }
        catch (BadPassword e2)
        {
            /* OK */
        }
        catch (UserAlreadyExists e2)
        {
            /* OK */
        }
        catch (InvalidConfirmation e2)
        {
            /* OK */
        }
        catch (NoToken e)
        {
            fail();
        }
    }

    public void testSignUpOk() throws IOException, BadEmail{
        try {
            wcs.signUp("Derek2","pass", "pass", "derek@derek.com");

        } catch (BadEmail e) {
            Assert.fail();
        }
        catch (BadPassword e2)
        {
            Assert.fail();
        }
        catch (UserAlreadyExists e2)
        {
            Assert.fail();
        }
        catch (InvalidConfirmation e2)
        {
            Assert.fail();
        }
        catch (NoToken e)
        {
            fail();
        }
    }

    public void testSignIn() throws IOException
    {
        try
        {
            wcs.signUp("Derek2","pass", "pass", "derek@derek.com");
            User u = wcs.signin("Derek2", "pass");

            assertEquals(true, u.getEmail().equals("derek@derek.com"));

            User u2 = wcs.getCurrentUser();

            assertEquals(true, u2.getEmail().equals("derek@derek.com"));

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testSignOut()
    {
        try
        {
            wcs.signUp("Derek2","pass", "pass", "derek@derek.com");
            User u = wcs.signin("Derek2", "pass");

            assertEquals(true, u.getEmail().equals("derek@derek.com"));

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            User u = wcs.getCurrentUser();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(NoUserExist e)
        {
            fail();
        }
        catch (NoToken e)
        {
            // OK
        }
    }

    public void testData(){
        try {
            wcs.fakeload();

        }
        catch (IOException e)
        {
            Assert.fail();
        }
        catch (Exception e)
        {
            Assert.fail();
        }

        try
        {
            wcs.getAllUsers();
        }
        catch (IOException e)
        {
            Assert.fail();
        }
        catch (NoToken e)
        {
            fail();
        }
    }

    public void testGetAllUsers()
    {
        try {
            wcs.fakeload();

        }
        catch (IOException e)
        {
            Assert.fail();
        }
        catch (Exception e)
        {
            Assert.fail();
        }

        try
        {
            wcs.getAllUsers();
        }
        catch (IOException e)
        {
            Assert.fail();
        }
        catch (NoToken e)
        {
            fail();
        }
    }

    public void testGetAndSaveUser()
    {
        Long o = null;
        try
        {
            wcs.signUp("testUser", "pass", "pass", "test@test.com");
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            List<User> lst = wcs.getAllUsers();

            User user = null;

            for(User u : lst)
            {
                if(u.getLogin().equals("testUser"))
                {
                    user = u;
                }
            }


            user.setEmail("changed@changed.com");

            o = user.getID();
            wcs.saveUser(user);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            User u2 = wcs.getUserById(o);
            assertEquals(true, u2.getEmail().equals("changed@changed.com"));
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetAndSaveMultipleUsers()
    {
        Long o1 = null;
        Long o2 = null;
        try
        {
            o1 = wcs.signUp("testUser", "pass", "pass", "test@test.com");
            o2 = wcs.signUp("grog", "gregory", "gregory", "greg@greg.com");
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {

            User u1 = wcs.getUserById(o1);
            User u2 = wcs.getUserById(o2);

            u1.setEmail("changed@changed.com");
            u2.setEmail("bob@bobby.com");

            List<User> lst = new ArrayList<User>();
            lst.add(u1);
            lst.add(u2);

            wcs.saveUsers(lst);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            User u1 = wcs.getUserById(o1);
            User u2 = wcs.getUserById(o2);
            assertEquals(true, u1.getEmail().equals("changed@changed.com"));
            assertEquals(true, u2.getEmail().equals("bob@bobby.com"));
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetAllUsers2()
    {
        try
        {
            wcs.signUp("testUser", "pass", "pass", "test@test.com");
            wcs.signUp("grog", "gregory", "gregory", "greg@greg.com");
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            List<User> lstu = wcs.getAllUsers();
            assertEquals(2, lstu.size());
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetUserById()
    {

        Long o1 = null;
        Long o2 = null;

        try
        {
            o1 = wcs.signUp("testUser", "pass", "pass", "test@test.com");
            o2 = wcs.signUp("grog", "gregory", "gregory", "greg@greg.com");
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            User u1 = wcs.getUserById(o1);
            User u2 = wcs.getUserById(o2);
            assertEquals(true, u1.getEmail().equals("test@test.com"));
            assertEquals(true, u2.getEmail().equals("greg@greg.com"));
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetCurrentUser()
    {

        Long o1 = null;
        Long o2 = null;

        try
        {
            o1 = wcs.signUp("testUser", "pass", "pass", "test@test.com");
            o2 = wcs.signUp("grog", "gregory", "gregory", "greg@greg.com");
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            wcs.signin("grog", "gregory");

            User u = wcs.getCurrentUser();

            assertEquals(true, u.getEmail().equals("greg@greg.com"));

            wcs.signout();
            wcs.signin("testUser", "pass");
            u = wcs.getCurrentUser();

            assertEquals(true, u.getEmail().equals("test@test.com"));

        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteUserById()
    {

        Long o1 = null;
        Long o2 = null;

        try
        {
            o1 = wcs.signUp("testUser", "pass", "pass", "test@test.com");
            o2 = wcs.signUp("grog", "gregory", "gregory", "greg@greg.com");
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllUsers().size() == 2);

            wcs.deleteUserById(o1);
            assertEquals(true, wcs.getAllUsers().size() == 1);

            wcs.deleteUserById(o2);
            assertEquals(true, wcs.getAllUsers().size() == 0);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }


    public void testDeleteUser()
    {

        Long o1 = null;
        Long o2 = null;

        User u1 = null;
        User u2 = null;
        try
        {
            o1 = wcs.signUp("testUser", "pass", "pass", "test@test.com");
            o2 = wcs.signUp("grog", "gregory", "gregory", "greg@greg.com");

            u1 = wcs.getUserById(o1);
            u2 = wcs.getUserById(o2);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllUsers().size() == 2);

            wcs.deleteUser(u1);
            assertEquals(true, wcs.getAllUsers().size() == 1);

            wcs.deleteUser(u2);
            assertEquals(true, wcs.getAllUsers().size() == 0);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteAllUsers()
    {

        Long o1 = null;
        Long o2 = null;
        try
        {
            o1 = wcs.signUp("testUser", "pass", "pass", "test@test.com");
            o2 = wcs.signUp("grog", "gregory", "gregory", "greg@greg.com");

        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllUsers().size() == 2);

            wcs.deleteAllUsers();

            assertEquals(true, wcs.getAllUsers().size() == 0);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetItemByIdAndSaveItem()
    {
        Long o1 = null;
        Long o2 = null;

        Long userId = null;
        try
        {
            userId = wcs.signUp("testUser", "pass", "pass", "test@test.com");

            wcs.signin("testUser", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllItems().size() == 2);

            Item i1 = wcs.getItemById(o1);

            i1.setName("I HAVE CHANGED");

            wcs.saveItem(i1);

            Item i2 = wcs.getItemById(o1);

            assertEquals(true, i2.getName().equals("I HAVE CHANGED"));

//            User u = wcs.getUserById(userId);

            List<Item> lstCustom = wcs.getItemsByUser();

            assertEquals(2, lstCustom.size());

            assertEquals(true, lstCustom.get(0).getName().equals("I HAVE CHANGED"));

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testSaveManyItems()
    {
        try
        {
            Long o1 = wcs.signUp("testUser", "pass", "pass", "test@test.com");

            wcs.signin("testUser", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            List<Item> lst = new ArrayList<Item>();

            lst.add(i1);

            lst.add(i2);

            wcs.saveManyItems(lst);

            User u = wcs.getUserById(o1);

            List<Item> lstCustom = wcs.getItemsByUser();

            assertEquals(2, lstCustom.size());

            assertEquals(true, lstCustom.get(0).getName().equals("Test1"));

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllItems().size() == 2);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetItemsByUser()
    {
        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");
            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            List<Long> lst = new ArrayList<Long>();

            Long oi1 = wcs.saveItem(i1);
            Long oi2 = wcs.saveItem(i2);

//            Long o = wcs.signUp("test", "pass", "pass", "testi@test.com");


//            User u = wcs.getUserById(o);
//            u.getCustomItems().add(oi1);
//            u.getCustomItems().add(oi2);

//            wcs.saveUser(u);

//            wcs.signin("test", "pass");

            List<Item> got = wcs.getItemsByUser();

            assertEquals(true, got.get(0).getName().equals("Test1"));
            assertEquals(true, got.get(1).getName().equals("Test2"));

            wcs.signout();

        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetAllItems()
    {
        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            assertEquals(0, wcs.getAllItems().size());
            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");


            Long oi1 = wcs.saveItem(i1);
            Long oi2 = wcs.saveItem(i2);


            assertEquals(2, wcs.getAllItems().size());
            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteItemById()
    {
        Long o1 = null;
        Long o2 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllItems().size() == 2);

            wcs.deleteItemById(o1);

            assertEquals(true, wcs.getAllItems().size() == 1);

            wcs.deleteItemById(o2);

            assertEquals(true, wcs.getAllItems().size() == 0);

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteItem()
    {
        Long o1 = null;
        Long o2 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllItems().size() == 2);

            Item i1 = wcs.getItemById(o1);
            Item i2 = wcs.getItemById(o2);

            wcs.deleteItem(i1);

            assertEquals(true, wcs.getAllItems().size() == 1);

            wcs.deleteItem(i2);

            assertEquals(true, wcs.getAllItems().size() == 0);

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteAllItems()
    {
        Long o1 = null;
        Long o2 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            assertEquals(true, wcs.getAllItems().size() == 2);

            wcs.deleteAllItems();

            assertEquals(true, wcs.getAllItems().size() == 0);

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testSaveItemPools()
    {
        Long o1 = null;
        Long o2 = null;

        ItemPool p1 = null;

        try
        {

            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic2);

            p1 = new ItemPool("PoolTest", lst);

            wcs.saveItemPools(p1);

            assertEquals(1, wcs.getAllItemPools().size());

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testSaveManyItemPoolsAndGetAllPools()
    {
        Long o1 = null;
        Long o2 = null;
        Long o3 = null;

        ItemPool p1 = null;
        ItemPool p2 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            Item i3 = new Item("Test3", Rarity.E, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
            o3 = wcs.saveItem(i3);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);
            Item_Chance ic3 = new Item_Chance(o3, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic3);

            List<Item_Chance> lst2 = new ArrayList<Item_Chance>();

            lst2.add(ic1);
            lst2.add(ic2);

            p1 = new ItemPool("PoolTest", lst);
            p2 = new ItemPool("PoolTest2", lst2);

            List<ItemPool> lstPools = new ArrayList<ItemPool>();

            lstPools.add(p1);
            lstPools.add(p2);

            wcs.saveManyItemPools(lstPools);

            assertEquals(2, wcs.getAllItemPools().size());

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetItemPoolById()
    {
        Long o1 = null;
        Long o2 = null;

        ItemPool p1 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic2);

            p1 = new ItemPool("PoolTest", lst);

            Long o3 = wcs.saveItemPools(p1);

            assertEquals(1, wcs.getAllItemPools().size());

            ItemPool pool = wcs.getItemPoolsById(o3);

            assertEquals(2, pool.getItems().size());
            assertEquals(true, pool.getName().equals("PoolTest"));

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetItemPoolsByUser()
    {
        Long o1 = null;
        Long o2 = null;
        Long o3 = null;

        ItemPool p1 = null;
        ItemPool p2 = null;

        try
        {

            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            Item i3 = new Item("Test3", Rarity.E, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
            o3 = wcs.saveItem(i3);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);
            Item_Chance ic3 = new Item_Chance(o3, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic3);

            List<Item_Chance> lst2 = new ArrayList<Item_Chance>();

            lst2.add(ic1);
            lst2.add(ic2);

            p1 = new ItemPool("PoolTest", lst);
            p2 = new ItemPool("PoolTest2", lst2);

            Long pid1 = wcs.saveItemPools(p1);
            Long pid2 = wcs.saveItemPools(p2);

//            User u = wcs.getUserById(o);
//            u.getCustomPools().add(pid1);
//            u.getCustomPools().add(pid2);

            assertEquals(2, wcs.getAllItemPools().size());

//            wcs.saveUser(u);

            List<ItemPool> pp = wcs.getItemPoolsByUser();

            assertEquals(2, pp.size());
            assertEquals(true, pp.get(0).getName().equals("PoolTest"));

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteItemPoolById()
    {
        Long o1 = null;
        Long o2 = null;
        Long o3 = null;

        ItemPool p1 = null;
        ItemPool p2 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            Item i3 = new Item("Test3", Rarity.E, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
            o3 = wcs.saveItem(i3);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);
            Item_Chance ic3 = new Item_Chance(o3, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic3);

            List<Item_Chance> lst2 = new ArrayList<Item_Chance>();

            lst2.add(ic1);
            lst2.add(ic2);

            p1 = new ItemPool("PoolTest", lst);
            p2 = new ItemPool("PoolTest2", lst2);

            Long pid1 = wcs.saveItemPools(p1);
            Long pid2 = wcs.saveItemPools(p2);


//            User u = wcs.getUserById(o);
//            u.getCustomPools().add(pid1);
//            u.getCustomPools().add(pid2);

            assertEquals(2, wcs.getAllItemPools().size());

//            wcs.saveUser(u);

            wcs.deleteItemPoolById(pid2);

            assertEquals(1, wcs.getAllItemPools().size());

            wcs.deleteItemPoolById(pid1);

            assertEquals(0, wcs.getAllItemPools().size());

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteItemPool()
    {
        Long o1 = null;
        Long o2 = null;
        Long o3 = null;

        ItemPool p1 = null;
        ItemPool p2 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            Item i3 = new Item("Test3", Rarity.E, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
            o3 = wcs.saveItem(i3);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);
            Item_Chance ic3 = new Item_Chance(o3, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic3);

            List<Item_Chance> lst2 = new ArrayList<Item_Chance>();

            lst2.add(ic1);
            lst2.add(ic2);

            p1 = new ItemPool("PoolTest", lst);
            p2 = new ItemPool("PoolTest2", lst2);

            Long pid1 = wcs.saveItemPools(p1);
            Long pid2 = wcs.saveItemPools(p2);

//            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");
//
//
//            User u = wcs.getUserById(o);
//            u.getCustomPools().add(pid1);
//            u.getCustomPools().add(pid2);

            assertEquals(2, wcs.getAllItemPools().size());

//            wcs.saveUser(u);

            wcs.deleteItemPool(p1);

            assertEquals(1, wcs.getAllItemPools().size());

            wcs.deleteItemPool(p2);

            assertEquals(0, wcs.getAllItemPools().size());

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testDeleteAllItemPools()
    {
        Long o1 = null;
        Long o2 = null;
        Long o3 = null;

        ItemPool p1 = null;
        ItemPool p2 = null;

        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            Item i3 = new Item("Test3", Rarity.E, "NoCat");
            o1 = wcs.saveItem(i1);
            o2 = wcs.saveItem(i2);
            o3 = wcs.saveItem(i3);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);
            Item_Chance ic3 = new Item_Chance(o3, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic3);

            List<Item_Chance> lst2 = new ArrayList<Item_Chance>();

            lst2.add(ic1);
            lst2.add(ic2);

            p1 = new ItemPool("PoolTest", lst);
            p2 = new ItemPool("PoolTest2", lst2);

            Long pid1 = wcs.saveItemPools(p1);
            Long pid2 = wcs.saveItemPools(p2);

//            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");
//
//
//            User u = wcs.getUserById(o);
//            u.getCustomPools().add(pid1);
//            u.getCustomPools().add(pid2);

            assertEquals(2, wcs.getAllItemPools().size());

//            wcs.saveUser(u);

            wcs.deleteAllItemPools();

            assertEquals(0, wcs.getAllItemPools().size());

            wcs.signout();
        }
        catch(IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testParseRarity()
    {
        try
        {
            Rarity r = wcs.parseRarity("A");

            assertEquals(true, r.toString().equals("A"));

            Rarity r2 = wcs.parseRarity("B");

            assertEquals(true, r2.toString().equals("B"));
        }
        catch (IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGenerateLoot()
    {
        try
        {
            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");

            wcs.signin("test", "pass");

            Item i1 = new Item("Test1", Rarity.D, "NoCat");
            Item i2 = new Item("Test2", Rarity.C, "NoCat");
            Item i3 = new Item("Test3", Rarity.E, "NoCat");
            Long o1 = wcs.saveItem(i1);
            Long o2 = wcs.saveItem(i2);
            Long o3 = wcs.saveItem(i3);

            Item_Chance ic1 = new Item_Chance(o1, 50);
            Item_Chance ic2 = new Item_Chance(o2, 50);
            Item_Chance ic3 = new Item_Chance(o3, 50);

            List<Item_Chance> lst = new ArrayList<Item_Chance>();

            lst.add(ic1);
            lst.add(ic3);

            List<Item_Chance> lst2 = new ArrayList<Item_Chance>();

            lst2.add(ic1);
            lst2.add(ic2);

            ItemPool p1 = new ItemPool("PoolTest", lst);
            ItemPool  p2 = new ItemPool("PoolTest2", lst2);

            Long pid1 = wcs.saveItemPools(p1);
            Long pid2 = wcs.saveItemPools(p2);

//            Long o = wcs.signUp("test", "pass", "pass", "test@test.com");
//
//
//            User u = wcs.getUserById(o);
//            u.getCustomPools().add(pid1);
//            u.getCustomPools().add(pid2);
//
//            u.getCustomItems().add(o1);
//            u.getCustomItems().add(o2);
//            u.getCustomItems().add(o3);
//
//            wcs.saveUser(u);

            GenLootData lootdat = new GenLootData();

            lootdat.pool1 = p1;
            lootdat.pool2 = p2;
            lootdat.pool3 = null;

            lootdat.pool1EQ = false;
            lootdat.pool2EQ = false;
            lootdat.pool3EQ = false;

            lootdat.pool1Chance = 66.0;
            lootdat.pool2Chance = 34.0;
            lootdat.pool3Chance = 0.0;

            lootdat.nbrOfItems = 5;

            lootdat.user = wcs.getCurrentUser();

            lootdat.instant = true;

            List<Item> loot = wcs.generateLoot(lootdat);

            assertEquals(5, loot.size());

            wcs.signout();
        }
        catch (IOException e)
        {
            fail();
        }
        catch(Exception e)
        {
            fail();
        }
    }

//    public void testGetTokenByUserId()
//    {
//        try
//        {
//
//        }
//        catch (IOException e)
//        {
//            fail();
//        }
//        catch(Exception e)
//        {
//            fail();
//        }
//    }
//    public void testGetTokenByUserEmail()
//    {
//        try
//        {
//            Rarity r = wcs.parseRarity("A");
//
//            assertEquals(true, r.toString().equals("A"));
//
//            Rarity r2 = wcs.parseRarity("B");
//
//            assertEquals(true, r2.toString().equals("B"));
//        }
//        catch (IOException e)
//        {
//            fail();
//        }
//        catch(Exception e)
//        {
//            fail();
//        }
//    }
}
