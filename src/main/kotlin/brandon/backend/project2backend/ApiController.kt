package brandon.backend.project2backend

import brandon.backend.project2backend.db_objects.Paste
import brandon.backend.project2backend.db_repositories.PasteRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ApiController @Autowired constructor(val pasteRepo: PasteRepo){

    @PostMapping("/paste")
    fun paste(@RequestBody data: Paste){
        pasteRepo.save(data)
    }

    @PostMapping("/recents")
    fun recents(): List<Paste> {
        return pasteRepo.findTop100ByOrderByTimePostedDesc()
    }

    @GetMapping("/{id}")
    fun pasteId(@PathVariable id: String): String{
        return id
    }

}