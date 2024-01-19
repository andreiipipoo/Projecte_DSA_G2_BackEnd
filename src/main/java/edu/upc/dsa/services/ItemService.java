package edu.upc.dsa.services;

import edu.upc.dsa.CRUD.DAO.*;
import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.Player;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.beans.IntrospectionException;
import java.util.List;

@Api(value = "/item", description = "Endpoint to Item Service")
@Path("/item")
public class ItemService {
    private ItemManager itemManager;
    private PlayerManager playerManager;
    private InventoryManager inventoryManager;

    public ItemService() {
        this.itemManager = ItemManagerImpl.getInstance();
        this.playerManager = PlayerManagerImpl.getInstance();
        this.inventoryManager = InventoryManagerImpl.getInstance();
    }

    @Path("basic")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }


    // get ALL items from the store --> OK
    @GET
    @ApiOperation(value = "Get all items in store", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Item.class, responseContainer="List"),
    })
    @Path("/storeList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStoreItems() {

        List<Item> storeList = this.itemManager.getStoreList();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(storeList) {};
        return Response.status(200).entity(entity).build();
    }

    //get ALL items from a user's inventory --> OK
    @GET
    @ApiOperation(value = "Get a particular User's inventory", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("inventoryList/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryList(@PathParam("username") String username) {

        Player user = playerManager.getPlayerByUsername(username);
        if (user == null) {
            return Response.status(404).build();
        } else {
            List<Item> inventory = this.inventoryManager.getUserInventory(user.getId());
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(inventory) {};
            return Response.status(200).entity(entity).build();
        }
    }


    // buy item from store --> OK
    @PUT
    @ApiOperation(value = "Buy an Item", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 402, message = "Not enough coins"),
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 405, message = "Item not found"),
            @ApiResponse(code = 409, message = "Item is already in possession")
    })
    @Path("/buyItem/{itemname}/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(@PathParam("itemname") String itemname, @PathParam("username") String username) throws IntrospectionException {

        Player user = playerManager.getPlayerByUsername(username);
        Item item = itemManager.getItemByName(itemname);

        if (user == null) {
            return Response.status(404).build();
        } else if (item == null) {
            return Response.status(405).build();
        } else if (user.getCroCoins() < item.getPrice()) {
            return Response.status(402).build();
        } else if (inventoryManager.alreadyExists(username, itemname)){
            return Response.status(409).build();
        } else {
            this.inventoryManager.buyItem(username,itemname);
            return Response.status(200).build();
        }
    }





















}
