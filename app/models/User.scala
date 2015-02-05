package models

import org.joda.time._
import play.api.db.slick.Config.driver.simple._

case class User(id: Option[Int], var firstname: String, var lastname: String, var email: String, var password: String, var telephone: Int, var fax: String, var company: String, var address: String, var countryId: Int, var provinceId: Int)

class Users(tag: Tag) extends Table[User](tag, "users"){
 def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("firstname")
  def lastName = column[String]("lastname")
  def email = column[String]("email")
  def password = column[String]("password")
  def telephone = column[Int]("telephone")
  def fax = column[String]("fax")
  def company = column[String]("company")
  def address = column[String]("address")
  def countryId = column[Int]("countryid")
  def provinceId = column[Int]("provinceid")

  def * = (id ?, firstName, lastName, email, password, telephone, fax, company, address, countryId, provinceId) <>(User.tupled, User.unapply)
}