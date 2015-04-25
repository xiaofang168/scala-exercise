package com.fangj.abstrac

class Food
abstract class Animal {
  //type SuitableFood <: Food

  //def eat(food: SuitableFood)

  def eat[T <: Food](food: T)
}
class Grass extends Food

class Cow extends Animal {
  type SuitableFood = Grass
  override def eat[Grass](food: Grass) {}
}
