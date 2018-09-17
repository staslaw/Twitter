package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
