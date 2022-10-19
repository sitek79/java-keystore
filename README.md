["# java-keystore" 
"# java-keystore" 
](https://gist.github.com/nirmalchoudhari/31f9661408039176e6a5ad951826c6c0
https://www.planttext.com/
https://www.baeldung.com/java-keystore
https://habr.com/ru/post/445786/

https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/JSSERefGuide.html

https://www.8host.com/blog/osnovy-java-keytool-rabota-s-java-keystore/

https://docs.oracle.com/en/java/javase/11/docs/specs/security/standard-names.html#keystore-types

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
)

The following commands creates four key pairs named ca, ca1
keytool -alias ca1 -dname CN=CA -genkeypair -keystore keystore.jks -keyalg rsa

С опцией -genkey, keytool генерирует новую пару public/private ключей, и для public ключа создает self-signed сертификат.
keytool -genkey -keyalg rsa -keystore server.jks -dname "CN=localhost, OU=IT, O=dev64-wordpress, L=Moscow, ST=Moscow, C=RU" -storepass password -alias server -keypass password

keytool -list -keystore server.jks

keytool -export -keystore server.jks -alias server -storepass password -file server.cer
keytool -import -keystore clienttrust.jks -file server.cer -storepass storepass


The following two commands create a chain of signed certificates; ca signs ca1 and ca1 signs ca2, all of which are self-issued:
??? keytool -alias ca1 -certreq | keytool -alias ca -gencert -ext san=dns:ca1 | keytool -alias ca1 -importcert


# Pre-configured options file
preconfig.cfg

# A tiny pre-configured options file
keytool.all = -keystore ${user.home}/ks
keytool.list = -v
keytool.genkeypair = -keyalg rsa

keytool -conf preconfig.cfg -genkeypair -alias client

keytool -conf preconfig.cfg -genkeypair -alias client -keyalg ec
# файл ks хранилища появится здесь:
C:\Users\tikhonovav


# читаем
keytool -keystore ~/ks -v -list


When generating a certificate or a certificate request, the default signature algorithm (-sigalg option) is derived from the algorithm of the underlying private key to provide an appropriate level of security strength as follows:

keyalg      keysize    default sigalg
DSA         any size   SHA256withDSA
RSA         <= 3072    SHA256withRSA
            <= 7680    SHA384withRSA
            > 7680     SHA512withRSA
EC          < 384      SHA256withECDSA
            < 512      SHA384withECDSA
            = 512      SHA512withECDSA
RSASSA-PSS  <= 3072    RSASSA-PSS (with SHA-256)
            <= 7680    RSASSA-PSS (with SHA-384)
            > 7680     RSASSA-PSS (with SHA-512)
EdDSA       255        Ed25519
            448        Ed448
Ed25519     255        Ed25519
Ed448       448        Ed448
