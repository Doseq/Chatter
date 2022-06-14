package pl.doseq.chatter.channel.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.doseq.chatter.channel.model.Channel;
import pl.doseq.chatter.channel.model.NewChannelDto;
import pl.doseq.chatter.channel.repository.ChannelRepository;
import pl.doseq.chatter.message.model.Message;
import pl.doseq.chatter.message.model.NewMessageDto;
import pl.doseq.chatter.user.model.User;
import pl.doseq.chatter.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class ChannelService {

    private ChannelRepository channelRepository;
    private UserRepository userRepository;

    public Page<Channel> getChannels(Pageable pageable) {
        return channelRepository.findAll(pageable);
    }

    public Channel createChannel(NewChannelDto newChannelDto) {
        return channelRepository.save(Channel.builder()
                .name(newChannelDto.getName())
                .build());
    }

    public boolean removeChannel(long id) {
        channelRepository.deleteById(id);
        return channelRepository.findById(id).isEmpty();
    }

    public Message addMessage(long id, NewMessageDto newMessageDto) {
        User user = userRepository.findById(newMessageDto.getUser().getId()).get(); //TODO: handle this, replace with JWT in future
        Message message = Message.builder()
                .content(newMessageDto.getContent())
                .user(user)
                .build();
        Channel channel = channelRepository.findById(id).get(); //TODO: handle this
        channel.addMessage(message);
        return message;
    }
}
