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


    // GET ALL ITEMS FROM STORE
    @GET
    @ApiOperation(value = "Get all items from the store", notes = " ")
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

    //GET ALL ITEMS FROM A PARTICULAR PLAYER
    @GET
    @ApiOperation(value = "Get a particular Player's inventory", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Item.class),
            @ApiResponse(code = 404, message = "Player not found")
    })
    @Path("inventoryList/{playerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInventoryList(@PathParam("playerId") String playerId) {

        Player player = playerManager.getPlayerById(playerId);
        if (player == null) {
            return Response.status(404).build();
        } else {
            List<Item> inventory = this.inventoryManager.getPlayerInventory(playerId);
            GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(inventory) {};
            return Response.status(200).entity(entity).build();
        }
    }


    // BUY AN ITEM FROM THE STORE
    @PUT
    @ApiOperation(value = "Buy an Item", notes = " ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful"),
            @ApiResponse(code = 402, message = "Not enough coins"),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 405, message = "Item not found"),
            @ApiResponse(code = 409, message = "Item is already in possession")
    })
    @Path("/buyItem/{itemId}/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyItem(@PathParam("itemId") String itemId, @PathParam("userName") String userName) throws IntrospectionException {

        Player player = playerManager.getPlayerByUsername(userName);
        Item item = itemManager.getItemById(itemId);

        if (player == null) {
            return Response.status(404).build();
        } else if (item == null) {
            return Response.status(405).build();
        } else if (player.getCroCoins() < item.getPrice()) {
            return Response.status(402).build();
        } else if (inventoryManager.repeatItem(userName, itemId)) {
            return Response.status(409).build();
        } else {
            this.inventoryManager.buyItem(userName,itemId);
            return Response.status(200).build();
        }
    }
}
