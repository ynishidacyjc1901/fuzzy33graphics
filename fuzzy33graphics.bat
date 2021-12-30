@echo off

rem  batch file for fuzzy33graphics.jar

rem  Last updated      DEC 30 2021
rem  Author            YUKI NISHIDA


start java --module-path "%JAVAFX_HOME%"  --add-modules=javafx.base --add-modules=javafx.controls --add-modules=javafx.fxml --add-modules=javafx.graphics --add-modules=javafx.media --add-modules=javafx.swing --add-modules=javafx.web -jar fuzzy33graphics.jar 

