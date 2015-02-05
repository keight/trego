package controllers

import java.io.File
import models.Slide
import models.schema.SlideSchema
import org.apache.commons.lang3.RandomStringUtils
import play.api.data._
import play.api.libs.json._
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}


object Slides extends Controller {

  var slideForm = Form(
  mapping(
    "slide-id" -> optional(number),
    "slide-name"-> text,
    "slide-description"-> text,
    "image" -> text
  )(Slide.apply)(Slide.unapply))

  var slForm = Form(
    tuple(
      "slide-name"-> text,
      "slide-description"-> text
    ))

  def slide = Action {
    Ok(views.html.admins.slide("cuc ky"))
  }

  def insert = Action(parse.multipartFormData) { implicit request =>
    request.body.file("slide-image").map { picture =>
      slideForm.bindFromRequest.fold(
        formWithErrors => {
          // binding failure, you retrieve the form containing errors:
          // in your form, test .hasErrors
          Redirect(routes.Slides.insert()).flashing(
            "error" -> "Xin lỗi vui lòng điền đầy đủ thông tin !")
        },
        slideData => {
          val filename : Array[String] = picture.filename.split("\\.")
          val newFilename : String = RandomStringUtils.randomAlphabetic(10)+"."+filename(1)
          picture.ref.moveTo(new File("public/uploads/"+newFilename), true)
          slideData.image = newFilename
          SlideSchema.insert(slideData) match {
            case 1 => Redirect(routes.Slides.insert())
            case _ => InternalServerError
          }
        }
      )
    }.getOrElse {
      Redirect(routes.Slides.insert()).flashing(
        "error" -> "Xin lỗi vui lòng chọn file hình !")
    }
  }
}
