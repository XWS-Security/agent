FROM maven AS appServerBuild
ARG STAGE=dev
ARG APPLLICATION_REPOSITORY=agent
WORKDIR /app
RUN curl -L https://github.com/XWS-Security/agent/archive/refs/heads/certificates.tar.gz | tar -xz && \
    cd agent-certificates && \
    mvn package -P${STAGE} -DskipTests
    
FROM openjdk AS appWebServerRuntime
COPY --from=appServerBuild app/agent-certificates/target/agent-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080
CMD java -jar agent-0.0.1-SNAPSHOT.jar
