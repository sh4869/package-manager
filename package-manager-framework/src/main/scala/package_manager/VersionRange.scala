package package_manager

trait VersionRange[V <: Version] {
  def acceptVersions(list: Seq[V]): Seq[V]
}

object VersionRange {
  // version -> VersionRangeへのimplicit
  implicit class OneVersionRange[V <: Version](val version: V) extends VersionRange[V] {
    def acceptVersions(list: Seq[V]): Seq[V] =
      list.filter(_ == version)
  }
}
