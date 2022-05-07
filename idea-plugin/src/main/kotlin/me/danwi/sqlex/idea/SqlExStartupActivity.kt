package me.danwi.sqlex.idea

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import me.danwi.sqlex.idea.repositroy.showMaybeSqlExImportNotification
import me.danwi.sqlex.idea.repositroy.syncRepositoryService

class SqlExStartupActivity : StartupActivity {
    override fun runActivity(project: Project) {
        project.syncRepositoryService()
        project.showMaybeSqlExImportNotification()
    }
}