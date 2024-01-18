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

        Player player = new Player(playerCred.getUsername(), playerCred.getPassword(), playerCred.getTelephone() ,playerCred.getEmail());
        if (player.getEmail().isEmpty() || player.getUsername().isEmpty() || player.getPassword().isEmpty() || player.getTelephone().isEmpty())
            return Response.status(500).build();

        Player namecheck = this.pm.getPlayerByUsername(playerCred.getUsername());
        Player emailcheck = this.pm.getPlayerByEmail(playerCred.getEmail());
        if (namecheck != null )
            return Response.status(405).build();
        else if (emailcheck != null )
            return Response.status(406).build();
        else {
            this.pm.addPlayer(player.getUsername(), player.getPassword(), player.getTelephone() ,player.getEmail());
            return Response.status(201).entity(player).build();
        }
    }

    // LOGIN
    @POST
    @ApiOperation(value = "Login Player", notes = "Username and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Wrong password"),
            @ApiResponse(code = 500, message = "Invalid credentials")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response logInUser(LoginInCredentials logInCred) {
        Player player = this.pm.getPlayerByUsername(logInCred.getUsername());
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

    /*
    // GET ALL PLAYERS signed up
    @GET
    @ApiOperation(value = "Get all signed up Players", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class, responseContainer="List"),
    })
    @Path("/playersList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlayers() {
        List<Player> playerList = this.pm.getAllPlayers();
        GenericEntity<List<Player>> entity = new GenericEntity<List<Player>>(playerList) {};
        return Response.status(201).entity(entity).build();
    }
*/

    // GET ONE PLAYER in particular
    @GET
    @ApiOperation(value = "Get a particular player", notes = "username")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Player.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("username") String username) {
        Player player = this.pm.getPlayerByUsername(username);
        if (player == null) {
            return Response.status(404).build();
        } else {
            GenericEntity<Player> entity = new GenericEntity<Player>(player) {};
            return Response.status(201).entity(entity).build();
        }
    }

    //DELETE ONE PLAYER
    @DELETE
    @ApiOperation(value = "Delete a player", notes = "id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("/delete/{id}")
    public Response deleteUser(@PathParam("id") String id){

        Player player = pm.getPlayerById(id);
        if (player == null) {
            return Response.status(404).build();
        }
        else {
            pm.deletePlayer(id);
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

        Player namecheck = this.pm.getPlayerByUsername(playerCredentials.getUsername());
        Player emailcheck = this.pm.getPlayerByEmail(playerCredentials.getEmail());
        if ((namecheck != null) && (!namecheck.getUsername().equals(oldUsername)) )
            return Response.status(405).build();
        else if ((emailcheck != null) && (!emailcheck.getEmail().equals(playerCredentials.getEmail())))
            return Response.status(406).build();

        else {
            pm.updatePlayer(oldUsername, playerCredentials.getUsername(), playerCredentials.getPassword(), playerCredentials.getTelephone() ,playerCredentials.getEmail());
            return Response.status(200).entity(playerCredentials).build();
        }
    }
}
