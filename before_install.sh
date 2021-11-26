# Removing the local docker-compose from Travis ubuntu container
echo "[INFO] Removing docker-compose standard version"
sudo rm /usr/local/bin/docker-compose

# Download the specified version
echo "[INFO] Downloading new docker-compose version"
curl -L https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-`uname -s`-`uname -m` > docker-compose

# Changing the access permission
echo "[INFO] Changing access permission"
chmod +x docker-compose

# Move docker-compose to specific folder
echo "[INFO] Move docker compose"
sudo mv docker-compose /usr/local/bin

# Setup credentials
echo "[INFO] Setting up credentials"
docker login -u "$DOCKER_USERNAME" -p "$DOCKER_PASSWORD"