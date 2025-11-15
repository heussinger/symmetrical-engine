#!/bin/bash
# wait-for-db.sh
# Wartet, bis PostgreSQL auf host:port antwortet, bevor Spring Boot startet

set -e

HOST=${DB_HOST:-db}
PORT=${DB_PORT:-5432}
USER=${DB_USER:-user}
PASSWORD=${DB_PWD:-password}

echo "Warte auf Datenbank $HOST:$PORT ..."

# Endlosschleife, bis DB erreichbar
until PGPASSWORD=$PASSWORD psql -h $HOST -U $USER -d $USER -c '\q' 2>/dev/null; do
  echo "Datenbank nicht erreichbar, versuche erneut in 2 Sekunden..."
  sleep 2
done

echo "Datenbank ist bereit! Starte Spring Boot..."
exec java -jar /app/your-spring-boot-app.jar

