package package_manager

import scala.collection.mutable.ListBuffer

trait DependencySolver[DependencyType <: Dependency] {
  protected val packageManagerOption: PackageManagerOption
  protected val packageRegistry: PackageRegistry[DependencyType]

  def solve(
      newPackages: Seq[PackageInstallRequest],
      remove: Seq[PackageInfo[DependencyType]],
      installed: Seq[PackageInfo[DependencyType]]
  ): Set[InstallPackage]

  def update(
      installed: Seq[PackageInfo[DependencyType]]
  ): Set[InstallPackage] = {
    packageRegistry.update
    var newPackages = ListBuffer.empty[PackageInstallRequest]
    var remove = ListBuffer.empty[PackageInfo[DependencyType]]
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
