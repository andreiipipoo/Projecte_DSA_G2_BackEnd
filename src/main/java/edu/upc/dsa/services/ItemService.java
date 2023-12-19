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

@Api(value = "/item", description = "Endpoint to Player Service")
@Path("/item")
public class ItemService {
    final static Logger logger = Logger.getLogger(ItemService.class);
    private TrappyManager tm;
    private PlayerManager pm;
    private InventoryManager im;

    public ItemService() {
        this.tm = TrappyManagerImpl.getInstance();
        this.pm = PlayerManagerImpl.getInstance();
        this.im = InventoryManagerImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Items from the shop", notes = "Items from the shop")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Item.class, responseContainer= "List"),
    })
    @Path("/shop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() {
        List<Item> items = this.tm.ShopItems();
        GenericEntity<List<Item>> entity = new GenericEntity<List<Item>>(items) {};
        return Response.status(201).entity(entity).build();
    }
}
