package controllers

import anorm.NotAssigned
import models.User
import models.schema.UserSchema
import org.mindrot.jbcrypt.BCrypt

//import play.api.data.{Form, Forms}
import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import play.api.data.format.Formats._
import play.api.Play.current
import play.api.data._
import play.api.data.Forms._
object Users extends Controller {

  implicit val UserFormatter = Json.format[User]

//  val userForm = Form(
//    mapping(
//      "name" -> text,
//      "age" -> optional(number)
//    )(User1.apply)(User1.unapply)
//  )

//  val userForm = Form(
//    tuple(
//      "register-name" -> text,
//      "register-lastName" -> text,
//      "email" -> text,
//      "password" -> text,
//      "telephone" -> number,
//      "fax" -> text,
//      "company" -> text,
//      "address" -> text,
//      "countryId" -> number,
//      "provinceId" -> number
//    ))
//
  val userForm = Form(
    mapping(
      "register-id" -> optional(number),
      "register-firstname" -> text,
      "register-lastname" -> text,
      "register-email" -> text,
      "register-password" -> text,
      "register-telephone" -> number,
      "register-fax" -> text,
      "register-company" -> text,
      "register-address" -> text,
      "register-countryid" -> number,
      "register-provinceid" -> number
    )(User.apply)(User.unapply)
  )

  val loginForm = Form(
    tuple(
      "login-email" ->text,
      "login-password" -> text
    )
  )


  def loginView = Action {
    Ok(views.html.users.login("Login"))
  }

  def register = Action {
    println("alo alo")
    Ok(views.html.users.register("Register"))
  }

  def submit = Action { implicit request =>
    var user: User = userForm.bindFromRequest.get
    Ok(views.html.users.register("Register"))
  }

  def registerPost() = Action{ implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        // binding failure, you retrieve the form containing errors:
        // in your form, test .hasErrors
        BadRequest(views.html.users.register("Register"))
      },
      userData => {
        UserSchema.findByEmail(userData.email) match {
          case Some(users) => Ok(views.html.users.register("Register"))
          case None => {
            userData.password = BCrypt.hashpw(userData.password,BCrypt.gensalt())
            UserSchema.insert(userData) match {
              case 1 => Redirect(routes.Users.loginView())
              case _ => InternalServerError
            }
          }
        }
      }
    )
  }

  def login() = Action { implicit request =>
    val(email, password) = loginForm.bindFromRequest.get
    if (email == null || password == null){
      Ok(views.html.users.login("Login"))
    } else {
      UserSchema.findByEmail(email) match {
        case Some(user) => {
          if (BCrypt.checkpw(password, user.password)) {
            Redirect(routes.Application.index()).withSession("userId" -> user.id.toString(), "email" -> user.email)
          }
          else
            Ok(views.html.users.login("login"))
        }
        case None => Ok(views.html.users.login("login"))
      }
    }
  }

}