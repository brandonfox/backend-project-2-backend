package brandon.backend.project2backend

import brandon.backend.project2backend.db_objects.Paste
import brandon.backend.project2backend.db_repositories.PasteRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.time.LocalDateTime

@RestController
@CrossOrigin(origins = ["http://localhost:8080"])
@RequestMapping("/api")
class ApiController @Autowired constructor(val pasteRepo: PasteRepo){

    @PostMapping("/paste")
    fun paste(@RequestBody data: Paste): ResponseEntity<Any>{
        return try {
            data.createdAt = LocalDateTime.now()
            println("Title: ${data.title}, Content: ${data.content}, Time posted: ${data.createdAt}")
            pasteRepo.save(data)
            ResponseEntity(object {
                val title = data.title
                val content = data.content
            },HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(e.toString(),HttpStatus.BAD_REQUEST)
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