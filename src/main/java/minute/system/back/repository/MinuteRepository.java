package minute.system.back.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import minute.system.back.model.entity.Minute;
import minute.system.back.model.entity.MinuteResult;

public interface MinuteRepository extends Repository<Minute, Long>{

    public Minute save(Minute minute);

    public Minute findById(Long id);

    @Query(value = "SELECT COUNT(*) AS total, SUM(CASE WHEN vote = 'yes' THEN 1 ELSE 0 END) AS yes, SUM(CASE WHEN vote = 'no' THEN 1 ELSE 0 END) AS no FROM TBvotes WHERE id_minute = ?", nativeQuery = true)
    public MinuteResult findResultById(Long id);
    
}
