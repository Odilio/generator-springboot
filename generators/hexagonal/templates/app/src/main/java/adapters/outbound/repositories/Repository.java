package <%= packageName %>.adapters.outbound.repositories;

import <%= packageName %>.adapters.entities.<%= entityName %>;
import org.springframework.data.jpa.repository.JpaRepository;

public interface <%= entityName %>Repository extends JpaRepository<<%= entityName %>, Long> {}
