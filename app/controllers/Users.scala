package controllers

import play.api.mvc.{Action, Controller}

object Users extends Controller {

  def login = Action {
    Ok(views.html.users.login("Login"))
  }

  def register = Action {
    Ok(views.html.users.register("Register"))
  }
}