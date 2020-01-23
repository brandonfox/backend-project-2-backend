package brandon.backend.project2backend

import brandon.backend.project2backend.db_objects.Paste
import brandon.backend.project2backend.db_repositories.PasteRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:8080"])
@RequestMapping("/api")
class ApiController @Autowired constructor(val pasteRepo: PasteRepo){

    @PostMapping("/paste")
    fun paste(@RequestBody data: Paste){
        data.timePosted = System.currentTimeMillis()
        println("Title: ${data.title}, Content: ${data.content}, Time posted: ${data.timePosted}")
        pasteRepo.save(data)
    }

    @PostMapping("/recents")
    fun recents(): List<Paste> {
        println("Got request to get recent pastes")
        return pasteRepo.findTop100ByOrderByTimePostedDesc()
    }

    @GetMapping("/{id}")
    fun pasteId(@PathVariable id: String): Paste{
        println("Got request to view paste with id: $id")
        val paste = pasteRepo.findById(id.toLong())
        if(paste.isPresent)
            return paste.get()
        else
            throw IllegalArgumentException()
    }

}