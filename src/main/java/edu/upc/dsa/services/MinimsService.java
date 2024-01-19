package edu.upc.dsa.services;


import edu.upc.dsa.CRUD.DAO.*;
import edu.upc.dsa.models.*;
import edu.upc.dsa.CRUD.DAO.QuestionFromPlayerManager;
import edu.upc.dsa.CRUD.DAO.QuestionFromPlayerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;



@Api(value = "/minims", description = "Endpoint to Minims Service")
@Path("/minims")
public class MinimsService {

    private QuestionFromPlayerManager questionManager;
    private IssueManager issueManager;
    private MessageManager messageManager;

    public MinimsService() {
        this.questionManager = QuestionFromPlayerImpl.getInstance();
        this.issueManager = IssueManagerImpl.getInstance();
        this.messageManager = MessageManagerImpl.getInstance();
        if(messageManager.MessagesSize()==0){
            this.messageManager.addMessage(new Message("Nuevas skins han sido añadidas en la tienda de Croacky!"));
            this.messageManager.addMessage(new Message("Proximamente se añadiran nuevos niveles!"));
            this.messageManager.addMessage(new Message("Se ha implementado un nuevo sistema de reportes!"));
            this.messageManager.addMessage(new Message("Charly ha sido baneado por 1 semana!"));
        }
    }

    // MINIMO 2 - AÑADIR PREGUNTA DE UN JUGADOR - ANDREI
    @POST
    @ApiOperation(value = "add a new question from a player", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = QuestionFromPlayer.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addQuestionFromPlayer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addQuestionFromPlayer(QuestionFromPlayer q) {
        QuestionFromPlayer question = new QuestionFromPlayer(q.getDate(), q.getTitle(), q.getMessage(), q.getSender());
        if (question.getDate().isEmpty() || question.getTitle().isEmpty() || question.getMessage().isEmpty() || question.getSender().isEmpty()) {
            return Response.status(500).entity(question).build();
        }
        else {
            this.questionManager.addQuestionFromPlayer(question);
            return Response.status(200).entity(question).build();
        }
    }

    // MINIMO 2 - NOTIFICACIÓN DE UN ABUSO/DENÚNCIA - SEBASTIAN
    @POST
    @ApiOperation(value = "add a new abuse report", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Issue.class),
            @ApiResponse(code = 404, message = "Player not found"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addIssue")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIssue(Issue i) {
        Issue issue = new Issue(i.getDate(), i.getTitle(), i.getMessage(), i.getSender());
        if (issue.getDate().isEmpty() || issue.getTitle().isEmpty() || issue.getMessage().isEmpty() || issue.getSender().isEmpty()) {
            return Response.status(500).entity(issue).build();
        }
        else {
            this.issueManager.addIssue(issue);
            return Response.status(200).entity(issue).build();
        }
    }

    // MINIMO 2 - MESSAGES FROM THE SYSTEM - CARLES
    @GET
    @ApiOperation(value = "get all messages from the system", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Message.class, responseContainer="List"),
    })
    @Path("/getMessages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessages() {
        List<Message> messageList = this.messageManager.getAllMessages();
        GenericEntity<List<Message>> entity = new GenericEntity<List<Message>>(messageList) {};
        return Response.status(200).entity(entity).build();
    }
}
