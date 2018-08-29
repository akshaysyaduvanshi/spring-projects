package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resource
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
class UserController(@Autowired val userRepo: UserSpringDataRepo,
                     @Autowired val postRepo: PostSpringDataRepo) {
    @GetMapping(path = ["/user"])
    fun getUsers(): List<User> {
        return userRepo.findAll()
    }

    @GetMapping(path = ["/user/{id}"])
    fun getUser(@PathVariable id: Int): Resource<User> {
        try {
            val user =  userRepo.getOne(id)
            val resource = Resource(user)
            val linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.javaClass).getUsers())
            resource.add(linkTo.withRel("all-users"))
            return resource

        } catch (e: Exception) {
            throw UserNotFoundException(e.message!!)
        }
    }

    @PostMapping(path = ["/user"])
    fun createUser(@Valid @RequestBody user: User): ResponseEntity<User> {
        val user = userRepo.save(user)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.id)
                .toUri()
        return ResponseEntity.created(uri).build()
    }

    @DeleteMapping(path = ["/user/{id}"])
    fun deleteUser(@PathVariable id: Int): User {
        var user: User?
        try {
            user  = userRepo.getOne(id)
        } catch (e: Exception) {
            throw UserNotFoundException("User not found")
        }
        userRepo.deleteById(id)
        return user!!
    }

    @GetMapping(path = ["/user/{id}/posts"])
    fun getPostForAUser(@PathVariable id: Int): List<Post>? {
        try {
            return userRepo.getOne(id).posts

        } catch (e: Exception) {
            throw UserNotFoundException(e.message!!)
        }
    }

    @PostMapping(path = ["/user/{id}/posts"])
    fun createPost(@PathVariable id: Int, @RequestBody post: Post): Post {
        try {
            val user =  userRepo.getOne(id)
            post.user = user
            postRepo.save(post)
            return post

        } catch (e: Exception) {
            throw UserNotFoundException(e.message!!)
        }
    }
}
