package joel.lajoiecorriveau.lootgen.appli.events;

import joel.lajoiecorriveau.lootgen.models.DisplayItem;

/**
 * Created by Vero on 10/15/2015.
 */
public class PoolSelected {
    public DisplayItem Pool;

    public PoolSelected(DisplayItem ppool)
    {
        Pool = ppool;
    }
}
