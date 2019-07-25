package joel.lajoiecorriveau.lootgen;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CatchAllMapper implements ExceptionMapper<Exception> {

//    @Override
    public javax.ws.rs.core.Response toResponse(Exception ex) {
        System.out.println("CatchAllMapper : " + ex.getClass().getSimpleName()+"    "+ex.getMessage());
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity(
                "Unfortunately, LootGen has encountered one or more errors : " +
                        ex.getClass().getSimpleName() + " - " + ex.getMessage() + "" +
                        " We are sorry for any inconvenience. Try again?"
        ).build();
    }

}