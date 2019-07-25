package joel.lajoiecorriveau.lootgen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import joel.lajoiecorriveau.lootgen.models.*;
import joel.lajoiecorriveau.lootgen.models.exception.*;
import joel.lajoiecorriveau.lootgen.models.repo.RepoItemPools;
import joel.lajoiecorriveau.lootgen.models.repo.RepoItems;
import joel.lajoiecorriveau.lootgen.models.repo.RepoTokens;
import joel.lajoiecorriveau.lootgen.models.repo.RepoUsers;

/**
 * Created by Joel on 9/13/2015.
 */
public class Service implements IService {

    RepoItems repoItems;
    RepoItemPools repoItemPools;
    RepoUsers repoUsers;
    RepoTokens repoTokens;

    private static Service serviceUnique;

    private Service()
    {
        this.repoUsers = RepoUsers.get();
        this.repoItems = RepoItems.get();
        this.repoItemPools = RepoItemPools.get();
        this.repoTokens = RepoTokens.get();
    }

    public static synchronized Service get()
    {
        if(serviceUnique == null)
            serviceUnique = new Service();

        return serviceUnique;
    }

    /**
     * Permet la création d'un utilisateur dans la base de données de l'application.
     *
     * @param pUsername        Le nom d'utilisateur
     * @param pPassword        Le mot de passe pour cet utilisateur
     * @param pConfirmPassword La confirmation du mot de passe pour cet utilisateur
     * @param pEmail           Le courriel de cet utilisateur
     * @return Le nouvel utilisateur
     * @throws BadEmail            Si le courriel est d'un format invalide
     * @throws BadPassword         Si le mot de passe contient des caractères invalides
     * @throws BadLogin            Si le nom d'utilisateur contient des caractères invalides
     * @throws UserAlreadyExists   Si un utilisateur avec le même nom que celui qu'on tente de créer existe,
     *                                                                 ou si le courriel est identique à un utilisateur existant.
     * @throws InvalidConfirmation Si la confirmation de mot de passe diffère du mot de passe.
     */
    @Override
    public User CreateUser(String pUsername, String pPassword, String pConfirmPassword, String pEmail)
            throws BadEmail, BadPassword, BadLogin, UserAlreadyExists, InvalidConfirmation {
        // Pour l'instant, cette implémentation ne permet pas d'espace dans les mots de passe et les Login
        if(!pUsername.matches(("^[^\\s]+$")))
        {
            throw new BadLogin("Le login ne peut contenir d'espaces!");
        }

        if(!pPassword.matches(("^[^\\s]+$")))
        {
            throw new BadPassword("Le mot de passe ne peut pas contenir d'espaces!");
        }

        Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");

        if(!ptr.matcher(pEmail).matches())
        {
            throw new BadEmail("Ceci n'est pas un courriel valide.");
        }

        if(!pPassword.equals(pConfirmPassword))
        {
            throw new InvalidConfirmation("Votre confirmation de mot de passe differe de votre mot de passe.");
        }

        if(repoUsers.UserNameTaken(pUsername) || repoUsers.EmailTaken(pEmail))
        {
            throw new UserAlreadyExists("Un utilisateur avec le nom " + pUsername + " ou le courriel " + pEmail + " existe déjà.");
        }

        User utilisateur = new User(pUsername, pPassword, pEmail);

        repoUsers.save(utilisateur);

        return utilisateur;
    }

    /**
     * Authentifie un utilisateur dans l'application en utilisant son mot de passe et son login.
     *
     * @param pUsername Le nom (login) de l'utilisateur
     * @param pPassword Le mot de passe pour cet utilisateur
     * @return L'utilisateur authentifié
     * @throws InvalidLoginAttempt Si le login ou le mot de passe est invalide
     * @throws NoUserExist         Si il n'y a pas d'utilisateur dans le système avec ce nom d'utilisateur (login)
     */
    @Override
    public User Authentify(String pUsername, String pPassword) throws InvalidLoginAttempt, NoUserExist {
        User utilisateur = repoUsers.authentify(pUsername, pPassword);
        return utilisateur;
    }

    /**
     * Sauvegarde un utilisateur dans le système
     *
     * @param u L'utilisateur à sauvegarder
     * @return L'ID de l'utilisateur sauvegardé
     */
    @Override
    public Long SaveUser(User u) throws BadEmail, BadLogin, BadPassword{
        if(!u.getLogin().matches(("^[^\\s]+$")))
        {
            throw new BadLogin("Le login ne peut contenir d'espaces!");
        }

        if(!u.getPassword().matches(("^[^\\s]+$")))
        {
            throw new BadPassword("Le mot de passe ne peut pas contenir d'espaces!");
        }

        Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");

        if(!ptr.matcher(u.getEmail()).matches())
        {
            throw new BadEmail("Ceci n'est pas un courriel valide.");
        }

//        if(repoUsers.UserNameTaken(u.getLogin()) || repoUsers.EmailTaken(u.getEmail()))
//        {
//            throw new UserAlreadyExists("Un utilisateur avec le nom " + u.getLogin() + " ou le courriel " + u.getEmail() + " existe déjà.");
//        }

        return repoUsers.save(u);
    }

    /**
     * Sauvegarde plusieurs utilisateurs (sous forme de liste) dans le système
     *
     * @param list Une liste des utilisateurs à sauvegarder
     */
    @Override
    public void SaveUsers(Iterable<User> list)throws UserAlreadyExists, BadEmail, BadLogin, BadPassword {
        for(User u : list)
        {
            this.SaveUser(u);
        }
    }

    /**
     * Sauvegarde plusieurs utilisateurs dans le système
     *
     * @param list Les utilisateurs à sauvegarder
     */
    @Override
    public void SaveManyUsers(User... list) throws UserAlreadyExists, BadEmail, BadLogin, BadPassword {
        for(User u : list)
        {
            this.SaveUser(u);
        }
    }

    /**
     * Obtient un utilisateur à l'aide de son ID.
     *
     * @param p Le ID de l'utilisateur
     * @return L'utilisateur qui correspond à ce ID
     * @throws NoUserExist Aucun utilisateur avec ce ID existe
     */
    @Override
    public User GetUserByID(Long p) throws NoUserExist {
        User util = repoUsers.getById(p);

        if(util == null)
            throw new NoUserExist("Aucun utilisateur avec ce ID existe.");

        return util;
    }

    /**
     * Obtient tous les utilisateurs du système sous forme de liste
     *
     * @return Une liste de tous les utilisateurs
     */
    @Override
    public List<User> GetAllUsers() {
        return repoUsers.getAll();
    }

    /**
     * Supprime un utilisateur par son ID
     *
     * @param o L'ID de l'utilisateur à supprimer
     */
    @Override
    public void DeleteUserByID(Long o) {
        repoUsers.deleteOne(o);
    }

    /**
     * Supprime un utilisateur du système
     *
     * @param o L'utilisateur à supprimer
     */
    @Override
    public void DeleteUser(User o) {
        repoUsers.deleteOne(o);
    }

    /**
     * Supprime tous les utilisateurs du système
     */
    @Override
    public void DeleteAllUsers() {
        repoUsers.deleteAll();
    }

    /**
     * Sauvegarde un Item dans le système
     *
     * @param o L'item à sauvegarder
     * @return Le ID de l'item sauvegardé
     */
    @Override
    public long SaveItem(Item o) throws BadItemName{
        if(o.getName().length() == 0)
            throw new BadItemName("Un Item doit avoir un nom.");

        return repoItems.save(o);
    }

    /**
     * Sauvegarde plusieurs items (sous forme de liste) dans le système
     *
     * @param list Une liste des items à sauvegarder
     */
    @Override
    public void SaveManyItems(Iterable<Item> list) throws BadItemName{
        for(Item i : list)
        {
            this.SaveItem(i);
        }
    }

    /**
     * Sauvegarde plusieurs items dans le système
     *
     * @param list Les items à sauvegarder
     */
    @Override
    public void SaveManyItems(Item... list) throws BadItemName{
        for(Item i : list)
        {
            this.SaveItem(i);
        }
    }

    /**
     * Obtient un Item par son ID
     *
     * @param p Le ID de l'item
     * @return L'Item qui correspond à ce ID
     * @throws ItemNotFound Si l'ID fournit ne correspond
     *                                                          à aucun item du système
     */
    @Override
    public Item GetItemByID(Long p) throws ItemNotFound {
        Item i = repoItems.getById(p);

        if(i == null)
            throw new ItemNotFound("L'objet avec le ID spécifié ne peut être trouvé.");

        return i;
    }

    /**
     * Obtient les items d'un Utilisateur
     *
     * @param o L'utilisateur en question
     * @return Les items de l'utilisateur, sous forme de liste
     */
    @Override
    public List<Item> GetItemsByUser(User o) {
        return repoItems.getItemsByUser(o);
    }

    /**
     * Obtient tous les items du système
     *
     * @return Tous les items, sous forme de liste
     */
    @Override
    public List<Item> GetAllItems() {
        return repoItems.getAll();
    }

    /**
     * Supprime un Item par son ID
     *
     * @param o l'ID de l'item à supprimer
     */
    @Override
    public void DeleteItemByID(Long o) {
        repoItems.deleteOne(o);
    }

    /**
     * Supprime un item du système
     *
     * @param o L'item à supprimer
     */
    @Override
    public void DeleteItem(Item o) {
        repoItems.deleteOne(o);
    }

    /**
     * Supprime tous les items du système
     */
    @Override
    public void DeleteAllItems() {
        repoItems.deleteAll();
    }

    /**
     * Sauvegarde une ItemPool dans le système
     *
     * @param o L'ItemPool à sauvegarder
     * @return Le ID de la nouvelle Item Pool
     */
    @Override
    public long SaveItemPools(ItemPool o) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool {
        if(o.getName().length() == 0)
            throw new BadItemPoolName("Une Item Pool doit avoir un nom.");

        if(o.getItems().size() == 0)
            throw new EmptyItemPool("Une Item Pool doit avoir au moins un item.");

        Double cpt = 0.0;
        for(Item_Chance item : o.getItems())
        {
            cpt += item.dropPercent;
        }

        if(cpt != 100.0)
        {
            throw new BadSumOfPoolsChances("La somme des dropChance des items doit etre de 100.");
        }

        return repoItemPools.save(o);
    }

    /**
     * Sauvegarde une liste de ItemPool dans le système
     *
     * @param list La liste de ItemPool à sauvegarder
     */
    @Override
    public void SaveManyItemPool(Iterable<ItemPool> list) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool{
        for(ItemPool p : list)
        {
            this.SaveItemPools(p);
        }
    }

    /**
     * Sauvegarde plusieurs ItemPool dans le système
     *
     * @param list Les ItemPool à sauvegarder
     */
    @Override
    public void SaveManyItemPools(ItemPool... list) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool{
        for(ItemPool p : list)
        {
            this.SaveItemPools(p);
        }
    }

    /**
     * Obtient une ItemPool par son ID
     *
     * @param p Le ID de l'item pool recherché
     * @return L'ItemPool qui correspond au ID
     * @throws ItemPoolNotFound Si aucun ItemPool existe avec ce ID
     */
    @Override
    public ItemPool GetItemPoolByID(Long p) throws ItemPoolNotFound {
        ItemPool pool = repoItemPools.getById(p);

        if(pool == null)
        {
            throw new ItemPoolNotFound("La Pool avec le ID spécifié ne peut être trouvé.");
        }

        return pool;
    }

    /**
     * Obtient les ItemPool d'un utilisateur
     *
     * @param o L'utilisateur en question
     * @return Les ItemPool de cet utilisateur, sous forme de liste
     */
    @Override
    public List<ItemPool> GetItemPoolsByUser(User o) {
        return repoItemPools.getItemPoolsByUser(o);
    }

    /**
     * Obtient tous les ItemPool du système
     *
     * @return Une liste de tous les ItemPool
     */
    @Override
    public List<ItemPool> GetAllItemPools() {
        return repoItemPools.getAll();
    }

    /**
     * Supprime une ItemPool du système avec son ID
     *
     * @param o Le ID de l'ItemPool à supprimer
     */
    @Override
    public void DeleteItemPoolByID(Long o) {
        repoItemPools.deleteOne(o);
    }

    /**
     * Supprime une ItemPool du système
     *
     * @param o L'ItemPool à supprimer
     */
    @Override
    public void DeleteItemPool(ItemPool o) {
        repoItemPools.deleteOne(o);
    }

    /**
     * Transforme une string en rareté
     * @param rar La rareté sous forme de string
     */
    @Override
    public Rarity ParseRarity(String rar) throws BadRarity
    {
        for(Rarity rarity : Rarity.values())
        {
            String rarInString = rarity.toString();

            if(rarInString.equals(rar))
            {
                return rarity;
            }
        }

        throw new BadRarity("The rarity could not be parsed. Does not exists.");
    }

    /**
     * Supprime tous les ItemPool du système
     */
    @Override
    public void DeleteAllItemPools() {
        repoItemPools.deleteAll();
    }

    /**
     * Tire au sort un nombre d'objets, venant des Item Pools choisit.
     *
     * @param pPool1     La première Item Pool à utiliser pour le tirage au sort.
     * @param pPool2     La deuxième Item Pool à utiliser pour le tirage au sort. Si aucune, mettre Null.
     * @param pPool3     La troisième Item Pool à utiliser pour le tirage au sort. Si aucune, mettre Null.
     * @param pPool1Chance     La chance de tiré un Item de la première Pool.
     * @param pPool2Chance     La chance de tiré un Item de la deuxième Pool.
     * @param pPool3Chance     La chance de tiré un Item de la troisième Pool.
     * @param pPool1EQ     Si on doit égaliser ou non la première Item Pool.
     * @param pPool2EQ     Si on doit égaliser ou non la deuxième Item Pool.
     * @param pPool3EQ     Si on doit égaliser ou non la troisième Item Pool.
     * @param pNbrOfItems   Le nombre d'objets à tiré au sort.
     * @param pInstant      Si le loot doit être généré instantanément (true) ou avec des animations (false).
     * @param pUser L'utilisateur courant.
     * @return Une liste des objets tirés au hasard.
     */
    @Override
    public List<Item> GenerateLoot(ItemPool pPool1, ItemPool pPool2, ItemPool pPool3, Double pPool1Chance, Double pPool2Chance, Double pPool3Chance,
                                   Boolean pPool1EQ, Boolean pPool2EQ, Boolean pPool3EQ, int pNbrOfItems, boolean pInstant, User pUser)
    throws BadItemPoolChances, BadItemPoolEqualizer{
        // Checking parameters...

        double totalDrop = 0;

        totalDrop += pPool1Chance + pPool2Chance + pPool3Chance;

        if(totalDrop != 100)
            throw new BadItemPoolChances("Les chances de vos Item Pools ne sont pas égales à 100 une fois cummulés");

        // Formatting chances...

        double chancePool1 = pPool1Chance;
        double chancePool2 = chancePool1 + pPool2Chance;
        double chancePool3 = chancePool2 + pPool3Chance;

        Random rand = new Random();

        if (pPool1Chance == 0)
            chancePool1 = 0;
        if (pPool2Chance == 0)
            chancePool2 = 0;
        if (pPool3Chance == 0)
            chancePool3 = 0;

        List<Item> lstLootItems = new ArrayList<Item>();

        // For each item... (in the Number of items chosen by user)
        for (int itemCount = 0; itemCount < pNbrOfItems; itemCount++)
        {
            Long poolIndex;

            // Choosing an item pool. Each pool has its chance.
            int poolRoll = rand.nextInt(100) + 1;

            ItemPool poolDrawn;

            int numberPool = -1;

            if (poolRoll <= chancePool1) {
                poolDrawn = pPool1;
                numberPool = 1;
            } else if (poolRoll > chancePool1 && poolRoll <= chancePool2) {
                poolDrawn = pPool2;
                numberPool = 2;
            } else {
                poolDrawn = pPool3;
                numberPool = 3;
            }


            // Formatting item drop chances...
            List<Double> dropTable = new ArrayList<Double>();

            Double precedentDropChance = 0D;

            boolean equalizeDropChances = false;

            if (numberPool == 1) {
                if (pPool1EQ) {
                    equalizeDropChances = true;
                }
            }
            if (numberPool == 2) {
                if (pPool2EQ) {
                    equalizeDropChances = true;
                }
            }
            if (numberPool == 3) {
                if (pPool3EQ) {
                    equalizeDropChances = true;
                }
            }

            if (equalizeDropChances) {
                for (Item_Chance item : poolDrawn.getItems()) {
                    dropTable.add(1D + precedentDropChance);
                    precedentDropChance += 1;
                }
            } else {
                for (Item_Chance item : poolDrawn.getItems()) {
                    dropTable.add(item.dropPercent + precedentDropChance);
                    precedentDropChance += item.dropPercent;
                }
            }

            int itemRoll;

            if (equalizeDropChances) {
                itemRoll = rand.nextInt(poolDrawn.getItems().size()) + 1;
            } else {
                itemRoll = rand.nextInt(100) + 1;
            }

            int itemIndexDrawn = 0;

            for (int i = 0; i < dropTable.size(); i++) {
                if (itemRoll <= dropTable.get(i)) {
                    itemIndexDrawn = i;
                    break;
                }
            }

            Item itemDrawn = repoItems.getById(poolDrawn.getItems().get(itemIndexDrawn).ItemIndex);

            lstLootItems.add(itemDrawn);
        }

        return lstLootItems;
    }

    @Override
    public Token getTokenByUserId(Long o) throws NoToken
    {
        return repoTokens.getByUserID(o);
    }

    @Override
    public Token getTokenByUserEmail(String o) throws NoToken, NoUserExist
    {
        return repoTokens.getByUserEmail(o);
    }

    @Override
    public Token signin(String pUsername, String pPassword) throws InvalidLoginAttempt, NoUserExist
    {
        User u = Authentify(pUsername, pPassword);

        Token t;
        try {
            t = getTokenByUserId(u.getID());
            repoTokens.deleteOne(t);
        } catch (NoToken e) {}
        // produce a new one
        Token newT = Token.forUser(u, 7);
        repoTokens.save(newT);
        return newT;
    }

    @Override
    public Token getTokenByUUID(String uuid) throws NoToken
    {
        return repoTokens.getByUUID(uuid);
    }

    @Override
    public void signout(String tokenID) throws NoToken
    {
        repoTokens.deleteByUUID(tokenID);
    }
}
