docker-compose down
CALL ./maven-build.bat
docker-compose build --no-cache
docker-compose up