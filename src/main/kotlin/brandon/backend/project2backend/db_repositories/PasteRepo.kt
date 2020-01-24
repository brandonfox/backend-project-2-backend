package brandon.backend.project2backend.db_repositories

import brandon.backend.project2backend.db_objects.Paste
import org.springframework.data.repository.CrudRepository

interface PasteRepo : CrudRepository<Paste,Long> {

    fun findTop100ByOrderByCreatedAtDesc(): List<Paste>

}