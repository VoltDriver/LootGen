package joel.lajoiecorriveau.lootgen;

import com.google.gson.Gson;
import joel.lajoiecorriveau.lootgen.models.*;
import joel.lajoiecorriveau.lootgen.models.exception.*;
import joel.lajoiecorriveau.lootgen.service.Service;
import joel.lajoiecorriveau.lootgen.transfer.GenLootData;
import joel.lajoiecorriveau.lootgen.transfer.SignInRequest;
import joel.lajoiecorriveau.lootgen.transfer.SignupRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;

@Path("/lootgen")
public class WebService {

    private static final String Cookie = "lootgen-id-cookie";

	private Gson gson;
    private Service service;

	public WebService() {
		gson = new Gson();
        service = Service.get();
	}

	// simple example of a straight get access
	@GET					@Path("/GetAllUsers")
    @Produces("text/json")
    public String getAllUsers(@CookieParam(Cookie) Cookie cookie) throws NoToken{

//        if(cookie == null)
//        {
//            throw new NoToken("You must be logged in.");
//        }
        System.out.println("WEB SERVICE : GET ALL USERS " + cookie);
        return gson.toJson(service.GetAllUsers());
    }

    @GET					@Path("/GetCurrentUser")
    @Produces("text/json")
    public Response getCurrentUser(@CookieParam(Cookie) Cookie cookie) throws NoToken, NoUserExist{

        if(cookie == null)
        {
            throw new NoToken("You must be logged in.");
        }

        User u = getUserByCookie(cookie);

        System.out.println("WEB SERVICE : GET CURRENT USER " + cookie);
        return Response.ok(gson.toJson(u),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/test/returnTest/{stuff}")
    public String all(@PathParam("stuff") String stuff){
        System.out.println("WEB SERVICE : TESTED " + stuff );

        if(stuff.equals("ERROR"))
        {
            throw new IllegalArgumentException("There was a super duper exception there.");
        }

        return "YOU ARE NOW THE BEST AROUND! NO ONE CAN EVER BRING YOU DOWN! " + stuff;
    }

    @POST					@Path("/signin")
    @Produces("text/json")
    public Response signin(String req) throws InvalidLoginAttempt, NoUserExist{
        SignInRequest request = gson.fromJson(req, SignInRequest.class);
        Token t = service.signin(request.username, request.password);
        System.out.println("WEB SERVICE : SIGNIN " + t.id );
        NewCookie cookiee = new NewCookie(Cookie, t.id, "/", "", "id token", 604800, false); // setting the cookie
        return Response.ok(gson.toJson(t),MediaType.APPLICATION_JSON)
                .cookie(cookiee)
                .build();
    }

    @POST					@Path("/signup")
    @Produces("text/json")
    public Response signup(String signup) throws BadPassword, BadEmail, UserAlreadyExists,
                                            BadLogin, InvalidConfirmation {
        // Get the person that is candidate
        SignupRequest request = gson.fromJson(signup, SignupRequest.class);
//        try {
            User u = service.CreateUser(request.username, request.password, request.confirmpassword, request.email);
            return Response.ok(gson.toJson(u.getID()),MediaType.APPLICATION_JSON).build();
//        } catch (Exception e) {
//            // construction d'une reponse avec un code d'erreur HTTP
//            return Response.status(Response.Status.BAD_REQUEST).entity(e.getClass().getSimpleName() + ": Please Try Again.").build();
//        }
    }

    @GET					@Path("/signout")
    @Produces("text/json")
    public Response signout(@CookieParam(Cookie) Cookie cookie, @PathParam("token") String token) throws NoToken{
        if (cookie == null) return Response.ok("No cookie", MediaType.TEXT_PLAIN).build();
        service.signout(cookie.getValue());

        // cree un cookie ave une duree de vie de 0 secondes qui sera donc le remplacement de l'actuel
        // puis supprime
        NewCookie toDelete = new NewCookie(Cookie, null, "/", null, null, 0, true);
        Response res = Response.ok("DONE",MediaType.TEXT_PLAIN)
                .cookie(toDelete)
                .build();
        return res;
    }

    @POST					@Path("/SaveUser")
    @Produces("text/json")
    public Response saveUser(String user) throws BadPassword, BadEmail, UserAlreadyExists,
            BadLogin, InvalidConfirmation {
        // Get the person that is candidate
        User u = gson.fromJson(user, User.class);
        service.SaveUser(u);
        return Response.ok(gson.toJson(u.getID()),MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/SaveUsers")
    public Response saveUsers(String users) throws BadPassword, BadEmail, UserAlreadyExists,
            BadLogin {
        // Get the person that is candidate
        List<User> result =
                gson.fromJson(users, new TypeToken<List<User>>(){}.getType());
        service.SaveUsers(result);
        return Response.ok(gson.toJson(result),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/GetUserById/{id}")
    @Produces("text/json")
    public Response getUserById(@PathParam("id") String id) throws NoUserExist{
        // Get the person that is candidate
        Long userId = Long.parseLong(id);
        User u = service.GetUserByID(userId);
        return Response.ok(gson.toJson(u),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/DeleteUserById/{id}")
    public Response deleteUserById(@PathParam("id") String id) throws NoUserExist{
        // Get the person that is candidate
        Long userId = Long.parseLong(id);
        service.DeleteUserByID(userId);
        return Response.ok("Successful",MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/DeleteUser")
    public Response deleteUser(String user) throws NoUserExist{
        // Get the person that is candidate
        User u = gson.fromJson(user, User.class);
        service.DeleteUser(u);
        return Response.ok("Successful",MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/DeleteAllUsers")
    public Response deleteAllUsers() {
        // Get the person that is candidate
        service.DeleteAllUsers();
        return Response.ok("Successful",MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/SaveItem")
    @Produces("text/json")
    public Response saveItem(@CookieParam(Cookie) Cookie cookie, String item) throws BadItemName, NoToken, NoUserExist,
                    BadLogin, BadPassword, BadEmail{
        if(cookie == null)
            throw new NoToken("You must be signed in to do this.");

        User u = getUserByCookie(cookie);

        // Get the person that is candidate
        Item i = gson.fromJson(item, Item.class);
        Long o = service.SaveItem(i);

        if(!u.getCustomItems().contains(o))
        {
            u.getCustomItems().add(o);
        }

        service.SaveUser(u);

        return Response.ok(gson.toJson(i.getID()),MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/SaveManyItems")
    public Response saveManyItems(@CookieParam(Cookie) Cookie cookie, String items) throws BadItemName, NoToken, NoUserExist,
                            BadLogin, BadPassword, BadEmail{
        if(cookie == null)
            throw new NoToken("You must be signed in to do this.");

        // Get the person that is candidate
        List<Item> result =
                gson.fromJson(items, new TypeToken<List<Item>>(){}.getType());

        User u = getUserByCookie(cookie);

        service.SaveManyItems(result);

        for(Item i : result)
        {
            if(!u.getCustomItems().contains(i.getID()))
            {
                u.getCustomItems().add(i.getID());
            }
        }

        service.SaveUser(u);

        return Response.ok(gson.toJson(result),MediaType.APPLICATION_JSON).build();
    }


    @GET					@Path("/GetItemById/{id}")
    @Produces("text/json")
    public Response getItemById(@PathParam("id") String id) throws ItemNotFound{
        Long itemid = Long.parseLong(id);
        Item i = service.GetItemByID(itemid);
        return Response.ok(gson.toJson(i),MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/GetItemsByUser")
    @Produces("text/json")
    public Response getItemsByUser(@CookieParam(Cookie) Cookie cookie) throws NoToken, NoUserExist {
        if(cookie == null)
            throw new NoToken("You need to be signed in to access this.");

        //User u = gson.fromJson(content, User.class);
        User u = getUserByCookie(cookie);

        List<Item> items = service.GetItemsByUser(u);
        return Response.ok(gson.toJson(items),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/GetAllItems")
    @Produces("text/json")
    public Response getAllItems() {
        List<Item> i = service.GetAllItems();
        return Response.ok(gson.toJson(i),MediaType.APPLICATION_JSON).build();
    }
    @GET					@Path("/DeleteItemById/{id}")
    public Response deleteItemById(@PathParam("id") String id){
        Long itemid = Long.parseLong(id);
        service.DeleteItemByID(itemid);
        return Response.ok("Succesful",MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/DeleteItem")
    @Produces("text/json")
    public Response deleteItem(String item){
        Item i = gson.fromJson(item, Item.class);
        service.DeleteItem(i);
        return Response.ok("Succesful",MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/DeleteAllItems")
    public Response deleteAllItems() {
        service.DeleteAllItems();
        return Response.ok("Successfull",MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/SaveItemPools")
    @Produces("text/json")
    public Response saveItemPools(@CookieParam(Cookie) Cookie cookie, String pool) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool,
    NoToken, NoUserExist, BadEmail, BadLogin, BadPassword{
        if(cookie == null)
            throw new NoToken("You must be signed in to do this.");

        ItemPool i = gson.fromJson(pool, ItemPool.class);

        User u = getUserByCookie(cookie);

        Long o = service.SaveItemPools(i);


        if(!u.getCustomPools().contains(o))
        {
            u.getCustomPools().add(o);
        }

        service.SaveUser(u);

        return Response.ok(gson.toJson(o),MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/SaveManyItemPools")
    public Response saveManyItemPools(@CookieParam(Cookie) Cookie cookie, String pools) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool,
    BadLogin, BadEmail, BadPassword, NoToken, NoUserExist
    {
        if(cookie == null)
            throw new NoToken("You must be signed in to do this.");

        User u = getUserByCookie(cookie);

        List<ItemPool> result =
                gson.fromJson(pools, new TypeToken<List<ItemPool>>(){}.getType());
        service.SaveManyItemPool(result);

        for(ItemPool i : result)
        {
            if(!u.getCustomPools().contains(i.getID()))
            {
                u.getCustomPools().add(i.getID());
            }
        }

        service.SaveUser(u);

        return Response.ok(gson.toJson(result),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/GetItemPoolsById/{id}")
    @Produces("text/json")
    public Response getItemPoolById(@PathParam("id") String id) throws ItemPoolNotFound{
        // Get the person that is candidate
        Long poolid = Long.parseLong(id);
        ItemPool u = service.GetItemPoolByID(poolid);
        return Response.ok(gson.toJson(u),MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/GetItemPoolsByUser")
    @Produces("text/json")
    public Response getItemPoolsByUser(@CookieParam(Cookie) Cookie cookie) throws NoUserExist, NoToken{
//        User u = gson.fromJson(user, User.class);
        if(cookie == null)
            throw new NoToken("You must be signed in to do this.");

        User u = getUserByCookie(cookie);
        List<ItemPool> list = service.GetItemPoolsByUser(u);
        return Response.ok(gson.toJson(list),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/GetAllItemPools")
    @Produces("text/json")
    public Response getAllItemPools(){
        List<ItemPool> list = service.GetAllItemPools();
        return Response.ok(gson.toJson(list),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/DeleteItemPoolById/{id}")
    public Response deleteItemPoolById(@PathParam("id") String id){
        // Get the person that is candidate
        Long poolid = Long.parseLong(id);
        service.DeleteItemPoolByID(poolid);
        return Response.ok("Succesful",MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/DeleteItemPool")
    public Response deleteItemPool(String pool){
        // Get the person that is candidate
        ItemPool p = gson.fromJson(pool, ItemPool.class);
        service.DeleteItemPool(p);
        return Response.ok("Succesful",MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/DeleteAllItemPools")
    public Response deleteAllItemPools() {
        service.DeleteAllItemPools();
        return Response.ok("Successfull",MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/ParseRarity/{rar}")
    @Produces("text/json")
    public Response parseRarity(@PathParam("rar") String rar) throws BadRarity{
        // Get the person that is candidate
        Rarity r = service.ParseRarity(rar);
        return Response.ok(gson.toJson(r),MediaType.APPLICATION_JSON).build();
    }

    @POST					@Path("/GenerateLoot")
    @Produces("text/json")
    public Response generateLoot(String genlootdata) throws BadItemPoolChances, BadItemPoolEqualizer
    {
        GenLootData data = gson.fromJson(genlootdata, GenLootData.class);
        List<Item> loot = service.GenerateLoot(data.pool1, data.pool2, data.pool3,
                data.pool1Chance, data.pool2Chance, data.pool3Chance,
                data.pool1EQ, data.pool2EQ, data.pool3EQ,
                data.nbrOfItems, data.instant, data.user);
        return Response.ok(gson.toJson(loot),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/GetTokenByUserId/{id}")
    @Produces("text/json")
    public Response getTokenByUserId(@PathParam("id") String id) throws NoToken{
        Long p = Long.parseLong(id);
        Token t = service.getTokenByUserId(p);
        return Response.ok(gson.toJson(t),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/GetTokenByUserEmail/{email}")
    @Produces("text/json")
    public Response getTokenByUserEmail(@PathParam("email") String email) throws NoToken, NoUserExist{
        Token t = service.getTokenByUserEmail(email);
        return Response.ok(gson.toJson(t),MediaType.APPLICATION_JSON).build();
    }

    @GET                    @Path("/GetTokenByUUID/{id}")
    public Response getTokenByUUID(@PathParam("id") String id) throws NoToken
    {
        Token t = service.getTokenByUUID(id);
        return Response.ok(gson.toJson(t),MediaType.APPLICATION_JSON).build();
    }

    @GET					@Path("/test/initTestData")
    public String initTestData() throws BadPassword, BadEmail, UserAlreadyExists,
            BadLogin, InvalidConfirmation, BadItemName, BadItemPoolChances,
            BadItemPoolEqualizer, BadItemPoolName, BadSumOfPoolsChances,
            EmptyItemPool{
        System.out.println("WEB SERVICE : TEST DATA INITIALIZE");
        service.DeleteAllUsers();
        service.DeleteAllItems();
        service.DeleteAllItemPools();

        service.CreateUser("Derek", "pass", "pass", "derek@derek.com");
        service.CreateUser("Bob", "pass", "pass", "bob@bob.com");

        Item i1 = new Item("Sword", Rarity.A, "Weapon");
        Item i2 = new Item("Hammer", Rarity.B, "Weapon");
        Item i3 = new Item("Fist", Rarity.C, "Weapon");
        Item i4 = new Item("Club", Rarity.D, "Weapon");
        Item i5 = new Item("Mace", Rarity.E, "Weapon");

        service.SaveManyItems(i1,i2,i3,i4,i5);

        Item_Chance c1 = new Item_Chance(i1.getID(), 25);
        Item_Chance c2 = new Item_Chance(i2.getID(), 25);
        Item_Chance c3 = new Item_Chance(i3.getID(), 10);
        Item_Chance c4 = new Item_Chance(i4.getID(), 5);
        Item_Chance c5 = new Item_Chance(i5.getID(), 35);

        List<Item_Chance> lst = new ArrayList<Item_Chance>();
        lst.add(c1);
        lst.add(c2);
        lst.add(c3);
        lst.add(c4);
        lst.add(c5);

        ItemPool pool = new ItemPool("TestingPool", lst);

        service.SaveItemPools(pool);

        User test = null;

        for(User u : service.GetAllUsers())
        {
            if(u.getEmail().equals("derek@derek.com"))
            {
                u.getCustomItems().add(i1.getID());
                u.getCustomItems().add(i2.getID());
                u.getCustomItems().add(i3.getID());
                u.getCustomItems().add(i4.getID());
                u.getCustomItems().add(i5.getID());

                u.getCustomPools().add(pool.getID());
            }
        }

        return "Fake data created successfully.";
    }

    private User getUserByCookie(Cookie pCookie) throws NoToken, NoUserExist
    {
        Token t = service.getTokenByUUID(pCookie.getValue());

        User u = service.GetUserByID(t.userID);

        return u;
    }
}
