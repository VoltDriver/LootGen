package joel.lajoiecorriveau.lootgen.models.repo;

import java.util.List;

import joel.lajoiecorriveau.lootgen.models.ItemPool;
import joel.lajoiecorriveau.lootgen.models.User;

/**
 * Created by Joel on 9/8/2015.
 */
public interface IRepoItemPools {
    long save(ItemPool o);

    void saveMany(Iterable<ItemPool> list);

    void saveMany(ItemPool... list);

    ItemPool getById(Long p);

    List<ItemPool> getItemPoolsByUser(User o);

    List<ItemPool> getAll();

    void deleteOne(Long o);

    void deleteOne(ItemPool o);

    void deleteAll();
}
