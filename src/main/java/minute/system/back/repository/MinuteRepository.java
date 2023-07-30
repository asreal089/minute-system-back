package minute.system.back.repository;

import org.springframework.data.repository.Repository;

import minute.system.back.model.entity.Minute;

public interface MinuteRepository extends Repository<Minute, Long>{

    public Minute save(Minute minute);
    
}
