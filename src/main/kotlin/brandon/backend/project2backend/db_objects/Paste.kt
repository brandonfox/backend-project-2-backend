package brandon.backend.project2backend.db_objects

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Paste constructor(
        @Id
        @GeneratedValue
        var id: Long? = null,
        @Column(nullable = false)
        var title: String? = null,
        @Column(nullable = false)
        var content: String? = null,
        @Column(nullable = false)
        var timePosted: Long? = null
){

}