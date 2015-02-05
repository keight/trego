package models.schema

import models.{Users, User}
import org.mindrot.jbcrypt.BCrypt
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

import scala.slick.lifted.TableQuery

object UserSchema {
  private val users = TableQuery[Users]

  def finAll = DB.withSession{ implicit session =>
    users sortBy (_.id.desc) list
  }

  def insert(user: User) = DB.withSession{ implicit session =>
    users += user
  }

  def findByEmail(email: String) = DB.withSession{ implicit session =>
    users filter (_.email === email) firstOption
  }

  def update(id: Int)(user: User) = DB.withSession{ implicit session =>
    users filter (_.id === id) update User(Option(id), user.firstname, user.lastname, user.email, user.password, user.telephone, user.fax, user.company, user.address, user.countryId, user.provinceId)
  }

  def destroy(id: Int) = DB.withSession{ implicit session =>
    users filter (_.id === id) delete
  }
}