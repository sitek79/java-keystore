https://gist.github.com/nirmalchoudhari/31f9661408039176e6a5ad951826c6c0
https://www.planttext.com/
https://www.baeldung.com/java-keystore
https://habr.com/ru/post/445786/

https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html

https://www.8host.com/blog/osnovy-java-keytool-rabota-s-java-keystore/

https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keystore-types
https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/security/KeyStore.html

https://github.com/eugenp/tutorials/tree/master/core-java-modules/core-java-security
https://www.educba.com/java-keystore/

C:\Users\tikhonovav\ks
C:\prog\java\keytool_test\test1

keytool -list -v -keystore keystore.jks

Скачаем сертификат интересующего нас домена:  
echo -n | openssl s_client -connect habr.com:443 -servername habr.com 2>&1 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > /tmp/habr.com.crt

Смотрим детали сертификата:  
openssl x509 -noout -text -in /tmp/habr.com.crt

Выдираем серийный номер сертификата:  
openssl x509 -in /tmp/habr.com.crt -noout -serial

Выведем на экран сертификат интересующего нас домена и цепочки промежуточных (Intermediate) сертификатов:  
echo -n | openssl s_client -connect habr.com:443 -showcerts

Определим OCSP-сервер:
openssl x509 -in /tmp/habr.com.crt -noout -ocsp_uri

Отправим запрос OCSP-серверу проверить сертификат на предмет отзыва:  
openssl ocsp -url http://ocsp.comodoca.com -issuer /tmp/intermediate.crt -cert /tmp/habr.com.crt -text

=======
JavaFX. 1. Создать новый проект из архетипа Maven.
### самый простой Архетип
```
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple -DarchetypeVersion=0.0.3 -DgroupId=ru.silent -DartifactId=javakeytool -Dversion=1.0.0 -Djavafx-version=17
```
### тот-же Архетип с обновленной версией
```
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple -DarchetypeVersion=0.0.6 -DgroupId=ru.silent -DartifactId=javakeytool -Dversion=1.0.0 -Djavafx-version=17.0.2
```
### Архетип с FXML javafx-archetype-fxml
```
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-fxml -DarchetypeVersion=0.0.6 -DgroupId=ru.silent -DartifactId=javakeytool -Dversion=1.0.0 -Djavafx-version=17.0.2
```
### Другие Архетипы
https://github.com/openjfx/javafx-maven-archetypes

JavaFX. 2. Привести к нужному соответствию pom.xml файл проекта Maven .
### pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
<modelVersion>4.0.0</modelVersion>
<groupId>ru.silent</groupId>
<artifactId>javakeytool</artifactId>
<version>1.0.0</version>
<packaging>jar</packaging>

```
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>17.0.2</java.version>
        <!--<maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>-->
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-controls</artifactId>
            <version>17.0.2</version>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-fxml</artifactId>
            <version>17.0.2</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <!--<release>11</release>-->
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.openjfx</groupId>
                <artifactId>javafx-maven-plugin</artifactId>
                <version>0.0.8</version>
                <executions>
                    <execution>
                        <!-- Default configuration for running -->
                        <!-- Usage: mvn clean javafx:run -->
                        <id>default-cli</id>
                        <configuration>
                            <mainClass>ru.silent.App</mainClass>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>
```



mvn clean javafx:run

######
jshell --show-version
jshell -v
/exit
/edit

#############
https://openjfx.io/openjfx-docs/#maven

### Запуск .bat файлом
set PATH_TO_FX_MODS="path\to\javafx-jmods-19"
set JAVA_HOME="path\to\jdkfx-19"
mvn compile package
java -jar shade\hellofx.jar

#######

# Cross-platform jar
<dependencies>
        ...
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>19</version>
            <classifier>win</classifier>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>19</version>
            <classifier>linux</classifier>
        </dependency>
        <dependency>
            <groupId>org.openjfx</groupId>
            <artifactId>javafx-graphics</artifactId>
            <version>19</version>
            <classifier>mac</classifier>
        </dependency>
    </dependencies>
