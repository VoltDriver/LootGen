package joel.lajoiecorriveau.lootgen.transfer;

import joel.lajoiecorriveau.lootgen.models.ItemPool;
import joel.lajoiecorriveau.lootgen.models.User;

/**
 * Created by Joel on 11/26/2015.
 */
public class GenLootData {
    public ItemPool pool1;
    public ItemPool pool2;
    public ItemPool pool3;

    public Double pool1Chance;
    public Double pool2Chance;
    public Double pool3Chance;

    public Boolean pool1EQ;
    public Boolean pool2EQ;
    public Boolean pool3EQ;


    public int nbrOfItems;

    public boolean instant;
	
	public User user;
}
