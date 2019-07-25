package joel.lajoiecorriveau.lootgen.models.repo;

import java.util.List;

import joel.lajoiecorriveau.lootgen.models.Item;
import joel.lajoiecorriveau.lootgen.models.User;

/**
 * Created by Joel on 9/8/2015.
 */
public interface IRepoItems {
    long save(Item o);

    void saveMany(Iterable<Item> list);

    void saveMany(Item... list);

    Item getById(Long p);

    List<Item> getItemsByUser(User o);

    List<Item> getAll();

    void deleteOne(Long o);

    void deleteOne(Item o);

    void deleteAll();
}
