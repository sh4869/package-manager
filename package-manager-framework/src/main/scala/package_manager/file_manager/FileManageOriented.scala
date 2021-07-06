package package_manager.file_manager

import package_manager.PackageBaseInfo
import package_manager.Version

sealed trait FileManageOriented

case class DatabaseOriented(basedir: String) extends FileManageOriented

case class FileSystemOriented[V <: Version](
    basedir: String,
    pathFunc: (PackageBaseInfo[V]) => String
) extends FileManageOriented
