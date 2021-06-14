package package_manager

case class InstallPackage[V <: Version](
    pack: PackageBaseInfo[V],
    requiredBy: Option[PackageBaseInfo[V]],
    installed: Boolean
)