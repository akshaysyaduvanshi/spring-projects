package com.example.demo

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@ApiModel
class User {
    @Id
    @GeneratedValue
    var id: Int = 0

    @Size(min = 2, message = "Name should be at lease two chars")
    @ApiModelProperty("Name should be at lease two chars")
    var name: String = ""
    var location: String = ""

    @OneToMany(mappedBy = "user")
    var posts: List<Post>? = null
}

@Entity
class Post {
    @Id
    @GeneratedValue
    var id: Int = 0

    @Size(min = 2, message = "Description should be at lease two chars")
    var description: String = ""

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    lateinit var user: User
}


@Repository
interface UserSpringDataRepo: JpaRepository<User, Int>

@Repository
interface PostSpringDataRepo: JpaRepository<Post, Int>
