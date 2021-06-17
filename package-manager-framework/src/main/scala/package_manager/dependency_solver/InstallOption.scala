package package_manager.dependency_solver

case class InstallOption(
    canInstallMultipleVersion: Boolean,
    canSpecifyConflictPacakge: Boolean,
    allowCircularDependency: Boolean
)
