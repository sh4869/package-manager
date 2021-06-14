package package_manager

import scala.collection.mutable.ListBuffer

trait DependencySolver[DependencyType <: Dependency, V <: Version] {
  protected val packageManagerOption: PackageManagerOption
  protected val packageRegistry: PackageRegistry[DependencyType,V]

  import VersionRange.OneVersionRange

  def solve(
      newPackages: Seq[PackageInstallRequest[V]],
      remove: Seq[PackageInfo[DependencyType, V]],
      installed: Seq[PackageInfo[DependencyType, V]]
  ): Set[InstallPackage[V]]

  def update(
      installed: Seq[PackageInfo[DependencyType, V]]
  ): Set[InstallPackage[V]] = {
    packageRegistry.update
    var newPackages = ListBuffer.empty[PackageInstallRequest[V]]
    var remove = ListBuffer.empty[PackageInfo[DependencyType, V]]
    installed.foreach(pack => {
      val packages = packageRegistry.getPackages(pack.name)
      packages
        .filter(p => p.version.isNewerThan(pack.version))
        .sortWith((a, b) => a.version.isNewerThan(b.version))
        .collect {
          case newPackage => {
            newPackages += PackageInstallRequest(
              newPackage.baseInfo.name,
              newPackage.baseInfo.version
            )
            remove += pack
          }
        }
    })
    solve(newPackages.toSeq, remove.toSeq, installed)
  }
}
