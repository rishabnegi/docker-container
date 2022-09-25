FROM openjdk:11.0.7-jdk
RUN wget https://downloads.gradle-dn.com/distributions/gradle-6.8-bin.zip
RUN unzip gradle-6.8-bin.zip
ENV GRADLE_HOME /gradle-6.8
ENV PATH $PATH:/gradle-6.8/bin
#compile and run app
WORKDIR /app
COPY ./ /app
RUN gradle build --rerun-tasks --no-build-cache
CMD ["java","-jar","build/libs/spring.jar"]