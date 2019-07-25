package joel.lajoiecorriveau.lootgen.models.repo;

import com.google.gson.Gson;

import joel.lajoiecorriveau.lootgen.models.exception.NoToken;
import joel.lajoiecorriveau.lootgen.models.exception.NoUserExist;
import joel.lajoiecorriveau.lootgen.service.Service;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import joel.lajoiecorriveau.lootgen.models.Token;
import joel.lajoiecorriveau.lootgen.models.User;

/**
 * Created by Joel on 9/8/2015.
 */
public class RepoTokens implements IRepoTokens{
    Gson gson = new Gson();
    Class<Token> classe = Token.class;
    private final File base;

    private static RepoTokens repoUnique;

    private RepoTokens()
    {
        this.base = new File("./data/"+classe.getSimpleName());
        this.createStorage();
    }

    public static synchronized RepoTokens get()
    {
        if(repoUnique == null)
            repoUnique = new RepoTokens();

        return repoUnique;
    }

    public void createStorage()
    {
        base.mkdirs();
    }

    public void deleteStorage()
    {
        deleteFolder(base);
    }

    private static void deleteFolder(File folder)
    {
        try
        {
            File[] files = folder.listFiles();
            if(files != null)
            {
                for (File f : files)
                {
                    if (f.isDirectory())
                        deleteFolder(f);
                    else
                    {
                        String ext = FilenameUtils.getExtension(f.getName());
                        if(ext.equals("Token"))
                            f.delete();
                    }
                }
            }
            //folder.delete();
        }
        catch (Exception e)
        {
            System.out.println("FILE : Could not save properly. Errors happened.");
        }
    }

    private long nextAvailableId(){
        synchronized (classe) {
            long max = 0;

            for (Token a : getAll())
            {
                if (a.getID() > max)
                {
                    max = a.getID();
                }
            }
            return max+1;
        }
    }


    @Override
    public long save(Token a)
    {
        synchronized (classe) {
            // set the id
            if (a.getID() == null) a.setID(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            try {
                FileUtils.writeStringToFile(new File(base, a.getID() + ".Token"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getID();

        }
    }

    @Override
    public void saveMany(Iterable<Token> list)
    {
        for (Token p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(Token... list)
    {
        for (Token p : list ){
            this.save(p);
        }
    }

    @Override
    public Token getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {

                File f = new File(base, id+".Token");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".Token"));
                Token p = gson.fromJson(content, classe);

                return p;
            }
            catch (Exception e)
            {
                System.out.println("ERROR :Could not retrieve Token with id " + id.toString() + ".");
                return null;
            }
        }
    }

    @Override
    public List<Token> getAll() {
        synchronized(classe)
        {
            List<Token> prods = new ArrayList<Token>();

            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("Token")) {
                    try {
                        String content = FileUtils.readFileToString(f);
                        Token prod = gson.fromJson(content, classe);
                        prods.add(prod);
                    } catch (Exception e) {
                        System.out.println("ERROR :Could not read file " + f.getName() + ".");
                        e.printStackTrace();
                    }
                }
            }

            return prods;
        }
    }

    @Override
    public void deleteOne(Long o) {
        this.deleteOne(this.getById(o));
    }

    @Override
    public void deleteOne(Token o) {
        synchronized (classe)
        {

            File f = new File(base, o.getID()+".Token");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }



    @Override
    public Token getByUserID(Long o) throws NoToken{
        synchronized(classe)
        {
            for(Token t : getAll())
            {
                if(t.userID.equals(o))
                {
                    return t;
                }
            }

            throw new NoToken("No token with userID : " + o.toString());
        }
    }

    @Override
    public Token getByUserEmail(String o) throws NoToken, NoUserExist{
        synchronized(classe)
        {
            List<User> users = RepoUsers.get().getAll();

            User user = null;

            for(User u : users)
            {
                if(u.getEmail().equals(o))
                {
                    user = u;
                }
            }

            if(user == null)
            {
                throw new NoUserExist("Could not found a user with email : " + o);
            }

            return getByUserID(user.getID());
        }
    }

    @Override
    public void deleteByUUID(String o) throws NoToken{
        synchronized (classe)
        {

            for(Token t : getAll())
            {
                if(t.id.equals(o))
                {
                    deleteOne(t);
                    return;
                }
            }

            throw new NoToken("No token exists with this UUID : " + o);
        }
    }

    @Override
    public Token getByUUID(String o) throws NoToken{
        synchronized (classe)
        {

            for(Token t : getAll())
            {
                if(t.id.equals(o))
                {
                    return t;
                }
            }

            throw new NoToken("No token exists with this UUID : " + o);
        }
    }
}
