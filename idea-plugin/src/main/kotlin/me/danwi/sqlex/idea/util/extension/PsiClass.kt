package me.danwi.sqlex.idea.util.extension

import com.intellij.psi.PsiClass

//获取全限定包名
val PsiClass.qualifiedPackageName: String?
    inline get() = this.qualifiedName?.removeSuffix(".${this.name}")