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
