package controllers

import play.api.mvc.{Action, Controller}

object Slide extends Controller {


  def slide = Action {
    Ok(views.html.admins.slide("cuc ky"))
  }

  def insert = Action(parse.multipartFormData) { implicit request =>
    request.body.file("slide-image").map { picture =>
      println(picture.contentType)
      println(picture.filename)
      println(picture.key)
      println(picture.ref)

    }
    Redirect(routes.Slide.slide())
  }
}