package pl.doseq.chatter.channel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.doseq.chatter.channel.model.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {

}
