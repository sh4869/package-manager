package package_manager

case class InstallPackage(
    pack: PackageBaseInfo,
    requiredBy: Option[PackageBaseInfo],
    installed: Boolean
)