FROM tomcat:8.5-jre8-alpine
RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./target/alterUber_domain.war /usr/local/tomcat/webapps/alter-uber-service.war
VOLUME /var/log/alter-uber-service:/var/log/alter-uber-service/
CMD ["catalina.sh", "run"]
