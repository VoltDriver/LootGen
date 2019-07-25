package joel.lajoiecorriveau.lootgen.models.repo;

import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import joel.lajoiecorriveau.lootgen.models.Item;
import joel.lajoiecorriveau.lootgen.models.ItemPool;
import joel.lajoiecorriveau.lootgen.models.User;

/**
 * Created by Joel on 9/8/2015.
 */
public class RepoItems implements IRepoItems{
    Gson gson = new Gson();
    Class<Item> classe = Item.class;
    private final File base;

    private static RepoItems repoUnique;

    private RepoItems()
    {
        this.base = new File("./data/"+classe.getSimpleName());
        this.createStorage();
    }

    public static synchronized RepoItems get()
    {
        if(repoUnique == null)
            repoUnique = new RepoItems();

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
                        if(ext.equals("Item"))
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

            for (Item a : getAll())
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
    public long save(Item a)
    {
        synchronized (classe) {
            // set the id
            if (a.getID() == null) a.setID(this.nextAvailableId());
            //
            String serialise = gson.toJson(a);
            try {
                FileUtils.writeStringToFile(new File(base, a.getID() + ".Item"), serialise);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return a.getID();

        }
    }

    @Override
    public void saveMany(Iterable<Item> list)
    {
        for (Item p : list ){
            this.save(p);
        }
    }

    @Override
    public void saveMany(Item... list)
    {
        for (Item p : list ){
            this.save(p);
        }
    }

    @Override
    public Item getById(Long id)
    {
        synchronized (classe)
        {
            String content;
            try
            {

                File f = new File(base, id+".Item");

                if(!f.exists()) return null;

                content = FileUtils.readFileToString(new File(base, id+".Item"));
                Item p = gson.fromJson(content, classe);

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
    public List<Item> getAll() {
        synchronized(classe)
        {
            List<Item> prods = new ArrayList<Item>();

            for(File f : base.listFiles())
            {
                String ext = FilenameUtils.getExtension(f.getName());
                if(ext.equals("Item")) {
                    try {
                        String content = FileUtils.readFileToString(f);
                        Item prod = gson.fromJson(content, classe);
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
    public void deleteOne(Item o) {
        synchronized (classe)
        {

            File f = new File(base, o.getID()+".Item");

            f.delete();
        }
    }

    @Override
    public void deleteAll() {
        deleteStorage();
        createStorage();
    }

    @Override
    public List<Item> getItemsByUser(User o) {
        synchronized(classe)
        {
            List<Item> prods = new ArrayList<Item>();

            for(int i = 0; i < o.getCustomItems().size(); i++)
            {
                prods.add(getById(o.getCustomItems().get(i)));
            }

            return prods;
        }
    }
}
