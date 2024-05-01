# 1. Java 8을 기반으로 하는 Docker 이미지를 사용합니다.
FROM openjdk:8-jdk-alpine

ENV LC_ALL=C.UTF-8
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 2. 작업 디렉토리를 설정합니다.
WORKDIR /app

# 2. 애플리케이션의 JAR 파일을 Docker 이미지에 복사합니다.
COPY ./build/libs/bbs-0.0.1-SNAPSHOT.jar .

# 4. 포트 8080을 외부에 노출합니다.
EXPOSE 8080

# 5. 컨테이너가 시작될 때 실행할 명령을 지정합니다.
CMD ["java", "-jar", "bbs-0.0.1-SNAPSHOT.jar"]