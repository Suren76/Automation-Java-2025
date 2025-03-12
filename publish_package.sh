set -a; source .env; set +a;
mvn clean deploy -s settings.xml;