package package_manager

trait VersionRange {
    def acceptVersions(list: Seq[Version]): Seq[Version]
}