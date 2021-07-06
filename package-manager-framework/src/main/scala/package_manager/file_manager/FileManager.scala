package package_manager.file_manager

import package_manager.dependency_solver.InstallPackage
import package_manager.Version
import package_manager.PackageInfo
import package_manager.PackageBaseInfo

trait FileManager[O <: FileManageOriented, V <: Version] {
  protected val oriented: O

  def install(set: Set[InstallPackage[V]]): Unit

  def uninstall(pack: PackageBaseInfo[V]): Unit
}
