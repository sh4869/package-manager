package package_manager

import scala.collection.mutable.ListBuffer

trait DependencySolver[D <: Dependency, V <: Version] {
  protected val packageManagerOption: PackageManagerOption
  protected val packageRegistry: PackageRegistry[D,V]

  import VersionRange.OneVersionRange

  def solve(
      newPackages: Seq[PackageInstallRequest[V]],
      remove: Seq[PackageInfo[D, V]],
      installed: Seq[PackageInfo[D, V]]
  ): Set[InstallPackage[V]]

  def update(
      installed: Seq[PackageInfo[D, V]]
  ): Set[InstallPackage[V]] = {
    packageRegistry.update
    var newPackages = ListBuffer.empty[PackageInstallRequest[V]]
    var remove = ListBuffer.empty[PackageInfo[D, V]]
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
