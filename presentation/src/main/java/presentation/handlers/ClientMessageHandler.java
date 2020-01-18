package presentation.handlers;

import io.javalin.websocket.WsMessageContext;
import presentation.models.Room;
import presentation.models.UserViewModel;
import presentation.models.messages.EmptyMessageModel;
import presentation.models.messages.SetIntersectionMessageModel;
import presentation.models.messages.UserMessageModel;

import static presentation.handlers.MessageHandler.*;

public class ClientMessageHandler {
    private static final int MAX_MESSAGE_LENGTH = 200;
    private static final int MIN_MESSAGE_LENGTH = 1;

   public static void pulseMessage(WsMessageContext ctx) {
       broadcastMessageTo(ctx, new EmptyMessageModel("CHECK"));
   }

   public static void setStartPointMessage(WsMessageContext ctx, Room room){
       var setStartPointMessage = ctx.message(SetIntersectionMessageModel.class);
       room.setUserStartPoint(ctx, setStartPointMessage.getIntersectionId());
       broadcastRouteFinder(ctx, room);
   }

   public static void setDestinationMessage(WsMessageContext ctx, Room room) {
       var setDestinationMessage = ctx.message(SetIntersectionMessageModel.class);
       room.setDestination(setDestinationMessage.getIntersectionId());
       broadcastRouteFinder(ctx, room);
   }

   public static void chatMessage(WsMessageContext ctx, Room room) {
       var chatMessage = ctx.message(UserMessageModel.class);
       int chatLength = chatMessage.getMessage().length();

       if (chatLength >= MIN_MESSAGE_LENGTH && chatLength <= MAX_MESSAGE_LENGTH) {
           UserViewModel user = room.getUser(ctx);
           chatMessage.setSender(user.getName());
           broadcastMessage(chatMessage, room);
       }
   }

}
