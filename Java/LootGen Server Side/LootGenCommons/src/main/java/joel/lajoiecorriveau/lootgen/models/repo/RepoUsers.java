package joel.lajoiecorriveau.lootgen.models.repo;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.util.ArrayList;
import java.util.List;

import joel.lajoiecorriveau.lootgen.models.exception.InvalidLoginAttempt;
import joel.lajoiecorriveau.lootgen.models.exception.NoUserExist;
import joel.lajoiecorriveau.lootgen.models.User;
import sun.rmi.runtime.Log;

/**
 * Created by Joel on 9/8/2015.
 */
public class RepoUsers implements IRepoUsers{
    Gson gson = new Gson();
    Class<User> classe = User.class;
    private final File base;

//    Context context;

    private static RepoUsers repoUnique;

//    private RepoUsers(Context c)
//    {
//        this.context = c;
//        this.createStorage();
//    }

    private RepoUsers()
    {
        this.base = new File("./data/"+classe.getSimpleName());
        this.createStorage();
    }

//    public static synchronized RepoUsers get(Context context)
//    {
//        if(repoUnique == null)
//            repoUnique = new RepoUsers(context);
//
//        return repoUnique;
//    }

    public static synchronized RepoUsers get()
    {
        if(repoUnique == null)
            repoUnique = new RepoUsers();

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
                        if(ext.equals("User"))
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

            for (User a : getAll())
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
    public long save(User a)
    {
        synchronized (classe) {
            // set the id
            if (a.getID() == null) a.setID(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);

            try {
                FileUtils.writeStringToFile(new File(base, a.getID() + ".User"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getID();

        }
    }

    @Override
    public void saveMany(Iterable<User> list)
    {
        for (User p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(User... list)
    {
        for (User p : list ){
            this.save(p);
        }
    }

    @Override
    public User getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {


                File f = new File(base, id+".User");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".User"));
                User p = gson.fromJson(content, classe);

                return p;
            }
            catch (Exception e)
            {
                System.out.println("ERROR :Could not retrieve product with id " + id.toString() + ".");
                return null;
            }
        }
    }

    @Override
    public List<User> getAll() {
        synchronized(classe)
        {
            List<User> prods = new ArrayList<User>();


            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("User")) {
                    try {
                        String content = FileUtils.readFileToString(f);
                        User prod = gson.fromJson(content, classe);
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
    public void deleteOne(User o) {
        synchronized (classe)
        {

            File f = new File(base, o.getID()+".User");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }

    @Override
    public User authentify(String pUsername, String pPassword) throws InvalidLoginAttempt, NoUserExist {
        List<User> users = this.getAll();

        for(User u : users)
        {
            if(u.getLogin().equals(pUsername))
            {
                if(u.getPassword().equals(pPassword))
                {
                    return u;
                }
                else
                {
                    throw new InvalidLoginAttempt("Invalid Login or Password.");
                }
            }
        }

        throw new NoUserExist("No user exists with this Login.");
    }

    @Override
    public boolean UserNameTaken(String pUsername) {
        List<User> users = this.getAll();

        for(User u : users)
        {
            if(u.getLogin().equals(pUsername))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean EmailTaken(String pEmail) {
        List<User> users = this.getAll();

        for(User u : users)
        {
            if(u.getEmail().equals(pEmail))
            {
                return true;
            }
        }

        return false;
    }
}
