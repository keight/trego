package controllers

import models.schema.SlideSchema
import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Trego - Creative Portfolio Template")(SlideSchema.getAll))
  }

}