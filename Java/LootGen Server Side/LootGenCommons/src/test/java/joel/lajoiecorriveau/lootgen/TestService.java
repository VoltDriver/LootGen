package joel.lajoiecorriveau.lootgen;

import java.util.ArrayList;
import java.util.List;

import joel.lajoiecorriveau.lootgen.models.exception.BadEmail;
import joel.lajoiecorriveau.lootgen.models.exception.BadItemName;
import joel.lajoiecorriveau.lootgen.models.exception.BadItemPoolChances;
import joel.lajoiecorriveau.lootgen.models.exception.BadItemPoolName;
import joel.lajoiecorriveau.lootgen.models.exception.BadLogin;
import joel.lajoiecorriveau.lootgen.models.exception.BadPassword;
import joel.lajoiecorriveau.lootgen.models.exception.InvalidConfirmation;
import joel.lajoiecorriveau.lootgen.models.exception.InvalidLoginAttempt;
import joel.lajoiecorriveau.lootgen.models.Item;
import joel.lajoiecorriveau.lootgen.models.exception.ItemNotFound;
import joel.lajoiecorriveau.lootgen.models.ItemPool;
import joel.lajoiecorriveau.lootgen.models.exception.ItemPoolNotFound;
import joel.lajoiecorriveau.lootgen.models.Item_Chance;
import joel.lajoiecorriveau.lootgen.models.exception.NoUserExist;
import joel.lajoiecorriveau.lootgen.models.Rarity;
import joel.lajoiecorriveau.lootgen.models.User;
import joel.lajoiecorriveau.lootgen.models.exception.UserAlreadyExists;
import joel.lajoiecorriveau.lootgen.service.Service;
import junit.framework.TestCase;

/**
 * Created by Joel on 9/8/2015.
 */
public class TestService extends TestCase
{
    Service service;

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

    public void testSaveAndGetAllUser(){
        User p = new User("Username", "Password", "test1@gmail.com");

        try
        {
            service.SaveUser(p);
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(service.GetAllUsers().size(), 1);
    }

    public void testCreateUser(){

        try
        {
            service.CreateUser("Username", "Password", "Password", "test1@gmail.com");
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(service.GetAllUsers().size(), 1);

        // Testing BadLogin

        try
        {
            service.CreateUser("U ser  na me", "Password", "Password", "test1@gmail.com");
            fail();
        }
        catch(BadLogin e)
        {

        }
        catch (Exception e)
        {
            fail();
        }

        // Testing BadPassword
        try
        {
            service.CreateUser("Username", "Pa ssw ord", "Password", "test1@gmail.com");
            fail();
        }
        catch(BadPassword e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
        // Testing BadEmail
        try
        {
            service.CreateUser("Username", "Password", "Password", "test1gmail.com");
            fail();
        }
        catch(BadEmail e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
        // Testing InvalidConfirmation
        try
        {
            service.CreateUser("Username", "Password", "Passwssord", "test1@gmail.com");
            fail();
        }
        catch(InvalidConfirmation e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
        // Testing UserAlreadyExists
        try
        {
            service.CreateUser("Username", "Password", "Password", "test2@gmail.com");
            fail();
        }
        catch(UserAlreadyExists e)
        {

        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            service.CreateUser("Username2", "Password", "Password", "test1@gmail.com");
            fail();
        }
        catch(UserAlreadyExists e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testSaveUserWithExceptions()
    {
        User p = new User("Username", "Password", "test1@gmail.com");

        try
        {
            service.SaveUser(p);
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(service.GetAllUsers().size(), 1);

        // Testing BadLogin
        User p2 = new User("U ser  na me2", "Password", "test2@gmail.com");

        try
        {
            service.SaveUser(p2);
            fail();
        }
        catch(BadLogin e)
        {

        }
        catch (Exception e)
        {
            fail();
        }

        // Testing BadPassword
        User p3 = new User("Username3", "Pass wor d", "test3@gmail.com");
        try
        {
            service.SaveUser(p3);
            fail();
        }
        catch(BadPassword e)
        {

        }
        catch(Exception e)
        {
            fail();
        }

        // Testing BadEmail
        User p4 = new User("Username4", "Password", "test4gmail.com");
        try
        {
            service.SaveUser(p4);
            fail();
        }
        catch(BadEmail e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testMultipleSaveAndGetAllUser()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        try
        {
            service.SaveManyUsers(p, p2, p3, p4);
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(service.GetAllUsers().size(), 4);
    }

    public void testMultipleSaveAndGetAll2User()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        List<User> prodlist = new ArrayList<User>();

        prodlist.add(p);
        prodlist.add(p2);
        prodlist.add(p3);
        prodlist.add(p4);

        try
        {
            service.SaveUsers(prodlist);
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(service.GetAllUsers().size(), 4);
    }

    public void testGetByIDUser()
    {
        User p = new User("Username", "Password", "test1@gmail.com");

        Long idDuProd = -1L;

        try
        {
            idDuProd = service.SaveUser(p);
        }
        catch(Exception e)
        {
            fail();
        }

        User p2 = null;
        try
        {
            p2 = service.GetUserByID(idDuProd);
        }
        catch (Exception e)
        {
            fail();
        }

        assertTrue(p.getEmail().equals(p2.getEmail()));
    }

    public void testDeleteOneUser()
    {
        User p = new User("Username", "Password", "test1@gmail.com");

        Long idDuProd = -1L;

        try
        {
            idDuProd = service.SaveUser(p);
        }
        catch(Exception e)
        {
            fail();
        }

        service.DeleteUser(p);

        assertEquals(service.GetAllUsers().size(), 0);
    }

    public void testDeleteOne2User()
    {
        User p = new User("Username", "Password", "test1@gmail.com");

        Long idDuProd = -1L;

        try
        {
            idDuProd = service.SaveUser(p);
        }
        catch(Exception e)
        {
            fail();
        }

        service.DeleteUserByID(idDuProd);

        assertEquals(service.GetAllUsers().size(), 0);
    }

    public void testDeleteALLUser()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        try
        {
            service.SaveManyUsers(p, p2, p3, p4);
        }
        catch(Exception e)
        {
            fail();
        }

        service.DeleteAllUsers();

        assertEquals(service.GetAllUsers().size(), 0);
    }

    public void testAuthentifyUser()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Turtle", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        try
        {
            service.SaveManyUsers(p,p3,p4);
        }
        catch(Exception e)
        {
            fail();
        }


        Long idOfUser2 = -1L;

        try
        {
            idOfUser2 = service.SaveUser(p2);
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            User u = service.Authentify("Username2", "BadPassword");
            fail();
        }
        catch (InvalidLoginAttempt e)
        {

        }
        catch(NoUserExist b)
        {
            fail();
        }

        User trueUser = null;

        try
        {
            trueUser = service.GetUserByID(idOfUser2);
        }
        catch (Exception e)
        {
            fail();
        }

        User authented = null;

        try
        {
            authented = service.Authentify("Username2", "Turtle");
        }
        catch (InvalidLoginAttempt e)
        {
            fail();
        }
        catch(NoUserExist b)
        {
            fail();
        }

        assertEquals(trueUser.getEmail(), authented.getEmail());
        assertEquals(trueUser.getID(), authented.getID());
        assertEquals(trueUser.getLogin(), authented.getLogin());

        try
        {
            User u = service.Authentify("DoesntExists", "BadPassword");
            fail();
        }
        catch (InvalidLoginAttempt e)
        {
            fail();
        }
        catch(NoUserExist b)
        {

        }
    }

    public void testGetUserByIDNoExist()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        List<User> prodlist = new ArrayList<User>();

        prodlist.add(p);
        prodlist.add(p2);
        prodlist.add(p3);
        prodlist.add(p4);

        try
        {
            service.SaveUsers(prodlist);
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(service.GetAllUsers().size(), 4);

        try
        {
            service.GetUserByID(999L);
            fail();
        }
        catch (NoUserExist e)
        {

        }
        catch (Exception e)
        {
            fail();
        }
    }

    public void testSaveItem()
    {
        Item i = new Item("ItemTest", Rarity.B, "Testcat");

        try
        {
            service.SaveItem(i);
        }
        catch(Exception e)
        {
            fail();
        }

        Item i2 = new Item("", Rarity.C, "Testcat");

        try
        {
            service.SaveItem(i2);
            fail();
        }
        catch(BadItemName e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetByIDItem()
    {
        Item p = new Item("ItemTest1", Rarity.A, "Test1");

        Long idDuProd = -1L;
        try
        {
            idDuProd = service.SaveItem(p);
        }
        catch(Exception e)
        {

        }

        Item p2 = null;

        try
        {
            p2 = service.GetItemByID(idDuProd);
        }
        catch(Exception e)
        {

        }


        assertTrue(p.getName().equals(p2.getName()));

        try
        {
            Item p3 = service.GetItemByID(9999L);
            fail();
        }
        catch(ItemNotFound e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testSaveItemPool()
    {
        Item_Chance it = new Item_Chance(30L, 100.0);
        List<Item_Chance> list = new ArrayList<Item_Chance>();
        list.add(it);
        ItemPool pool = new ItemPool("Test", list);

        try
        {
            service.SaveItemPools(pool);
        }
        catch(Exception e)
        {
            fail();
        }

        ItemPool pool2 = new ItemPool("", new ArrayList<Item_Chance>());

        try
        {
            service.SaveItemPools(pool2);
            fail();
        }
        catch(BadItemPoolName e)
        {

        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testGetItemPoolByID()
    {
        try
        {
            ItemPool p = service.GetItemPoolByID(999L);
            fail();
        }
        catch(ItemPoolNotFound b)
        {

        }
        catch(Exception e)
        {
            fail();
        }

        Item_Chance it = new Item_Chance(30L, 100.0);
        List<Item_Chance> list = new ArrayList<Item_Chance>();
        list.add(it);

        ItemPool pool = new ItemPool("Test", list);

        try
        {
            service.SaveItemPools(pool);
        }
        catch(Exception e)
        {
            fail();
        }

        try
        {
            ItemPool p = service.GetItemPoolByID(999L);
            fail();
        }
        catch(ItemPoolNotFound b)
        {

        }
        catch(Exception e)
        {
            fail();
        }
    }

    public void testLootGeneration()
    {
        User u = new User("Bob", "password", "test@gmail.com");

        Item i = new Item("TestItem", Rarity.D, "TestCat");
        Item i2 = new Item("TestItem2", Rarity.B, "TestCat2");
        Item i3 = new Item("TestItem3", Rarity.C, "TestCat3");

        List<Item_Chance> lst = new ArrayList<Item_Chance>();

        Long id = -1L;
        Long id2 = -1L;
        Long id3 = -1L;

        try
        {
            id = service.SaveItem(i);
            id2 = service.SaveItem(i2);
            id3 = service.SaveItem(i3);
        }
        catch (Exception e)
        {
            fail();
        }


        lst.add(new Item_Chance(id, 33.0));
        lst.add(new Item_Chance(id2, 33.0));
        lst.add(new Item_Chance(id3, 34.0));

        ItemPool pool = new ItemPool("TestPool", lst);

        lst.clear();
        lst.add(new Item_Chance(id, 50));
        lst.add(new Item_Chance(id2, 10));
        lst.add(new Item_Chance(id3, 40));

        ItemPool pool2 = new ItemPool("TestPool2", lst);

        lst.clear();
        lst.add(new Item_Chance(id, 70));
        lst.add(new Item_Chance(id2, 15));
        lst.add(new Item_Chance(id3, 15));

        ItemPool pool3 = new ItemPool("TestPool3", lst);

        Long idPool = -1L;
        Long idPool2 = -1L;
        Long idPool3 = -1L;

        try
        {
            idPool = service.SaveItemPools(pool);
            idPool2 = service.SaveItemPools(pool2);
            idPool3 = service.SaveItemPools(pool3);
        }
        catch (Exception e)
        {
            fail();
        }

        u.getCustomPools().add(idPool);

        try
        {
            service.SaveUser(u);
        }
        catch(Exception e)
        {
            fail();
        }

        List<Item> lstRandomLoot = new ArrayList<Item>();

        try
        {
            lstRandomLoot = service.GenerateLoot(pool, null, null, 0D, 0D, 0D, false, false, false, 5, false, u);
            fail();
        }
        catch (BadItemPoolChances h)
        {

        }
        catch (Exception e)
        {
            fail();
        }

        for (int c = 0; c < 10; c++)
        {
            try
            {
                lstRandomLoot = service.GenerateLoot(pool, null, null, 100D, 0D, 0D, false, false, false, 5, false, u);
            }
            catch (Exception e)
            {
                fail();
            }
        }

        for (int c = 0; c < 10; c++)
        {
            try
            {
                lstRandomLoot = service.GenerateLoot(pool, pool2, pool3, 50D, 25D, 25D, false, false, false, 3, false, u);
            }
            catch (Exception e)
            {
                fail();
            }
        }

        for (int c = 0; c < 10; c++)
        {
            try
            {
                lstRandomLoot = service.GenerateLoot(pool, pool2, pool3, 30D, 10D, 60D, false, true, false, 3, false, u);
            }
            catch (Exception e)
            {
                fail();
            }
        }
    }
}
