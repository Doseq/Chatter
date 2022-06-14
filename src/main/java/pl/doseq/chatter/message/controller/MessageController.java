package pl.doseq.chatter.message.controller;

import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.doseq.chatter.channel.service.ChannelService;
import pl.doseq.chatter.message.model.Message;
import pl.doseq.chatter.message.model.NewMessageDto;

@RestController
@RequestMapping("/message")
@AllArgsConstructor
public class MessageController {

    private ChannelService channelService;

    @MessageMapping("/channel/{id}")
    @SendTo("/topic/channel/{id}")
    Message greet(@DestinationVariable long id, NewMessageDto newMessageDto) throws Exception {
        return channelService.addMessage(id, newMessageDto);
    }

}
