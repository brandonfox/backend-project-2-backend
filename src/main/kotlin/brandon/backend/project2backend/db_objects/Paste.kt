package brandon.backend.project2backend.db_objects

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(indexes = [ Index(name = "times", columnList = "createdAt", unique = false) ])
class Paste constructor(
        @Id
        @GeneratedValue
        var id: Long? = null,
        @Column(nullable = false)
        var title: String? = null,
        @Column(nullable = false,columnDefinition="LONGTEXT")
        var content: String? = null,
        @Column(nullable = false)
        var createdAt: LocalDateTime? = null
){

}