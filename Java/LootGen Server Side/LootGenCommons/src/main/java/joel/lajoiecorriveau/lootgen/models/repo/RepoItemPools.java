package joel.lajoiecorriveau.lootgen.models.repo;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import joel.lajoiecorriveau.lootgen.models.ItemPool;
import joel.lajoiecorriveau.lootgen.models.User;

/**
 * Created by Joel on 9/8/2015.
 */
public class RepoItemPools implements IRepoItemPools {
    Gson gson = new Gson();
    Class<ItemPool> classe = ItemPool.class;

    private final File base;

    private static RepoItemPools repoUnique;

    private RepoItemPools()
    {
        this.base = new File("./data/"+classe.getSimpleName());
        this.createStorage();
    }

    public static synchronized RepoItemPools get()
    {
        if(repoUnique == null)
            repoUnique = new RepoItemPools();

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
                        if(ext.equals("ItemPool"))
                            f.delete();
                    }
                }
            }
            //folder.delete();
        }
        catch (Exception e)
        {
            System.out.println("FILE: Could not save properly. Errors happened.");
        }
    }

    private long nextAvailableId(){
        synchronized (classe) {
            long max = 0;

            for (ItemPool a : getAll())
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
    public long save(ItemPool a)
    {
        synchronized (classe) {
            // set the id
            if (a.getID() == null) a.setID(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);

            try {
                FileUtils.writeStringToFile(new File(base, a.getID() + ".ItemPool"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getID();

        }
    }

    @Override
    public void saveMany(Iterable<ItemPool> list)
    {
        for (ItemPool p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(ItemPool... list)
    {
        for (ItemPool p : list ){
            this.save(p);
        }
    }

    @Override
    public ItemPool getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {


                File f = new File(base, id+".ItemPool");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".ItemPool"));
                ItemPool p = gson.fromJson(content, classe);

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
    public List<ItemPool> getAll() {
        synchronized(classe)
        {
            List<ItemPool> prods = new ArrayList<ItemPool>();


            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("ItemPool")) {
                    try {
                        String content = FileUtils.readFileToString(f);
                        ItemPool prod = gson.fromJson(content, classe);
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
    public List<ItemPool> getItemPoolsByUser(User o) {
        synchronized(classe)
        {
            List<ItemPool> prods = new ArrayList<ItemPool>();

            for(int i = 0; i < o.getCustomPools().size(); i++)
            {
                prods.add(getById(o.getCustomPools().get(i)));
            }

            return prods;
        }
    }

    @Override
    public void deleteOne(Long o) {
        this.deleteOne(this.getById(o));
    }

    @Override
    public void deleteOne(ItemPool o) {
        synchronized (classe)
        {
            File f = new File(base, o.getID()+".ItemPool");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }
}
