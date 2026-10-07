package ressources;

import entities.Option;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static metiers.OptionBusiness.options;

@Path("/options")
public class OptionRest {

    public static OptionBusiness opb = new OptionBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOptions() {

        List l = opb.getListeOptions();

        if (l.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT)
                    .entity("No data found")
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity(l)
                .build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOption(Option option) {

        if(opb.addOption(option))
        {
            return Response.status(201).build();
        }
            return Response.status(400).build();
    }

    public List<Option> getAllOptions(@QueryParam("domaine") String d) {

        if (d != null && !d.isEmpty()) {
            return opb.getOptionsByDomaine(d);
        }

        return opb.getListeOptions();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public Response deleteOption(@PathParam("id") int id) {

        boolean deleted = opb.deleteOption(id);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).entity("Deleted").build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOption(@PathParam("id") int id, Option option) {

        boolean updated = opb.updateOption(id, option);

        if (updated) {
            return Response.status(Response.Status.OK)
                    .entity(option)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOptionByCode(@PathParam("id") int id) {

        Option option = opb.getOptionByCode(id);

        if (option != null) {
            return Response.status(Response.Status.OK)
                    .entity(option)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
