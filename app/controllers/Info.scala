package controllers

import play.api.mvc.{Action, Controller}

object Info extends Controller{

  def aboutus = Action{
    Ok(views.html.info.aboutus("About us"))
  }

  def contact = Action{
    Ok(views.html.info.contact("Contact"))
  }
}