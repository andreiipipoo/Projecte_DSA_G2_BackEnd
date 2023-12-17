package edu.upc.dsa.services;

import edu.upc.dsa.CRUD.DAO.TrappyManager;
import edu.upc.dsa.CRUD.DAO.TrappyManagerImpl;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Api(value = "/trappy", description = "Endpoint to Trappy Service")
@Path("/trappy")

public class TrappyService {
    final static Logger logger = Logger.getLogger(TrappyService.class);
    private TrappyManager tm;
    private PlayerManager pm;

    public TrappyService() {
        this.tm = TrappyManagerImpl.getInstance();
        this.pm = PlayerManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "register", notes = "register")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/player/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Player player) {
        try {
            this.tm.register(new Player(player.getId(), player.getUsername(), player.getPassword(), player.getEmail(), player.getTelephoneNumber()));
            return Response.status(201).entity(player).build();
        } catch (UsernameInUseException e) {
            logger.error("UsernameInUseException");
            return Response.status(500).entity(player).build();
        }
    }

    @POST
    @ApiOperation(value = "login", notes = "login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/player/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Login login) {
        try {
            Player player = this.tm.login(login);
            return Response.status(201).entity(player).build();
        } catch (PasswordNotMatchException e) {
            logger.error("PasswordNotMatchException");
            return Response.status(500).entity(login).build();
        }
    }
    @GET
    @ApiOperation(value = "items from shop", notes = "items from shop")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/shop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shop() {
        List<Item> items = this.tm.Shop();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build();
    }
    @PUT
    @ApiOperation(value = "buy item", notes = "buy item")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/buyItem/{idItem}/{idPlayer}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(@PathParam("idItem") String idItem, @PathParam("idPlayer") String idPlayer) {
        try {
            this.pm.buyItem(idItem, idPlayer);
            return Response.status(201).build();
        } catch (Exception e) {
            logger.error("Exception");
            return Response.status(500).build();
        }
    }
}





