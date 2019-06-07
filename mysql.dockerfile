FROM mysql:5.7

LABEL Author="Vitaly Dragun", Version=0.1

ENV MYSQL_ROOT_PASSWORD=root \
	MYSQL_USER=germes \
	MYSQL_PASSWORD=germes \
	MYSQL_DATABASE=germes