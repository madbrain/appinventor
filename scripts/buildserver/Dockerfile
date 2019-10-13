FROM openjdk:8-jdk-alpine
RUN mkdir -p /opt/buildserver
COPY lib/* /opt/buildserver/
COPY launch.sh /opt/buildserver/
RUN chmod 755 /opt/buildserver/launch.sh
VOLUME /tmp
EXPOSE 9990
ENTRYPOINT [ "/opt/buildserver/launch.sh" ]