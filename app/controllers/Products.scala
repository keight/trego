package controllers

import models.schema.SlideSchema
import play.api._
import play.api.mvc._

object Products extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready.")(SlideSchema.getAll))
  }

  def checkout = Action {
    Ok(views.html.products.checkout("Check Out"))
  }

  def cart = Action {
    Ok(views.html.products.cart("Your Cart"))
  }

  def detail(id: Int) = Action {
    Ok(views.html.products.detail("Product detail"))
  }

  def compare = Action {
    Ok(views.html.products.compare("Compare products"))
  }

  def category = Action {
    Ok(views.html.products.category("Category"))
  }

}