package edu.upc.dsa.services;

import edu.upc.dsa.CRUD.DAO.*;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

@Api(value = "/player", description = "Endpoint to Player Service")
@Path("/player")
public class PlayerService {
    private PlayerManager pm;

    public PlayerService() {
        this.pm = PlayerManagerImpl.getInstance();
    }

    // SIGN UP
    @POST
    @ApiOperation(value = "Sign up a new player", notes = "username + password + telephone + email")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 405, message = "Username already in use"),
            @ApiResponse(code = 406, message = "Email already in use"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response playerSignUp(SignUpCredentials playerCred) {

        Player player = new Player(playerCred.getUsername(), playerCred.getPassword(), playerCred.getTelephone(), playerCred.getEmail());
        if (player.getUsername().isEmpty() || player.getPassword().isEmpty() || player.getTelephone().isEmpty() || player.getEmail().isEmpty())
            return Response.status(500).build();

        Player nameCheck = this.pm.getPlayerByUsername(playerCred.getUsername());
        Player emailCheck = this.pm.getPlayerByEmail(playerCred.getEmail());
        if (nameCheck != null )
            return Response.status(405).build();
        else if (emailCheck != null )
            return Response.status(406).build();
        else {
            this.pm.addPlayer(player.getUsername(), player.getPassword(), player.getTelephone(), player.getEmail());
            return Response.status(201).entity(player).build();
        }
    }

    // LOGIN
    @POST
    @ApiOperation(value = "Login Player", notes = "Username and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 405, message = "Wrong password"),
            @ApiResponse(code = 500, message = "Invalid credentials")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logInUser(LoginInCredentials logInCred) {
        Player player = pm.getPlayerByUsername(logInCred.getUsername());
        if ((logInCred.getUsername().isEmpty()) || (logInCred.getPassword().isEmpty()))
            return Response.status(500).build();
        else if (player == null)
            return Response.status(404).build();
        else {
            if (player.getPassword().equals(logInCred.getPassword()))
                return Response.status(200).entity(player).build();
            else
                return Response.status(405).build();
        }
    }



    //GET THE INFO OF THE PLAYERS ONLINE
    @GET
    @ApiOperation(value = "Get all items from the store", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class, responseContainer="List"),
    })
    @Path("/playersInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoPlayers() {
        List<Player> playersInfo = this.pm.getInfoPlayers();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(playersInfo) {};
        return Response.status(200).entity(entity).build();
    }





    //GET THE INFO OF THE PLAYER
    @GET
    @ApiOperation(value = "Get all items from the store", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class, responseContainer="List"),
    })
    @Path("/playerInfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfoPlayer() {
        List<Player> playersInfo = this.pm.getInfoPlayers();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(playersInfo) {};
        return Response.status(200).entity(entity).build();
    }



    // GET ONE PLAYER in particular
    @GET
    @ApiOperation(value = "Get a particular player", notes = "username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayer(@PathParam("username") String username) {
        Player player = pm.getPlayerByUsername(username);
        if (player == null) {
            return Response.status(404).build();
        } else {
            GenericEntity<Player> entity = new GenericEntity<Player>(player) {};
            return Response.status(201).entity(entity).build();
        }
    }

    //DELETE ONE PLAYER
    @DELETE
    @ApiOperation(value = "Delete a player", notes = "Id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/delete/{username}")
    public Response deletePlayer(@PathParam("username") String username){

        Player player = pm.getPlayerByUsername(username);
        if (player == null) {
            return Response.status(404).build();
        }
        else {
            pm.deletePlayer(username);
            return Response.status(201).entity(player).build();
        }
    }

    // UPDATE PLAYER
    @PUT
    @ApiOperation(value = "Update player credentials", notes = "username, password, telephone, email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 405, message = "Username already in use"),
            @ApiResponse(code = 406, message = "Email already in use"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/update/{oldUsername}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlayer(@PathParam("oldUsername") String oldUsername, SignUpCredentials playerCredentials){

        Player oldPlayer = pm.getPlayerByUsername(oldUsername);
        if ((playerCredentials.getUsername().isEmpty()) || (playerCredentials.getPassword().isEmpty()) || (playerCredentials.getTelephone().isEmpty()) || (playerCredentials.getEmail().isEmpty()))
            return Response.status(500).build();
        else if (oldPlayer == null) {
            return Response.status(404).build();
        }

        Player nameCheck = this.pm.getPlayerByUsername(playerCredentials.getUsername());
        Player emailCheck = this.pm.getPlayerByEmail(playerCredentials.getEmail());
        if ((nameCheck != null) && (!nameCheck.getUsername().equals(oldUsername)) )
            return Response.status(405).build();
        else if ((emailCheck != null) && (!emailCheck.getEmail().equals(playerCredentials.getEmail())))
            return Response.status(406).build();

        else {
            pm.updatePlayer(oldUsername, playerCredentials.getUsername(), playerCredentials.getPassword(), playerCredentials.getTelephone() ,playerCredentials.getEmail());
            return Response.status(200).entity(playerCredentials).build();
        }
    }
}
