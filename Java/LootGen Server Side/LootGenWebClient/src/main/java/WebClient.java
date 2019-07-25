import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import joel.lajoiecorriveau.lootgen.models.*;
import joel.lajoiecorriveau.lootgen.models.exception.*;
import joel.lajoiecorriveau.lootgen.transfer.GenLootData;
import joel.lajoiecorriveau.lootgen.transfer.SignInRequest;
import joel.lajoiecorriveau.lootgen.transfer.SignupRequest;
import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.List;
import java.io.IOException;
import java.util.Map;

/**
 * Created by 1387434 on 2015-11-23.
 */
public class WebClient {

    //TODO: Check every catch of exceptions so you can handle unrecognized IOExceptions
    private String baseURL;
    private URI baseURI;
    private CookieManager cookieMngr;

    private static final String COOKIES_HEADER = "Set-Cookie";

    private Gson gson = new Gson();

    public WebClient(String base) throws URISyntaxException
    {
        this.cookieMngr = new CookieManager();
        this.baseURL = base;
        this.baseURI = new URI(base.split(":")[0]);
    }

    // le plus simple pas d'entree pas de sortie
    public void fakeload() throws IOException, BadEmail, BadPassword, InvalidConfirmation, UserAlreadyExists, NoToken {
        InputStream is = null;
        HttpURLConnection conn = null;
        try
        {
            URL url = new URL(this.baseURL+"lootgen/test/initTestData");
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            setCookieHeader(conn);
            // Starts the query
            conn.connect();
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println("TESTDATA " +contentAsString);
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadEmail"))
            {
                throw new BadEmail(exception);
            }
            if(exception.contains("BadPassword"))
            {
                throw new BadPassword(exception);
            }
            if(exception.contains("InvalidConfirmation"))
            {
                throw new InvalidConfirmation(exception);
            }
            if(exception.contains("UserAlreadyExists"))
            {
                throw new UserAlreadyExists(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
//            if(exception.contains("BadEmail"))
//            {
//                throw new BadEmail("Erreur Email");
//            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // gestion des cas d'exceptions
    public Long signUp(String login, String password, String confpassword, String email) throws BadEmail, IOException, BadPassword, UserAlreadyExists, InvalidConfirmation, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/signup");
        SignupRequest request = new SignupRequest();
        request.username = login;
        request.password = password;
        request.confirmpassword = confpassword;
        request.email = email;
        String json = gson.toJson(request);
        HttpURLConnection conn = null;
        Long o = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            o = gson.fromJson(contentAsString, Long.class);

            return o;
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadEmail"))
            {
                throw new BadEmail(exception);
            }
            if(exception.contains("BadPassword"))
            {
                throw new BadPassword(exception);
            }
            if(exception.contains("InvalidConfirmation"))
            {
                throw new InvalidConfirmation(exception);
            }
            if(exception.contains("UserAlreadyExists"))
            {
                throw new UserAlreadyExists(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
//        catch(Exception e){
//            int response = conn.getResponseCode();
//            System.out.println("Response code " + response);
//            System.out.println("Response message " + conn.getResponseMessage());
//            System.out.println("Response code " + conn.getResponseMessage());
//            is = conn.getErrorStream();
//            // Convert the InputStream into a string
//            String exception = IOUtils.toString(is, "UTF-8");
//            System.out.println("Response content >" + exception+"<");
//            if(exception.equals("IllegalArgumentException"))
//                throw new BadEmail(login);
//        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return o;
    }



    // exemple de r�cup�ration d'une liste
    public List<User> getAllUsers() throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetAllUsers");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");

            setCookieHeader(conn);

            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);
            List<User> result =
                    gson.fromJson(contentAsString, new TypeToken<List<User>>(){}.getType());
            return result;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

//    public User getByUserId(Long id) throws NoUserExist, IOException {
//        InputStream is = null;
//        URL url = new URL(this.baseURL+"lootgen/GetUserById/" + id.toString());
//        HttpURLConnection conn = null;
//        User u = null;
//        try {
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000 /* milliseconds */);
//            conn.setConnectTimeout(15000 /* milliseconds */);
//            conn.setRequestMethod("GET");
//            conn.setInstanceFollowRedirects(false);
//            conn.setDoInput(true);
//            conn.setDoOutput(true);
//
////            OutputStream os = conn.getOutputStream();
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
////            os.close();
//
//            // Starts the query
//            conn.connect();
//
//            is = conn.getInputStream();
//            // Convert the InputStream into a string
//            String contentAsString = IOUtils.toString(is, "UTF-8");
//            System.out.println(contentAsString);
//
//            u = gson.fromJson(contentAsString, User.class);
//
//            // Makes sure that the InputStream is closed after the app is
//            // finished using it.
//        }
//        catch(IOException e){
//            int response = conn.getResponseCode();
//            System.out.println("Response code " + response);
//            System.out.println("Response message " + conn.getResponseMessage());
//            System.out.println("Response code " + conn.getResponseMessage());
//            is = conn.getErrorStream();
//            // Convert the InputStream into a string
//            String exception = IOUtils.toString(is, "UTF-8");
//            System.out.println("Response content >" + exception+"<");
//            if(exception.contains("NoUserExist"))
//            {
//                throw new NoUserExist(exception);
//            }
//        }
//        finally {
//            if (is != null) {
//                is.close();
//            }
//        }
//
//        return u;
//    }

    public Long saveUser(User pUser) throws BadEmail, IOException, BadPassword, UserAlreadyExists, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/SaveUser");
        User u = pUser;
        String json = gson.toJson(u);
        HttpURLConnection conn = null;
        Long id = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            id = gson.fromJson(contentAsString, Long.class);

            u.setID(id);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadEmail"))
            {
                throw new BadEmail(exception);
            }
            if(exception.contains("BadPassword"))
            {
                throw new BadPassword(exception);
            }
            if(exception.contains("UserAlreadyExists"))
            {
                throw new UserAlreadyExists(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return id;
    }
    // NOTE : You have to ReFetch each individual Users after saving them in bulk. Otherwise, their ID is not updated...
    public void saveUsers(List<User> pUsers) throws BadEmail, IOException, BadPassword, UserAlreadyExists, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/SaveUsers");
        String json = gson.toJson(pUsers);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            List<User> result =
                    gson.fromJson(contentAsString, new TypeToken<List<User>>(){}.getType());

            pUsers = result;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadEmail"))
            {
                throw new BadEmail(exception);
            }
            if(exception.contains("BadPassword"))
            {
                throw new BadPassword(exception);
            }
            if(exception.contains("UserAlreadyExists"))
            {
                throw new UserAlreadyExists(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public User getUserById(Long id) throws IOException, NoUserExist, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetUserById/" + id.toString());
        HttpURLConnection conn = null;

        User u = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            u = gson.fromJson(contentAsString, User.class);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return u;
    }

    public User getCurrentUser() throws IOException, NoUserExist, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetCurrentUser");
        HttpURLConnection conn = null;

        User u = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            u = gson.fromJson(contentAsString, User.class);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return u;
    }

    public void deleteUserById(Long id) throws IOException, NoUserExist, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteUserById/" + id.toString());
//        String json = gson.toJson(pUsers);
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void deleteUser(User pUser) throws NoUserExist, IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteUser");
        String json = gson.toJson(pUser);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void deleteAllUsers() throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteAllUsers");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public Long saveItem(Item pItem) throws BadItemName, IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/SaveItem");
        String json = gson.toJson(pItem);
        HttpURLConnection conn = null;
        Long id = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);
            //TODO : Assign the new ID to the item passed in parameters
            id = gson.fromJson(contentAsString, Long.class);

            pItem.setID(id);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadItemName"))
            {
                throw new BadItemName(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return id;
    }

    public void saveManyItems(List<Item> pItems) throws BadItemName, IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/SaveManyItems");
        String json = gson.toJson(pItems);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000000 /* milliseconds */);
            conn.setConnectTimeout(150000000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            List<Item> result =
                    gson.fromJson(contentAsString, new TypeToken<List<Item>>(){}.getType());

            pItems = result;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadItemName"))
            {
                throw new BadItemName(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // EXAMPLE COOKIE SEND
    public Item getItemById(Long id) throws IOException, ItemNotFound, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetItemById/" + id.toString());
        HttpURLConnection conn = null;

        Item i = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            setCookieHeader(conn);

            // CALL SET HEADER COOKIE

            setCookieHeader(conn);

            conn.setDoOutput(true);

//            OutputStream os = conn.getOutputStream();

//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
//
//            writer.write(json);
//            writer.flush();
//            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            i = gson.fromJson(contentAsString, Item.class);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("ItemNotFound"))
            {
                throw new ItemNotFound(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return i;
    }

    public List<Item> getItemsByUser() throws IOException, NoToken, NoUserExist {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetItemsByUser");
        HttpURLConnection conn = null;
//        String json = gson.toJson(u);
        List<Item> lst = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
//
//            writer.write(json);
//            writer.flush();
//            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            lst =
                    gson.fromJson(contentAsString, new TypeToken<List<Item>>(){}.getType());
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return lst;
    }

    public List<Item> getAllItems() throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetAllItems");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            setCookieHeader(conn);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);
            List<Item> result =
                    gson.fromJson(contentAsString, new TypeToken<List<Item>>(){}.getType());
            return result;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void deleteItemById(Long o) throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteItemById/" + o.toString());
        HttpURLConnection conn = null;
        Long id = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

    }

    public void deleteItem(Item pItem) throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteItem");
        String json = gson.toJson(pItem);
        HttpURLConnection conn = null;
        Long id = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

    }

    public void deleteAllItems() throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteAllItems");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            setCookieHeader(conn);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public Long saveItemPools(ItemPool pPool) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool, IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/SaveItemPools");
        String json = gson.toJson(pPool);
        HttpURLConnection conn = null;
        Long id = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            id = gson.fromJson(contentAsString, Long.class);

            pPool.setID(id);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadItemPoolName"))
            {
                throw new BadItemPoolName(exception);
            }
            if(exception.contains("BadSumOfPoolsChances"))
            {
                throw new BadSumOfPoolsChances(exception);
            }
            if(exception.contains("EmptyItemPool"))
            {
                throw new EmptyItemPool(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }

        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return id;
    }

    public void saveManyItemPools(List<ItemPool> pPools) throws BadItemPoolName, BadSumOfPoolsChances, EmptyItemPool, IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/SaveManyItemPools");
        String json = gson.toJson(pPools);
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            List<ItemPool> result =
                    gson.fromJson(contentAsString, new TypeToken<List<ItemPool>>(){}.getType());

            pPools = result;


            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadItemPoolName"))
            {
                throw new BadItemPoolName(exception);
            }
            if(exception.contains("BadSumOfPoolsChances"))
            {
                throw new BadSumOfPoolsChances(exception);
            }
            if(exception.contains("EmptyItemPool"))
            {
                throw new EmptyItemPool(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public ItemPool getItemPoolsById(Long id) throws IOException, ItemPoolNotFound, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetItemPoolsById/" + id.toString());
        HttpURLConnection conn = null;

        ItemPool i = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            i = gson.fromJson(contentAsString, ItemPool.class);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("ItemPoolNotFound"))
            {
                throw new ItemPoolNotFound(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return i;
    }

    public List<ItemPool> getItemPoolsByUser() throws IOException, NoToken, NoUserExist {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetItemPoolsByUser");
        HttpURLConnection conn = null;
//        String json = gson.toJson(u);
        List<ItemPool> lst = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
//
//            writer.write(json);
//            writer.flush();
//            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            lst =
                    gson.fromJson(contentAsString, new TypeToken<List<ItemPool>>(){}.getType());
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return lst;
    }

    public List<ItemPool> getAllItemPools() throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetAllItemPools");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            setCookieHeader(conn);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);
            List<ItemPool> result =
                    gson.fromJson(contentAsString, new TypeToken<List<ItemPool>>(){}.getType());
            return result;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void deleteItemPoolById(Long o) throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteItemPoolById/" + o.toString());
        HttpURLConnection conn = null;
        Long id = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

    }

    public void deleteItemPool(ItemPool pPool) throws IOException,NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteItemPool");
        String json = gson.toJson(pPool);
        HttpURLConnection conn = null;
        Long id = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

    }

    public void deleteAllItemPools() throws IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/DeleteAllItemPools");
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            setCookieHeader(conn);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());

        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public Rarity parseRarity(String rar) throws IOException, BadRarity, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/ParseRarity/" + rar);
        HttpURLConnection conn = null;

        Rarity i = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            i = gson.fromJson(contentAsString, Rarity.class);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadRarity"))
            {
                throw new BadRarity(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return i;
    }

    public List<Item> generateLoot(GenLootData pLootData) throws IOException, BadItemPoolChances, BadItemPoolEqualizer, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GenerateLoot");
        String json = gson.toJson(pLootData);
        HttpURLConnection conn = null;
        List<Item> loot = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            loot =
                    gson.fromJson(contentAsString, new TypeToken<List<Item>>(){}.getType());
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("BadItemPoolChances"))
            {
                throw new BadItemPoolChances(exception);
            }
            if(exception.contains("BadItemPoolEqualizer"))
            {
                throw new BadItemPoolEqualizer(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return loot;
    }

    public Token getTokenByUserId(Long id) throws IOException, NoToken, NoUserExist {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetTokenByUserId/" + id.toString());
        HttpURLConnection conn = null;

        Token i = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            i = gson.fromJson(contentAsString, Token.class);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return i;
    }

    public Token getTokenByUserEmail(String email) throws IOException, NoToken, NoUserExist {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/GetTokenByUserId/" + email.toString());
        HttpURLConnection conn = null;

        Token i = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            setCookieHeader(conn);

//            OutputStream os = conn.getOutputStream();
//
////            BufferedWriter writer = new BufferedWriter(
////                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);


            i = gson.fromJson(contentAsString, Token.class);
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return i;
    }




    public User signin(String login, String password) throws InvalidLoginAttempt, NoUserExist, MalformedURLException, IOException, NoToken {
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/signin");
        SignInRequest request = new SignInRequest();
        request.username = login;
        request.password = password;
        String json = gson.toJson(request);
        HttpURLConnection conn = null;

        User u = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setInstanceFollowRedirects(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(json);
            writer.flush();
            writer.close();
            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            Token t = gson.fromJson(contentAsString, Token.class);

            u = this.getUserById(t.userID);

            updateCookie(conn);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("InvalidLoginAttempt"))
            {
                throw new InvalidLoginAttempt(exception);
            }
            if(exception.contains("NoUserExist"))
            {
                throw new NoUserExist(exception);
            }
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }

        return u;
    }

    public void signout() throws MalformedURLException, IOException, NoToken{
        InputStream is = null;
        URL url = new URL(this.baseURL+"lootgen/signout");
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setInstanceFollowRedirects(false);

            setCookieHeader(conn);

            conn.setDoInput(true);
            conn.setDoOutput(true);

//            OutputStream os = conn.getOutputStream();
//            BufferedWriter writer = new BufferedWriter(
//                    new OutputStreamWriter(os, "UTF-8"));
////
////            writer.write(json);
////            writer.flush();
////            writer.close();
//            os.close();

            // Starts the query
            conn.connect();

            is = conn.getInputStream();
            // Convert the InputStream into a string
            String contentAsString = IOUtils.toString(is, "UTF-8");
            System.out.println(contentAsString);

            updateCookie(conn);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        }
        catch(IOException e){
            int response = conn.getResponseCode();
            System.out.println("Response code " + response);
            System.out.println("Response message " + conn.getResponseMessage());
            System.out.println("Response code " + conn.getResponseMessage());
            is = conn.getErrorStream();
            // Convert the InputStream into a string
            String exception = IOUtils.toString(is, "UTF-8");
            System.out.println("Response content >" + exception+"<");
            if(exception.contains("NoToken"))
            {
                throw new NoToken(exception);
            }
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void setCookieHeader(HttpURLConnection conn)
    {
        String cookieValue = "";
        if(cookieMngr.getCookieStore().get(baseURI) == null || cookieMngr.getCookieStore().get(baseURI).size() == 0)
        {
            // The cookie is not there
            System.out.println("No Cookie set on this request.");
        }
        else
        {
            // The cookie is there
            cookieValue = cookieMngr.getCookieStore().get(baseURI).get(0).toString();
            conn.setRequestProperty("Cookie", cookieValue);
            System.out.println("Cookie Set "+ cookieValue);
        }

    }

    public void updateCookie(HttpURLConnection conn)
    {
        Map<String, List<String>> headerFields = conn.getHeaderFields();
        List<String> cookiesHeader = headerFields.get(COOKIES_HEADER);
        System.out.println("Headers  ================================== \n"+headerFields+"\n===============================");
        if(cookiesHeader != null)
        {
            for (String cookie : cookiesHeader)
            {
                System.out.println("Cookie header : " + cookie);
                List<HttpCookie> co = HttpCookie.parse(cookie);
                for (HttpCookie c : co){
                    System.out.println("HttpCookie : " + c);
                    cookieMngr.getCookieStore().add(baseURI, c);
                }
                System.out.println(cookieMngr.getCookieStore());

            }
        }
    }
}
