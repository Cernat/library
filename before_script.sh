# Change directory to Docker folder
echo "[INFO] Changing directory"
cd library-backend/library-app/src/main/Docker

# Pulling down the oracle database image
echo "[INFO] Pulling down oracle image"
docker-compose pull db

# Running a container of database
echo "[INFO] Running db container"
docker-compose up -d db