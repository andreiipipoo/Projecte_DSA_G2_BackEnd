package edu.upc.dsa.services;

import edu.upc.dsa.CRUD.DAO.*;
import edu.upc.dsa.CRUD.DAO.TrappyManager;
import edu.upc.dsa.CRUD.DAO.TrappyManagerImpl;
import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;

import javax.naming.InsufficientResourcesException;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/player", description = "Endpoint to Player Service")
@Path("/player")
public class PlayerService {
    final static Logger logger = Logger.getLogger(PlayerService.class);
    private TrappyManager tm;
    private PlayerManager pm;
    private InventoryManager im;

    public PlayerService() {
        this.tm = TrappyManagerImpl.getInstance();
        this.pm = PlayerManagerImpl.getInstance();
        this.im = InventoryManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "register", notes = "register")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 500, message = "This email is already in use")
    })
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(Player player) {
        try {
            this.tm.registerPlayer(new Player(player.getIdPlayer(), player.getUsername(), player.getPassword(), player.getTelephone(), player.getEmail()));
            return Response.status(201).entity(player).build();
        } catch (EmailAlreadyInUseException e) {
            logger.error("EmailAlreadyInUseException");
            return Response.status(500).entity(player).build();
        }
    }

    @POST
    @ApiOperation(value = "login", notes = "login")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 402, message = "Incorrect password")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials c) throws PlayerNotRegisteredException, PasswordIsIncorrectException {
        try {
            String id = this.tm.loginPlayer(c);
            logger.info("Player logged in" + id);
            idPlayer idPlayer = new idPlayer(id);
            return Response.status(201).entity(id).build();
        } catch (PlayerNotRegisteredException e) {
            logger.error("PlayerNotRegisteredException");
            return Response.status(404).build();
        } catch (PasswordIsIncorrectException e) {
            logger.error("PasswordIsIncorrectException");
            return Response.status(402).build();
        }
    }
}
