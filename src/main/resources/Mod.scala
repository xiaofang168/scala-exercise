trait Mod extends (String => Boolean) {
  def apply(a: String):Boolean = if(a=="ddd") true else false
}
new Mod { }