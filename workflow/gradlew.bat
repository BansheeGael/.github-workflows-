@echo off
@rem Finds the java.exe in the same folder as this script
setlocal
set DIRNAME=%~dp0
set JAVA_EXE=java.exe
set WRAPPER_JAR="%DIRNAME%gradle\wrapper\gradle-wrapper.jar"
set CLASSPATH=%WRAPPER_JAR%

:execute
"%JAVA_EXE%" -Dorg.gradle.appname=gradlew -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
