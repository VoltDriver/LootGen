package joel.lajoiecorriveau.lootgen;

import java.util.ArrayList;
import java.util.List;

import joel.lajoiecorriveau.lootgen.models.exception.InvalidLoginAttempt;
import joel.lajoiecorriveau.lootgen.models.exception.NoUserExist;
import joel.lajoiecorriveau.lootgen.models.User;
import joel.lajoiecorriveau.lootgen.models.repo.RepoUsers;
import junit.framework.TestCase;

/**
 * Created by Joel on 9/8/2015.
 */
public class TestStockageUser extends TestCase
{
    RepoUsers repoProd;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoProd = RepoUsers.get();
        //repoProd = new RepositoryProduitsFichiers();
        repoProd.deleteAll();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        repoProd.deleteAll();
        repoProd = null;
    }

    public void testSaveAndGetAll(){
        User p = new User("Username", "Password", "test1@gmail.com");
        repoProd.save(p);
        assertEquals(repoProd.getAll().size(), 1);
    }

    public void testMultipleSaveAndGetAll()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        repoProd.saveMany(p,p2,p3,p4);

        assertEquals(repoProd.getAll().size(), 4);
    }

    public void testMultipleSaveAndGetAll2()
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

        repoProd.saveMany(prodlist);

        assertEquals(repoProd.getAll().size(), 4);
    }

    public void testGetByID()
    {
        User p = new User("Username", "Password", "test1@gmail.com");

        Long idDuProd = repoProd.save(p);

        User p2 = repoProd.getById(idDuProd);

        assertTrue(p.getEmail().equals(p2.getEmail()));
    }

    public void testDeleteOne()
    {
        User p = new User("Username", "Password", "test1@gmail.com");

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(p);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        User p = new User("Username", "Password", "test1@gmail.com");

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(idDuProd);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        repoProd.saveMany(p,p2,p3,p4);

        repoProd.deleteAll();

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testAuthentify()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Turtle", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        repoProd.saveMany(p,p3,p4);

        Long idOfUser2 = repoProd.save(p2);

        try
        {
            User u = repoProd.authentify("Username2", "BadPassword");
            fail();
        }
        catch (InvalidLoginAttempt e)
        {

        }
        catch(NoUserExist b)
        {
            fail();
        }

        User trueUser = repoProd.getById(idOfUser2);
        User authented = null;

        try
        {
            authented = repoProd.authentify("Username2", "Turtle");
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
            User u = repoProd.authentify("DoesntExists", "BadPassword");
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

    public void testEmailTaken()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        repoProd.saveMany(p,p2,p3,p4);

        assertEquals(repoProd.EmailTaken("test4@gmail.com"), true);
        assertEquals(repoProd.EmailTaken("test1@gmail.com"), true);
        assertEquals(repoProd.EmailTaken("nottaken@gmail.com"), false);
    }

    public void testUsernameTaken()
    {
        User p = new User("Username", "Password", "test1@gmail.com");
        User p2 = new User("Username2", "Password2", "test2@gmail.com");
        User p3 = new User("Username3", "Password3", "test3@gmail.com");
        User p4 = new User("Username4", "Password4", "test4@gmail.com");

        repoProd.saveMany(p,p2,p3,p4);

        assertEquals(repoProd.UserNameTaken("Username"), true);
        assertEquals(repoProd.UserNameTaken("Username3"), true);
        assertEquals(repoProd.UserNameTaken("NotTaken"), false);
    }
}
