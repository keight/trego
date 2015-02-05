package models
import play.api.db.slick.Config.driver.simple._

case class Slide(id: Option[Int], name: String, description: String, image: String)
class Slides(tag: Tag) extends Table[Slide](tag, "slides"){
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def description = column[String]("description")
  def image = column[String]("image")

  def * = (id ?, name, description, image)<>(Slide.tupled, Slide.unapply)
}