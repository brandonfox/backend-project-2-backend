package brandon.backend.project2backend

import brandon.backend.project2backend.db_objects.Paste
import brandon.backend.project2backend.db_repositories.PasteRepo
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class ApiController @Autowired constructor(val pasteRepo: PasteRepo){

    val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/paste")
    fun paste(@RequestBody data: Paste): ResponseEntity<Any>{
        data.createdAt = LocalDateTime.now()
        if(data.content == null){
            return ResponseEntity("Content cannot be empty",HttpStatus.BAD_REQUEST)
        }
        else if(data.title == null){
            return ResponseEntity("Title cannot be empty",HttpStatus.BAD_REQUEST)
        }
        else {
            val utf: ByteArray = data.content!!.toByteArray(StandardCharsets.UTF_8)
            logger.info("Logging content size of ${utf.size} bytes")
            if(utf.size > 64000) return ResponseEntity("Content too big",HttpStatus.BAD_REQUEST)
            logger.info("Logged new paste: Title: ${data.title}, Time posted: ${data.createdAt}")
            pasteRepo.save(data)
            return ResponseEntity(object {
                val title = data.title
                val content = data.content
            }, HttpStatus.OK)
        }
    }

    @PostMapping("/recents")
    fun recents(): List<Paste> {
        println("Got request to get recent pastes")
        return pasteRepo.findTop100ByOrderByCreatedAtDesc()
    }

    @GetMapping("/{id}")
    fun pasteId(@PathVariable id: String): ResponseEntity<Paste>{
        println("Got request to view paste with id: $id")
        val paste = pasteRepo.findById(id.toLong())
        if(paste.isPresent)
            return ResponseEntity(paste.get(),HttpStatus.OK)
        else
            return ResponseEntity(HttpStatus.NOT_FOUND)
    }

}