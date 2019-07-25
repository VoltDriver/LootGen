package joel.lajoiecorriveau.lootgen;

import joel.lajoiecorriveau.lootgen.models.Item;
import joel.lajoiecorriveau.lootgen.models.Rarity;
import joel.lajoiecorriveau.lootgen.models.Token;
import joel.lajoiecorriveau.lootgen.models.User;
import joel.lajoiecorriveau.lootgen.models.repo.RepoItems;
import joel.lajoiecorriveau.lootgen.models.repo.RepoTokens;
import joel.lajoiecorriveau.lootgen.models.repo.RepoUsers;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1387434 on 2015-11-17.
 */
public class TestStockageToken extends TestCase{
    RepoTokens repoProd;
    RepoUsers repoUsers;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repoProd = RepoTokens.get();
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

        User testU = new User("Derek", "pass", "derek@derek.com");
        repoUsers.save(testU);

        Token p = Token.forUser(testU, 10);

        repoProd.save(p);

        assertEquals(repoProd.getAll().size(), 1);
    }

    public void testGetByID()
    {
        User testU = new User("Derek", "pass", "derek@derek.com");
        repoUsers.save(testU);
        Token p = Token.forUser(testU, 10);

        Long idDuProd = repoProd.save(p);

        Token p2 = repoProd.getById(idDuProd);

        assertTrue(p.getID().equals(p2.getID()));
        assertTrue(p.userID.equals(p2.userID));
    }

    public void testDeleteOne()
    {
        User testU = new User("Derek", "pass", "derek@derek.com");
        repoUsers.save(testU);
        Token p = Token.forUser(testU, 10);

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(p);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteOne2()
    {
        User testU = new User("Derek", "pass", "derek@derek.com");
        repoUsers.save(testU);
        Token p = Token.forUser(testU, 10);

        Long idDuProd = repoProd.save(p);

        repoProd.deleteOne(idDuProd);

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testDeleteALL()
    {
        User testU = new User("Derek", "pass", "derek@derek.com");
        repoUsers.save(testU);
        Token p = Token.forUser(testU, 10);

        repoProd.deleteAll();

        assertEquals(repoProd.getAll().size(), 0);
    }

    public void testGetTokenByUserID()
    {
        User testU = new User("Derek", "pass", "derek@derek.com");
        Long userId = repoUsers.save(testU);

        Token p = Token.forUser(testU, 10);

        Long id = repoProd.save(p);

        Token tt = null;

        try
        {
            tt = repoProd.getByUserID(userId);
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(true, tt.userID.equals(userId));
    }

    public void testGetTokenByUserEmail()
    {
        User testU = new User("Derek", "pass", "derek@derek.com");
        Long userId = repoUsers.save(testU);

        Token p = Token.forUser(testU, 10);

        Long id = repoProd.save(p);

        Token tt = null;

        try
        {
            tt = repoProd.getByUserEmail("derek@derek.com");
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(true, tt.userID.equals(userId));
    }

    public void testDeleteByUUID()
    {
        User testU = new User("Derek", "pass", "derek@derek.com");
        Long userId = repoUsers.save(testU);

        Token p = Token.forUser(testU, 10);

        Long id = repoProd.save(p);

        assertEquals(true, repoProd.getAll().size() == 1);

        Token tt = null;

        try
        {
            repoProd.deleteByUUID(p.id);
        }
        catch(Exception e)
        {
            fail();
        }

        assertEquals(true, repoProd.getAll().size() == 0);
    }
}
