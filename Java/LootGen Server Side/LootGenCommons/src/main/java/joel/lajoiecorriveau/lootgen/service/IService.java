package joel.lajoiecorriveau.lootgen.service;

import java.util.List;

import joel.lajoiecorriveau.lootgen.models.*;
import joel.lajoiecorriveau.lootgen.models.exception.*;

/**
 * Created by Joel on 9/8/2015.
 */
public interface IService {
    /**
     * Permet la création d'un utilisateur dans la base de données de l'application.
     *
     * @param  pUsername  Le nom d'utilisateur
     * @param  pPassword Le mot de passe pour cet utilisateur
     * @param  pConfirmPassword La confirmation du mot de passe pour cet utilisateur
     * @param  pEmail Le courriel de cet utilisateur
     * @return      Le nouvel utilisateur
     * @throws      BadEmail    Si le courriel est d'un format invalide
     * @throws      BadPassword Si le mot de passe contient des caractères invalides
     * @throws BadLogin    Si le nom d'utilisateur contient des caractères invalides
     * @throws UserAlreadyExists   Si un utilisateur avec le même nom que celui qu'on tente de créer existe,
     *                                                                      ou si le courriel est identique à un utilisateur existant.
     * @throws InvalidConfirmation  Si la confirmation de mot de passe diffère du mot de passe.
     */
    public User CreateUser(String pUsername, String pPassword, String pConfirmPassword, String pEmail) throws BadEmail, BadPassword, BadLogin, UserAlreadyExists, InvalidConfirmation;

    /**
     * Authentifie un utilisateur dans l'application en utilisant son mot de passe et son login.
     * @param pUsername Le nom (login) de l'utilisateur
     * @param pPassword Le mot de passe pour cet utilisateur
     * @return L'utilisateur authentifié
     * @throws InvalidLoginAttempt Si le login ou le mot de passe est invalide
     * @throws NoUserExist Si il n'y a pas d'utilisateur dans le système avec ce nom d'utilisateur (login)
     */
    public User Authentify(String pUsername, String pPassword) throws InvalidLoginAttempt, NoUserExist;

    /**
     * Sauvegarde un utilisateur dans le système
     * @param u L'utilisateur à sauvegarder
     * @return L'ID de l'utilisateur sauvegardé
     */
    public Long SaveUser(User u) throws BadEmail, BadLogin, BadPassword;

    /**
     * Sauvegarde plusieurs utilisateurs (sous forme de liste) dans le système
     * @param list Une liste des utilisateurs à sauvegarder
     */
    public void SaveUsers(Iterable<User> list) throws UserAlreadyExists, BadEmail, BadLogin, BadPassword;

    /**
     * Sauvegarde plusieurs utilisateurs dans le système
     * @param list Les utilisateurs à sauvegarder
     */
    public void SaveManyUsers(User... list) throws UserAlreadyExists, BadEmail, BadLogin, BadPassword;

    /**
     * Obtient un utilisateur à l'aide de son ID.
     * @param p Le ID de l'utilisateur
     * @return  L'utilisateur qui correspond à ce ID
     * @throws NoUserExist Aucun utilisateur avec ce ID existe
     */
    public User GetUserByID(Long p) throws NoUserExist;

    /**
     * Obtient tous les utilisateurs du système sous forme de liste
     * @return Une liste de tous les utilisateurs
     */
    public List<User> GetAllUsers();

    /**
     * Supprime un utilisateur par son ID
     * @param o L'ID de l'utilisateur à supprimer
     */
    public void DeleteUserByID(Long o);

    /**
     * Supprime un utilisateur du système
     * @param o L'utilisateur à supprimer
     */
    public void DeleteUser(User o);

    /**
     * Supprime tous les utilisateurs du système
     */
    public void DeleteAllUsers();

    /**
     * Sauvegarde un Item dans le système
     * @param o L'item à sauvegarder
     * @return Le ID de l'item sauvegardé
     */
    public long SaveItem(Item o) throws BadItemName;

    /**
     * Sauvegarde plusieurs items (sous forme de liste) dans le système
     * @param list Une liste des items à sauvegarder
     */
    public void SaveManyItems(Iterable<Item> list) throws BadItemName;

    /**
     * Sauvegarde plusieurs items dans le système
     * @param list Les items à sauvegarder
     */
    public void SaveManyItems(Item... list) throws BadItemName;

    /**
     * Obtient un Item par son ID
     * @param p Le ID de l'item
     * @return L'Item qui correspond à ce ID
     * @throws ItemNotFound Si l'ID fournit ne correspond
     *                                                          à aucun item du système
     */
    public Item GetItemByID(Long p) throws ItemNotFound;

    /**
     * Obtient les items d'un Utilisateur
     * @param o L'utilisateur en question
     * @return Les items de l'utilisateur, sous forme de liste
     */
    public List<Item> GetItemsByUser(User o);

    /**
     * Obtient tous les items du système
     * @return Tous les items, sous forme de liste
     */
    public List<Item> GetAllItems();

    /**
     * Supprime un Item par son ID
     * @param o l'ID de l'item à supprimer
     */
    public void DeleteItemByID(Long o);

    /**
     * Supprime un item du système
     * @param o L'item à supprimer
     */
    public void DeleteItem(Item o);

    /**
     * Supprime tous les items du système
     */
    public void DeleteAllItems();

    /**
     * Sauvegarde une ItemPool dans le système
     * @param o L'ItemPool à sauvegarder
     * @return Le ID de la nouvelle Item Pool
     */
    public long SaveItemPools(ItemPool o) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool;

    /**
     * Sauvegarde une liste de ItemPool dans le système
     * @param list La liste de ItemPool à sauvegarder
     */
    public void SaveManyItemPool(Iterable<ItemPool> list)throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool;

    /**
     * Sauvegarde plusieurs ItemPool dans le système
     * @param list Les ItemPool à sauvegarder
     */
    public void SaveManyItemPools(ItemPool... list)throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool;

    /**
     * Obtient une ItemPool par son ID
     * @param p Le ID de l'item pool recherché
     * @return L'ItemPool qui correspond au ID
     * @throws ItemPoolNotFound Si aucun ItemPool existe avec ce ID
     */
    public ItemPool GetItemPoolByID(Long p) throws ItemPoolNotFound;

    /**
     * Obtient les ItemPool d'un utilisateur
     * @param o L'utilisateur en question
     * @return Les ItemPool de cet utilisateur, sous forme de liste
     */
    public List<ItemPool> GetItemPoolsByUser(User o);

    /**
     * Obtient tous les ItemPool du système
     * @return Une liste de tous les ItemPool
     */
    public List<ItemPool> GetAllItemPools();

    /**
     * Supprime une ItemPool du système avec son ID
     * @param o Le ID de l'ItemPool à supprimer
     */
    public void DeleteItemPoolByID(Long o);

    /**
     * Supprime une ItemPool du système
     * @param o L'ItemPool à supprimer
     */
    public void DeleteItemPool(ItemPool o);

    /**
     * Transforme une string en rareté
     * @param rar La rareté sous forme de string
     */
    public Rarity ParseRarity(String rar) throws BadRarity;

    /**
     * Supprime tous les ItemPool du système
     */
    public void DeleteAllItemPools();

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
    public List<Item> GenerateLoot(ItemPool pPool1, ItemPool pPool2, ItemPool pPool3, Double pPool1Chance, Double pPool2Chance, Double pPool3Chance,
                                   Boolean pPool1EQ, Boolean pPool2EQ, Boolean pPool3EQ, int pNbrOfItems, boolean pInstant, User pUser) throws BadItemPoolChances, BadItemPoolEqualizer;

    /**
     * Obtiens l'objet Token d'un utilisateur à l'aide de son ID.
     * @param o Le Id de l'utilisateur.
     * @return Le token associé à cet utilisateur.
     */
    public Token getTokenByUserId(Long o) throws NoToken;
    /**
     * Obtiens l'objet Token d'un utilisateur à l'aide de son email.
     * @param email L'email de l'utilisateur.
     * @return Le token associé à cet utilisateur.
     */
    public Token getTokenByUserEmail(String email) throws NoUserExist, NoToken;

    public Token getTokenByUUID(String uuid) throws NoToken;

    /**
     * Authentifie un utilisateur dans l'application en utilisant son mot de passe et son login. De plus, utilise le système de Token.
     * @param pUsername Le nom (login) de l'utilisateur
     * @param pPassword Le mot de passe pour cet utilisateur
     * @return L'utilisateur authentifié
     * @throws InvalidLoginAttempt Si le login ou le mot de passe est invalide
     * @throws NoUserExist Si il n'y a pas d'utilisateur dans le système avec ce nom d'utilisateur (login)
     */
    public Token signin(String pUsername, String pPassword) throws InvalidLoginAttempt, NoUserExist;

    /**
     * Déconnecte l'utilisateur courant du système, et supprime son token.
     * @param tokenID Le ID (UUID) du token de l'utilisateur courant.
     */
    public void signout(String tokenID) throws NoToken;
}
