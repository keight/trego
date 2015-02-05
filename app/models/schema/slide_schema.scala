package models.schema

import models.{Slide, Slides}
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import scala.slick.lifted.TableQuery

object SlideSchema {
  private val slides = TableQuery[Slides]

  def insert(slide: Slide) = DB.withSession{implicit session =>
    slides += slide
  }

  def getAll = DB.withSession{ implicit session =>
    slides sortBy (_.id.desc) list
  }
}