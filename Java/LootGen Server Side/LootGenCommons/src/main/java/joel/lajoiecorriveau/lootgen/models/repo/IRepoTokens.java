package joel.lajoiecorriveau.lootgen.models.repo;

import joel.lajoiecorriveau.lootgen.models.Item;
import joel.lajoiecorriveau.lootgen.models.Token;
import joel.lajoiecorriveau.lootgen.models.User;
import joel.lajoiecorriveau.lootgen.models.exception.NoToken;
import joel.lajoiecorriveau.lootgen.models.exception.NoUserExist;

import java.util.List;

/**
 * Created by 1387434 on 2015-11-17.
 */
public interface IRepoTokens {
    long save(Token o);

    void saveMany(Iterable<Token> list);

    void saveMany(Token... list);

    Token getById(Long p);

    List<Token> getAll();

    void deleteOne(Long o);

    void deleteOne(Token o);

    void deleteAll();

    Token getByUserID(Long p) throws NoToken;

    Token getByUserEmail(String pEmail) throws NoToken, NoUserExist;

    void deleteByUUID(String pUUID) throws NoToken;

    Token getByUUID(String pUUID) throws NoToken;
}
