package package_manager.dependency_solver

import package_manager.Version
import package_manager.VersionRange

case class PackageInstallRequest[V <: Version](
    name: String, 
    verison: VersionRange[V]
)