@echo off
echo Starting Full Stack Product Management Application...
echo.

echo 1. Starting Spring Boot Backend...
start "Spring Boot Backend" cmd /c "cd productmgmtboot && mvn spring-boot:run"

echo Waiting for backend to start...
timeout /t 10 /nobreak > nul

echo.
echo 2. Starting Angular Frontend...
start "Angular Frontend" cmd /c "cd product-management-app && npm start"

echo.
echo Both applications are starting...
echo Backend: http://localhost:8080
echo Frontend: http://localhost:4200
echo.
pause
