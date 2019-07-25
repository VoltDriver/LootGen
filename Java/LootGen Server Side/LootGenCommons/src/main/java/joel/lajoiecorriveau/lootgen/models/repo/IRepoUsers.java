package joel.lajoiecorriveau.lootgen.models.repo;

import java.util.List;

import joel.lajoiecorriveau.lootgen.models.exception.InvalidLoginAttempt;
import joel.lajoiecorriveau.lootgen.models.exception.NoUserExist;
import joel.lajoiecorriveau.lootgen.models.User;

/**
 * Created by Joel on 9/8/2015.
 */
public interface IRepoUsers {
    long save(User o);

    void saveMany(Iterable<User> list);

    void saveMany(User... list);

    User getById(Long p);

    List<User> getAll();

    void deleteOne(Long o);

    void deleteOne(User o);

    void deleteAll();

    /**
     * Authentifie un utilisateur dans l'application en utilisant son mot de passe et son login.
     * @param pUsername Le nom (login) de l'utilisateur
     * @param pPassword Le mot de passe pour cet utilisateur
     * @return L'utilisateur authentifié
     * @throws InvalidLoginAttempt Si le login ou le mot de passe est invalide
     * @throws NoUserExist Si il n'y a pas d'utilisateur dans le système avec ce nom d'utilisateur (login)
     */
    User authentify(String pUsername, String pPassword) throws InvalidLoginAttempt, NoUserExist;

    /**
     * Vérifis si un utilisateur existe avec le nom fournis
     * @param pUsername Le nom d'utilisateur à vérifier
     * @return True si le nom est déjà pris, false autrement
     */
    boolean UserNameTaken(String pUsername);

    /**
     * Vérifis si l'email entré est déjà pris par un utilisateur
     * @param pEmail L'email à verifier
     * @return True si déjà pris, sinon false
     */
    boolean EmailTaken(String pEmail);
}
