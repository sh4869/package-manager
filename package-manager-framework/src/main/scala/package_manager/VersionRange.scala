package package_manager

trait VersionRange {
  def acceptVersions(list: Seq[Version]): Seq[Version]
}

object VersionRange {
  // version -> VersionRangeへのimplicit
  implicit class OneVersionRange(val version: Version) extends VersionRange {
    def acceptVersions(list: Seq[Version]): Seq[Version] =
      list.filter(_ == version)
  }
}
