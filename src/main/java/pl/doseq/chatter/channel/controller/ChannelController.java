package pl.doseq.chatter.channel.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.doseq.chatter.channel.model.Channel;
import pl.doseq.chatter.channel.model.NewChannelDto;
import pl.doseq.chatter.channel.service.ChannelService;

@RestController
@RequestMapping("/channel")
@AllArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping
    public ResponseEntity<Page<Channel>> getChannels(@RequestParam int limit, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, limit);
        return new ResponseEntity<>(channelService.getChannels(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Channel> createChannel(@RequestBody NewChannelDto newChannelDto) {
        return new ResponseEntity<>(channelService.createChannel(newChannelDto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteChannel(@RequestParam long id) {
        return new ResponseEntity<>(channelService.removeChannel(id), HttpStatus.OK);
    }


}
