package joel.lajoiecorriveau.lootgen.appli.events;

import joel.lajoiecorriveau.lootgen.models.DisplayItem;

/**
 * Created by Joel on 10/31/2015.
 */
public class DeleteItemPressed {
    public DisplayItem content;

    public DeleteItemPressed(DisplayItem p)
    {
        content = p;
    }
}
