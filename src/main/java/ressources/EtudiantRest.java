package ressources;

import entities.Etudiant;
import entities.Option;
import metiers.EtudiantBusiness;
import metiers.OptionBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/etudiants")
public class EtudiantRest {
    public EtudiantBusiness eb = new EtudiantBusiness();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEtudiant(Etudiant etudiant) {

        boolean added = eb.addEtudiant(etudiant);

        if (added) {
            return Response.status(Response.Status.OK)
                    .entity(etudiant)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEtudiants() {

        return Response.status(Response.Status.OK)
                .entity(eb.getAllEtudiants())
                .build();
    }

    @GET
    @Path("/{identifiant}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEtudiantByIdentifiant(@PathParam("identifiant") String identifiant) {

        Etudiant etudiant = eb.getEtudiantByIdentifiant(identifiant);

        if (etudiant != null) {
            return Response.status(Response.Status.OK)
                    .entity(etudiant)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @DELETE
    @Path("/{identifiant}")
    public Response deleteEtudiant(@PathParam("identifiant") String identifiant) {

        boolean deleted = eb.deleteEtudiant(identifiant);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @PUT
    @Path("/{identifiant}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEtudiant(
            @PathParam("identifiant") String identifiant,
            Etudiant etudiant) {

        boolean updated = eb.updateEtudiant(identifiant, etudiant);

        if (updated) {
            return Response.status(Response.Status.OK)
                    .entity(etudiant)
                    .build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/option")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEtudiantsByOption(
            @QueryParam("codeOption") int codeOption) {

        Option option = new OptionBusiness().getOptionByCode(codeOption);

        if (option == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Etudiant> etudiants = eb.getEtudiantsByOption(option);

        return Response.status(Response.Status.OK)
                .entity(etudiants)
                .build();
    }
}

