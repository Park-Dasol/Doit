package com.ssafy.doit.model.response;

import com.ssafy.doit.model.store.Product;
import com.ssafy.doit.model.store.ChatRoom;
import com.ssafy.doit.model.store.ChatRoomJoin;
import com.ssafy.doit.model.store.ProductStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class ResponseChatRoom {
    private Long id;
    private Product product;
    private ResponseUser otherUser;
    private String Button;

    public ResponseChatRoom(ChatRoom chatRoom, Long currentUser){
        this.id = chatRoom.getId();
        this.product = chatRoom.getProduct();

        List<ChatRoomJoin> chatRoomJoins = chatRoom.getChatRoomJoins();
        this.otherUser = chatRoomJoins.get(0).getUser().getId() == currentUser ?
                new ResponseUser(chatRoomJoins.get(1).getUser()) : new ResponseUser(chatRoomJoins.get(0).getUser());

        if(!currentUser.equals(product.getUser().getId()) && product.getStatus() == ProductStatus.WAITING)
            this.Button = "purchase";
        else if(currentUser.equals(product.getUser().getId()) && product.getStatus() == ProductStatus.ONSALE)
            this.Button = "sale";
    }
}
