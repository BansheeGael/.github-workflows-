@echo off
setlocal

REM Найти директорию скрипта
set SCRIPT_DIR=%~dp0

REM Установить переменные окружения
set DEFAULT_JVM_OPTS=

REM Запуск Gradle
"%SCRIPT_DIR%gradlew" %*

endlocal
