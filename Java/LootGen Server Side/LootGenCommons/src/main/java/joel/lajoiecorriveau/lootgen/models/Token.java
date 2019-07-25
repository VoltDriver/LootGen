package joel.lajoiecorriveau.lootgen.models;

import java.util.Date;
import java.util.UUID;

import org.joda.time.DateTime;

public class Token {

    public Long dbid;

    public Long getID()
    {
        return dbid;
    }

    public void setID(Long pid)
    {
        dbid = pid;
    }

    public String id;

    public Long userID;

    public Date expirationDate;

    public static Token forUser(User u, int validityInDays) {
        Token t = new Token();
        t.expirationDate = DateTime.now().plusDays(validityInDays).toDate();
        t.userID = u.getID();
        t.id = UUID.randomUUID().toString();
        return t;
    }

}