package com.example.demo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
class User {
    @Id
    @GeneratedValue
    var id: Int = 0

    @Size(min = 2, message = "Name should be at lease two chars")
    var name: String = ""
    var location: String = ""
}

@Repository
interface UserSpringDataRepo: JpaRepository<User, Int>
