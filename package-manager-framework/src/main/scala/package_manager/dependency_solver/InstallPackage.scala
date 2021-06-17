package package_manager.dependency_solver

import package_manager.PackageBaseInfo
import package_manager.Version

case class InstallPackage[V <: Version](
    pack: PackageBaseInfo[V],
    requiredBy: Option[PackageBaseInfo[V]],
    installed: Boolean
)
