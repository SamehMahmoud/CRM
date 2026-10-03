build:
	./mvnw clean package -DskipTests

run:
	set -a && . ./.env && set +a && ./mvnw spring-boot:run

test:
	set -a && . ./.env && set +a && ./mvnw clean test