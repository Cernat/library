# Changing directory backwards
#echo "[INFO] Changing directory"

# Changing the access permission
#echo "[INFO] Changing access permission"
#chmod +x travis_sleep.sh

# Executing travis_sleep.sh
#echo "[INFO] Executing travis_sleep"
#bash travis_sleep.sh

# Changing directory backwards
echo "[INFO] Changing directory"
cd library-backend/library-app

# Run maven clean install
echo "[INFO] Running mvn clean install"
mvn clean install